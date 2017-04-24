package com.diverhome.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import com.diverhome.wechat.core.TokenThread;
import com.tencent.WXSdk;

/**
 * 
 * ***************************************************************  
 * <p>Filename:    StartupListener.java
 * <p>Description: 服务启动监听用于初始化配置
 * <p>
 * <p>Copyright:   Copyright (c)2015  
 * <p>Company:     njits  
 * <p>Create at:   2015年12月1日 下午8:41:00
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2015年12月1日     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
public class StartupListener extends ContextLoaderListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {

		// 日志记录器定义在这里为了防止服务器启动时Log4j还未初始化
		Logger logger = LoggerFactory.getLogger(StartupListener.class);
		
		AppConfigManager.getInstance();

		// 获取appconfig.properties中配置的参数
		TokenThread.appid = AppConfigManager.getParamValueDirect("appid");
		TokenThread.appsecret = AppConfigManager.getParamValueDirect("appsecret");

		logger.info("weixin api appid:{}", TokenThread.appid);
		logger.info("weixin api appsecret:{}", TokenThread.appsecret);

		// 未配置appid、appsecret时给出提示
		if ("".equals(TokenThread.appid) || "".equals(TokenThread.appsecret)) {
			logger.error("appid and appsecret configuration error, please check carefully.");
		} else {
			// 启动定时获取access_token的线程
			new Thread(new TokenThread()).start();

			// 微信配置
			WXSdk.initSDKConfiguration(AppConfigManager.getParamValueDirect("api_key"), TokenThread.appid,
					TokenThread.appsecret, null, null, null, null);
		}
	}
}
