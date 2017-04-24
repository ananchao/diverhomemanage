package com.diverhome.wechat.pojo;

/**
 * 父菜按钮 包含有二级菜单项的一级菜单。这类菜单项包含有二个属性：name和sub_button
 * 
 * @author anchao
 * 
 */
public class ComplexButton extends Button {
	//二级菜单数组，个数应为1~5个
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
}
