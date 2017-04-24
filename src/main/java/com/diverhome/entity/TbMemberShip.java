package com.diverhome.entity;

import java.io.Serializable;

/**
 * ***************************************************************  
 * <p>Filename:    TbMemberShip.java
 * <p>Description: 会员表实体类
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
public class TbMemberShip implements Serializable {

	private static final long serialVersionUID = -4677542488860133118L;

	private int id;
	private String name;
	private int age;
	private String phone;
	private String email;
	private String sex;
	private String qualification;
	private String area_code;
	private String area_name;
	private String membership_level;
	private String join_way;
	private String openid;
	private int visit_count;
	private String update_time;
	private String insert_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getMembership_level() {
		return membership_level;
	}

	public void setMembership_level(String membership_level) {
		this.membership_level = membership_level;
	}

	public String getJoin_way() {
		return join_way;
	}

	public void setJoin_way(String join_way) {
		this.join_way = join_way;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getVisit_count() {
		return visit_count;
	}

	public void setVisit_count(int visit_count) {
		this.visit_count = visit_count;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		if (null != update_time && update_time.contains(".")) {
			update_time = update_time.substring(0, update_time.length() - 2);
		}
		this.update_time = update_time;
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
