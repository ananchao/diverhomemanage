package com.diverhome.wechat.message.req.event;

/**
 * 自定义菜单事件
 * 
 * @author anchao
 * 
 */
public class EventClickMessage extends EventBaseMessage {
	// 事件KEY值，与自定义菜单接口中KEY值对应
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

}
