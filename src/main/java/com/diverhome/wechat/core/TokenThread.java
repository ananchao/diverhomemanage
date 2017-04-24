package com.diverhome.wechat.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.diverhome.util.WeiXinUtil;
import com.diverhome.wechat.pojo.AccessToken;
import com.diverhome.wechat.pojo.JsapiTicket;

/**
 * 定时获取access_token的线程
 * @author anchao
 *
 */
public class TokenThread implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(TokenThread.class);
	
	// 第三方用户唯一凭证
	public static String appid = "";
	// 第三方用户唯一凭证密钥
	public static String appsecret = "";
	
	public static AccessToken accessToken = null;
	
	public static JsapiTicket jsapiTicket = null;

	public void run() {
		while (true) {
			try {
				accessToken = WeiXinUtil.getAccessToken(appid, appsecret);
				if (null != accessToken) {
					logger.info("获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresIn(), accessToken.getToken());
					jsapiTicket = WeiXinUtil.getJsapiTicket(accessToken.getToken());
					if (null != jsapiTicket) {
						logger.info("获取jsapi_ticket成功，有效时长{}秒 jsapi_ticket:{}", jsapiTicket.getExpiresIn(), jsapiTicket.getTicket());
						// 休眠7000秒
						Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
					} else {
						// 如果jsapiTicket为null，60秒后再获取
						Thread.sleep(60 * 1000);
					}
				} else {
					// 如果access_token为null，60秒后再获取
					Thread.sleep(60 * 1000);
				}
			} catch (InterruptedException e) {
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
					logger.error("{}", e1);
				}
				logger.error("{}", e);
			}
		}

	}
	
}
