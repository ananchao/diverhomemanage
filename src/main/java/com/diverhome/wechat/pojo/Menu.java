package com.diverhome.wechat.pojo;

/**
 * 菜单
 * 
 * @author anchao
 * 
 */
public class Menu {
	// 一级菜单数组，个数应为1~3个
	private Button[] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}
}
