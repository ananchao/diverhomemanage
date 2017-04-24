package com.diverhome.wechat.pojo;

/**
 * 菜单按钮的基类 所有菜单共同的属性name
 * 
 * @author anchao
 * 
 */
public abstract class Button {
	// 菜单标题
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
