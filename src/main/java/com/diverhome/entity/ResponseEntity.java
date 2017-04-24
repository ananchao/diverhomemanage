package com.diverhome.entity;

import java.io.Serializable;

import com.diverhome.util.Constant;

/**
 * ***************************************************************  
 * <p>Filename:    ResponseEntity.java
 * <p>Description: web请求响应数据实体
 * <p>
 * <p>Copyright:   Copyright (c)2016  
 * <p>Company:     njits  
 * <p>Create at:   2017年4月5日 下午9:26:51
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2017年4月5日     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
public class ResponseEntity implements Serializable {
	
	private static final long serialVersionUID = 2360152203744525776L;
	// 返回状态信息 成功：1 失败：0
	private int status = Constant.SUCCESS;
	// 返回的信息
	private String message;
	// 设置附加参数
	private Object data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
