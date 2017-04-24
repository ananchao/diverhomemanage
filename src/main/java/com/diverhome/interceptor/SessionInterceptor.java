package com.diverhome.interceptor;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.diverhome.util.Constant;


public class SessionInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = Logger.getLogger(SessionInterceptor.class);
	
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

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String requestUrl = request.getRequestURI();
		logger.debug("SessionInterceptor:" + requestUrl);
		if (uncheckUrls.contains(requestUrl)) {
			return true;
		} else {
			Object obj = request.getSession().getAttribute(Constant.SESSION_LOGIN_USER);
			if (obj==null) {
				request.setAttribute("msg", "您还没有登录或登录已超时，请重新登录");
				request.getRequestDispatcher("/manage/login").forward(request, response);
				return false;
			} else {
				return true;
			}
		}
	}
	
}
