package com.diverhome.wechat.pojo;
/**
 * ***************************************************************  
 * <p>Filename:    JsapiTicket.java
 * <p>Description: 微信JS接口的临时票据
 * <p>
 * <p>Copyright:   Copyright (c)2015  
 * <p>Company:     njits  
 * <p>Create at:   2015-7-9 下午2:04:41
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2015-7-9     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
public class JsapiTicket {
	
	private static JsapiTicket jsapiTicket;
	
	private String ticket;
	
	private int expiresIn;
	
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	private JsapiTicket() {
	}
	
	public static JsapiTicket getInstance() {
		if (jsapiTicket == null) {
			jsapiTicket = new JsapiTicket();
			return jsapiTicket;
		}
		return jsapiTicket;
	}
}
