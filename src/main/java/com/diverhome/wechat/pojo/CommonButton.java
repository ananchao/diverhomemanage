package com.diverhome.wechat.pojo;

/**
 * 子菜单项按钮 没有子菜单的菜单项，有可能是二级菜单项，也有可能是不含二级菜单的一级菜单。这类子菜单项一定会包含三个属性：type、name和key
 * 
 * @author anchao
 * 
 */
public class CommonButton extends Button {
	// 菜单的响应动作类型
	private String type;
	// 菜单KEY值，用于消息接口推送，不超过128字节
	private String key;
	// 网页链接，用户点击菜单可打开链接，不超过256字节
	private String url;
	// 调用新增永久素材接口返回的合法media_id
	private String media_id;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

}
