package com.diverhome.wechat.handler;

import java.util.Map;

import com.diverhome.util.PropertiesUtil;

/**
 * 处理器接口
 * 
 * @author anchao
 * 
 */
public abstract class IHandler {
	
	protected Map<String, String> requestMap;
	
	protected static String default_resp = PropertiesUtil.getProperties("wechatconfig.properties").getProperty("default_resp", "");

	public Map<String, String> getRequestMap() {
		return requestMap;
	}

	public void setRequestMap(Map<String, String> requestMap) {
		this.requestMap = requestMap;
	}

	public IHandler(Map<String, String> requestMap) {
		this.requestMap = requestMap;
	}
	
	//处理消息
	public abstract String handleRequest();
}
