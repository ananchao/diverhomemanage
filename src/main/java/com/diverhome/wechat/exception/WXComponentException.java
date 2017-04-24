package com.diverhome.wechat.exception;

/**
 * ***************************************************************  
 * <p>Filename:    WXComponentException.java
 * <p>Description: 自定义业务异常
 * <p>
 * <p>Copyright:   Copyright (c)2015  
 * <p>Company:     njits  
 * <p>Create at:   2015-6-25 下午12:09:05
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2015-6-25     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
public class WXComponentException extends Exception {
	
	private static final long serialVersionUID = 4451539124048751073L;

	public WXComponentException() {
		super();
	}

	public WXComponentException(String msg) {
		super(msg);
	}
	
	public WXComponentException(String msg, Exception e) {
		super(msg, e);
	}
}
