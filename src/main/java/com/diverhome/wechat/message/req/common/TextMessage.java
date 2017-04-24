package com.diverhome.wechat.message.req.common;


/**
 * 文本消息
 * 
 * @author anchao
 * 
 */
public class TextMessage extends CommonBaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
