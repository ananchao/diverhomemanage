package com.diverhome.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.diverhome.wechat.core.TokenThread;
import com.diverhome.wechat.pojo.AccessToken;
import com.diverhome.wechat.pojo.JsapiTicket;
import com.diverhome.wechat.pojo.Menu;
import com.tencent.common.RandomStringGenerator;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class WeiXinUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(TokenThread.class);

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return string
	 */
	public static String httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		HttpsURLConnection httpUrlConn = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			long time1 = System.currentTimeMillis();
			httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			inputStream = httpUrlConn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			logger.info("WeChat："+url+"执行效率：" + (System.currentTimeMillis() - time1));
		} catch (ConnectException ce) {
			logger.error("Weixin server connection timed out.");
		} catch (Exception e) {
			logger.error("https request error:{}", e);
		} finally {
			try {
				// 释放资源
				if (bufferedReader != null) {
					bufferedReader.close();
					inputStream = null;
				}
				if (inputStreamReader != null) {
					inputStreamReader.close();
					inputStreamReader = null;
				}
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
				if (httpUrlConn != null) {
					httpUrlConn.disconnect();
					httpUrlConn = null;
				}
			} catch (IOException e) {
				logger.error("connection close error:{}", e);
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 发起https请求并获取Json格式的结果
	 * @Title: httpRequestJson
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 * @return JSONObject
	 * @throws
	 */
	public static JSONObject httpRequestJson(String requestUrl,
			String requestMethod, String outputStr) {
		String resp = httpRequest(requestUrl, requestMethod, outputStr);
		JSONObject jsonObject = JSONObject.fromObject(resp);
		return jsonObject;
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;

		String requestUrl = Constant.ACCESS_TOKEN_URL.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		JSONObject jsonObject = httpRequestJson(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = AccessToken.getInstance();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				logger.error("获取token失败 errcode:{} errmsg:{}", jsonObject
						.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	
	/**
	 * 获取jsapi_ticket
	 * @param access_token
	 * @return
	 */
	public static JsapiTicket getJsapiTicket(String access_token) {
		JsapiTicket jsapiTicket = null;

		String requestUrl = Constant.JSAPI_TICKET_URL.replace("ACCESS_TOKEN", access_token);
		JSONObject jsonObject = httpRequestJson(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				jsapiTicket = JsapiTicket.getInstance();
				jsapiTicket.setExpiresIn(jsonObject.getInt("expires_in"));
				jsapiTicket.setTicket(jsonObject.getString("ticket"));
			} catch (JSONException e) {
				jsapiTicket = null;
				// 获取token失败
				logger.error("获取jsapi_ticket失败 errcode:{} errmsg:{}", jsonObject
						.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return jsapiTicket;
	}

	/**
	 * 获取缓存中的access_token
	 * 
	 * @return
	 */
	public static String getCacheAccessToken() {
		AccessToken accessToken = AccessToken.getInstance();
		String token = accessToken.getToken();
		return token;
	}
	
	/**
	 * 创建菜单
	 * 
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = Constant.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequestJson(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				logger.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}

	/**
	 * 获取客户端ip
	 * @param request
	 * @return String
	 */
	public static String getIpAddr(HttpServletRequest request) {
		/*String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}*/
		String ip = request.getRemoteAddr();
		return ip;
	}
	
	/**
	 * 生成商户订单号
	 * 商户系统内部的订单号,32个字符内、可包含字母
	 * 建议根据当前系统时间加随机序列来生成订单号
	 * @throws Exception
	 * @return String
	 */
	public static String createOutTradeNo() throws Exception {
		return TimeUtil.getCurrentTime("yyyyMMddHHmmssSSS") + RandomStringGenerator.getRandomNumberByLength(7);
	}

	/**
	 * 获取停车时常(格式：1小时20分)
	 * @Title: getParkTime
	 * @param time1
	 * @param time2
	 * @throws Exception
	 * @return String
	 */
	public static String getParkTime(String time1, String time2) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = simpleDateFormat.parse(time1);
		Date date2 = simpleDateFormat.parse(time2);
		long tmpTime1 = date2.getTime() - date1.getTime();
		int hour = (int) (tmpTime1 / (60 * 60 * 1000));
		long tmpTime2 = tmpTime1-(60 * 60 * 1000) * hour;
		int minute = (int) (tmpTime2 / (60 * 1000));
		return hour + "小时" + minute + "分钟";
	}
}
