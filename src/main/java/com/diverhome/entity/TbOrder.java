package com.diverhome.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ***************************************************************  
 * <p>Filename:    TbOrder.java
 * <p>Description: 订单表实体类
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
public class TbOrder implements Serializable {
	
	private static final long serialVersionUID = 9130295350552940689L;
	
	private int id;
	private String order_no;
	private String state;
	private int verification_code;
	private BigDecimal order_price;
	private int member_id;
	private int product_id;
	private String insert_time;
	private String validate_time;
	// 从下单到完成验证花费的时间（单位：秒）
	private int cost_time;
	// 关联的会员对象
	private TbMemberShip memberShip;
	// 关联的产品对象
	private TbProduct product;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getVerification_code() {
		return verification_code;
	}

	public void setVerification_code(int verification_code) {
		this.verification_code = verification_code;
	}

	public BigDecimal getOrder_price() {
		return order_price;
	}

	public void setOrder_price(BigDecimal order_price) {
		this.order_price = order_price;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
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
	
	public String getValidate_time() {
		return validate_time;
	}

	public void setValidate_time(String validate_time) {
		if (null != validate_time && validate_time.contains(".")) {
			validate_time = validate_time.substring(0, validate_time.length() - 2);
		}
		this.validate_time = validate_time;
	}

	public int getCost_time() {
		return cost_time;
	}

	public void setCost_time(int cost_time) {
		this.cost_time = cost_time;
	}

	public TbMemberShip getMemberShip() {
		return memberShip;
	}

	public void setMemberShip(TbMemberShip memberShip) {
		this.memberShip = memberShip;
	}

	public TbProduct getProduct() {
		return product;
	}

	public void setProduct(TbProduct product) {
		this.product = product;
	}

}
