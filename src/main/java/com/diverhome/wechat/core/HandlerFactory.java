package com.diverhome.wechat.core;

import java.util.Map;

import com.diverhome.util.Constant;
import com.diverhome.wechat.handler.EventClickMessageHandler;
import com.diverhome.wechat.handler.IHandler;
import com.diverhome.wechat.handler.ImageMessageHandler;
import com.diverhome.wechat.handler.LinkMessageHandler;
import com.diverhome.wechat.handler.LocationMessageHandler;
import com.diverhome.wechat.handler.SubscribeEventHandler;
import com.diverhome.wechat.handler.TextMessageHandler;
import com.diverhome.wechat.handler.VoiceMessageHandler;

/**
 * 消息处理器工厂类
 * 
 * @author anchao
 * 
 */
public class HandlerFactory extends AbstractFactory {

	@Override
	public IHandler createHandler(Map<String, String> requestMap) {
		IHandler handler = null;
		// 消息类型
		String msgType = requestMap.get("MsgType");
		// 文本消息
		if (msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)) {
			handler =  new TextMessageHandler(requestMap);
		}
		// 图片消息
		else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_IMAGE)) {
			handler = new ImageMessageHandler(requestMap);
		}
		// 地理位置消息
		else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_LOCATION)) {
			handler = new LocationMessageHandler(requestMap);
		}
		// 链接消息
		else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_LINK)) {
			handler = new LinkMessageHandler(requestMap);
		}
		// 音频消息
		else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_VOICE)) {
			handler = new VoiceMessageHandler(requestMap);
		}
		// 事件推送e
		else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_EVENT)) {
			// 事件类型
			String eventType = requestMap.get("Event");
			// 订阅
			if (eventType.equals(Constant.EVENT_TYPE_SUBSCRIBE)) {
				handler = new SubscribeEventHandler(requestMap);
			}
			// 取消订阅
			else if (eventType.equals(Constant.EVENT_TYPE_UNSUBSCRIBE)) {
			}
			// 菜单拉取消息时的事件推送
			else if (eventType.equals(Constant.EVENT_TYPE_CLICK)) {
				handler = new EventClickMessageHandler(requestMap);
			}
			// 菜单跳转链接时的事件推送
			else if (eventType.equals(Constant.EVENT_TYPE_VIEW)) {
				handler = new EventClickMessageHandler(requestMap);
			}
		}
		return handler;
	}

}
