package com.diverhome.entity;

import java.io.Serializable;
import java.util.List;

/**
 * ***************************************************************  
 * <p>Filename:    PaginationEntity.java
 * <p>Description: 分页数据实体
 * <p>
 * <p>Copyright:   Copyright (c)2016  
 * <p>Company:     njits  
 * <p>Create at:   2017年4月5日 下午9:26:37
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2017年4月5日     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
@SuppressWarnings("rawtypes")
public class PaginationEntity implements Serializable {
	
	private static final long serialVersionUID = -5593772817233616829L;
	
	private int total;
	
	private List rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
}
