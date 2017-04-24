package com.diverhome.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ***************************************************************  
 * <p>Filename:    TbProduct.java
 * <p>Description: 产品表实体类
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
public class TbProduct implements Serializable {

	private static final long serialVersionUID = -3695300295760166842L;

	private int id;
	private String title;
	private String brand;
	private BigDecimal original_price;
	private BigDecimal member_price;
	private String img_url;
	private String thumbnail_img_url;
	private String description;
	private int quantity;
	private String type;
	private String label;
	private String state;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public BigDecimal getOriginal_price() {
		return original_price;
	}

	public void setOriginal_price(BigDecimal original_price) {
		this.original_price = original_price;
	}

	public BigDecimal getMember_price() {
		return member_price;
	}

	public void setMember_price(BigDecimal member_price) {
		this.member_price = member_price;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
	public String getThumbnail_img_url() {
		return thumbnail_img_url;
	}

	public void setThumbnail_img_url(String thumbnail_img_url) {
		this.thumbnail_img_url = thumbnail_img_url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
