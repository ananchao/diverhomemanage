package com.diverhome.interceptor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.diverhome.entity.ResponseEntity;
import com.diverhome.util.Constant;
import com.diverhome.util.StringUtil;
import com.diverhome.wechat.jwt.Jwt;
import com.diverhome.wechat.jwt.TokenState;
import com.tencent.WXSdk;
import com.tencent.protocol.oauth2_protocol.OAuth2CodeReqData;

import net.sf.json.JSONObject;


public class RestInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = Logger.getLogger(RestInterceptor.class);
	
	//不需要验证的地址
	private ArrayList<String> uncheckUrls;

	public ArrayList<String> getUncheckUrls() {
		return uncheckUrls;
	}

	public void setUncheckUrls(ArrayList<String> uncheckUrls) {
		this.uncheckUrls = uncheckUrls;
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		response.setCharacterEncoding("UTF-8");		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 返回json对象
		response.setContentType("application/json; charset=utf-8");
		String requestUrl = request.getRequestURI();
		logger.debug("RestInterceptor:" + requestUrl);
		// 不需要验证的地址直接返回true
		for (String uncheckUrl : uncheckUrls) {
			int index = requestUrl.indexOf(uncheckUrl);
			if (index >= 0) {
				return true;
			}
		}
		ResponseEntity entity = new ResponseEntity();
		// 取出header中的customToken
		String customToken = request.getHeader("customToken");
		if (StringUtil.isValid(customToken)) {
			// 如果存在则判断是否合法
			Map<String, Object> resultMap = Jwt.validToken(customToken);
			TokenState state = TokenState.getTokenState((String) resultMap.get("state"));
			switch (state) {
			case VALID:
				logger.debug("有效token");
				entity.setStatus(Constant.SUCCESS);
				return true; // token验证通过直接返回true
			case EXPIRED:
				logger.debug("token过期");
				entity.setStatus(Constant.BUSINESS_ERROR);
				entity.setMessage(Constant.EXCEPTION_WECHAT_ORDER_MESSAGE);
				break;
			case INVALID:
				logger.debug("无效token");
				entity.setStatus(Constant.BUSINESS_ERROR);
				entity.setMessage(Constant.EXCEPTION_WECHAT_ORDER_MESSAGE);
				break;
			}
			// token未验证通过，返回失败
			PrintWriter out = response.getWriter();
			out.print(JSONObject.fromObject(entity).toString());
			out.flush();
			out.close();
			return false;
		} else {
			// 如果不存在，获取openid，如果openid注册过，向客户端返回customToken和member_id
			// 如果openid未注册，向客户端返回openid并通知客户端跳转到登录页面
			// 将用户请求的url(含参数)放入session中，用于用户注册后跳转到原请求页面
			//String realPath = "http://ananchao3.wicp.net/diverhome";
			String forwardUrl = "http://ananchao2.xicp.net/#/openIdValid";
			//String forwardUrl = realPath + Constant.OAUTH_FORWARDURL + "?" + getAllParam(request);
			//String forwardUrl = realPath + Constant.OAUTH_FORWARDURL;
			logger.debug("[ValidOpenIdController]oauth校验后跳转的url:" + forwardUrl);
			// OAuth授权接口参数封装实体类
			OAuth2CodeReqData oAuth2getCodeReq = new OAuth2CodeReqData(
					forwardUrl, Constant.OAUTH_RESPONSE_TYPE,
					Constant.OAUTH_SCOPE_SNSAPI_BASE, "");
			// 调用OAuth授权接口获取code
			String page = WXSdk.requestOAuth2CodeUrlService(oAuth2getCodeReq);
			//String getResp = new HttpSender().sendGet(page);
			entity.setData(page);
			// 设置跳转标识
			entity.setStatus(Constant.OPENID_VALID);
			PrintWriter out = response.getWriter();
			out.print(JSONObject.fromObject(entity).toString());
			out.flush();
			out.close();
			//response.sendRedirect(page);
			return false;
		}
	}
	
}
