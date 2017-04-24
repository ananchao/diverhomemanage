package com.diverhome.wechat.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.diverhome.util.Constant;
import com.diverhome.util.XmlUtil;
import com.diverhome.wechat.message.resp.Articles;
import com.diverhome.wechat.message.resp.NewsMessage;

/**
 * 
 * ***************************************************************  
 * <p>Filename:    SubscribeEventHandler.java
 * <p>Description: 关注事件处理器
 * <p>
 * <p>Copyright:   Copyright (c)2015  
 * <p>Company:     njits  
 * <p>Create at:   2016年2月29日 上午9:57:51
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2016年2月29日     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
public class SubscribeEventHandler extends IHandler {
	
	public SubscribeEventHandler(Map<String, String> requestMap) {
		super(requestMap);
	}
	
	@Override
	public String handleRequest() {
		// 默认返回的文本消息内容
		//String respContent = "感谢您关注优优学车微信公众号！";
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		// 回复文本消息
		/*TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime()/1000);
		textMessage.setMsgType(Constant.RESP_MESSAGE_TYPE_TEXT);*/

		//textMessage.setContent(respContent);
		//String respMessage = XmlUtil.textMessageToXml(textMessage);
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setCreateTime(new Date().getTime()/1000);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setMsgType(Constant.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setArticleCount(1);
		List<Articles> articles = new ArrayList<Articles>();
		Articles article = new Articles();
		article.setTitle("互联网考驾照是一种怎样的体验？");
		article.setDescription("2016优优学车正式向大家问候啦~");
		article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/dlauia3DnBREnwvgZsvMibqL88r3ahqSBpbjmt8MS4ZW5RH8cXJ5SmqQVmwO7yBKWRn9cn1ibznAw7ibAGHkJiaF7FQ/0?wx_fmt=png");
		article.setUrl("http://mp.weixin.qq.com/s?__biz=MzI0NTEwNzQ3NQ==&mid=402272221&idx=1&sn=0841f7044d55c978f8d7a86584420f15&scene=0&previewkey=aH5iboMSiGMIiKPBa3qtDMNS9bJajjJKzz%2F0By7ITJA%3D#wechat_redirect");
		articles.add(article);
		newsMessage.setArticles(articles);
		return XmlUtil.newsMessageToXml(newsMessage);
	}
	
}
