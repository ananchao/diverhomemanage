package com.diverhome.entity;

import java.io.Serializable;

/**
 * ***************************************************************  
 * <p>Filename:    TbNotice.java
 * <p>Description: 公告表实体类
 * <p>
 * <p>Copyright:   Copyright (c)2016  
 * <p>Company:     njits  
 * <p>Create at:   2017年4月2日 下午5:09:34
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2017年4月2日     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
public class TbNotice implements Serializable {

	private static final long serialVersionUID = -7231862703123721884L;
	
	private int id;
	private String title;
	private String content;
	private String insert_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(String insert_time) {
		if (null != insert_time && insert_time.contains(".")) {
			insert_time = insert_time.substring(0, insert_time.length() - 2);
		}
		this.insert_time = insert_time;
	}

}
