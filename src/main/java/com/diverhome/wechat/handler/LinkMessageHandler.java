package com.diverhome.wechat.handler;

import java.util.Date;
import java.util.Map;

import com.diverhome.util.Constant;
import com.diverhome.util.XmlUtil;
import com.diverhome.wechat.message.resp.TextMessage;

/**
 * 链接消息处理器
 * @author anchao
 *
 */
public class LinkMessageHandler extends IHandler {

	public LinkMessageHandler(Map<String, String> requestMap) {
		super(requestMap);
	}
	
	public String handleRequest() {
		// 默认返回的文本消息内容
		// String respContent = "您发送的是链接消息！";
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		// 回复文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime()/1000);
		textMessage.setMsgType(Constant.RESP_MESSAGE_TYPE_TEXT);

		textMessage.setContent(default_resp);
		String respMessage = XmlUtil.textMessageToXml(textMessage);
		return respMessage;
	}

}
