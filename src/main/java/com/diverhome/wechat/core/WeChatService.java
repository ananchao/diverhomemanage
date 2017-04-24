package com.diverhome.wechat.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.diverhome.util.XmlUtil;
import com.diverhome.wechat.handler.IHandler;

public class WeChatService {

	private static final Logger logger = LoggerFactory.getLogger(WeChatService.class);

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @param messageDuplicateChecker
	 *            消息重复检查器
	 * @return
	 */
	public static String processRequest(HttpServletRequest request, WxMessageDuplicateChecker messageDuplicateChecker) {
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = XmlUtil.parseXml(request);
			if (isDuplicateMessage(requestMap, messageDuplicateChecker)) {
				// 如果是重复消息，那么就不做处理
				logger.info("重复消息：" + requestMap);
				return respMessage;
			}
			logger.info("微信请求map：" + requestMap);
			AbstractFactory factory = new HandlerFactory();
			IHandler handler = factory.createHandler(requestMap);
			if (handler != null) {
				respMessage = handler.handleRequest();
			}
		} catch (Exception e) {
			logger.info("处理微信请求失败", e);
		}

		return respMessage;
	}

	protected static boolean isDuplicateMessage(Map<String, String> requestMap,
			WxMessageDuplicateChecker messageDuplicateChecker) {

		String messageId = "";
		if (requestMap.get("MsgId") == null) {
			messageId = String.valueOf(requestMap.get("CreateTime")) + "-" + requestMap.get("FromUserName") + "-"
					+ String.valueOf(requestMap.get("EventKey") == null ? "" : requestMap.get("EventKey")) + "-"
					+ String.valueOf(requestMap.get("Event") == null ? "" : requestMap.get("Event"));
		} else {
			messageId = String.valueOf(requestMap.get("MsgId"));
		}

		if (messageDuplicateChecker.isDuplicate(messageId)) {
			return true;
		}
		return false;

	}
}
