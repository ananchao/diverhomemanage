package com.diverhome.wechat.handler;

import java.util.Date;
import java.util.Map;

import com.diverhome.util.Constant;
import com.diverhome.util.XmlUtil;
import com.diverhome.wechat.message.resp.TextMessage;

/**
 * 文本消息处理器
 * 
 * @author anchao
 * 
 */
public class TextMessageHandler extends IHandler {
	
	//private static final Logger logger = LoggerFactory.getLogger(TextMessageHandler.class);
	
	public TextMessageHandler(Map<String, String> requestMap) {
		super(requestMap);
	}

	public String handleRequest() {
		// 默认返回的文本消息内容
		//String respContent = "您发送的是文本消息！";
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		// 文本消息内容
		//String content = requestMap.get("Content");

		/*if ("?".equals(content) || "？".equals(content)) {
			// 显示菜单
			//respContent = getMainMenu();
			respContent = "<a href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=http%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123#wechat_redirect'>111</a>";
		} else if ("2".equals(content)) {
			// 百度一下
			respContent = "<a href=\"http://www.baidu.com\">百度一下</a>";
		} else if ("3".equals(content)) {
			// Emoji表情 Unified版需要转码，SoftBank版直接使用Unicode值
			respContent = XmlUtil.emoji(0x1F6B2) + XmlUtil.emoji(0x1F6B9) + "\ue428" + "\ue159";
		} else if ("4".equals(content)) {
			// 多条图文消息
			Articles articles1 = new Articles();
			articles1.setTitle("南京城市智能交通公司-安超");
			articles1.setDescription("");
			articles1.setPicUrl("http://ananchao2.xicp.net/ESPWX/image/test.jpg");
			articles1.setUrl("http://ananchao2.xicp.net/ESPWX/image/test.jpg");
			
			Articles articles2 = new Articles();
			articles2.setTitle("点击进入搜狐");
			articles2.setDescription("");
			articles2.setPicUrl("http://ananchao2.xicp.net/ESPWX/image/meinv.jpg");
			articles2.setUrl("http://demo.open.weixin.qq.com/jssdk");
			List<Articles> list = new ArrayList<Articles>();
			list.add(articles1);
			list.add(articles2);
			
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setFromUserName(toUserName);
			newsMessage.setToUserName(fromUserName);
			newsMessage.setCreateTime(new Date().getTime()/1000);
			newsMessage.setArticleCount(list.size());
			newsMessage.setArticles(list);
			newsMessage.setMsgType(Constant.RESP_MESSAGE_TYPE_NEWS);
			String s = XmlUtil.newsMessageToXml(newsMessage);
			logger.info(s);
			return s;
		} else if (XmlUtil.isQqFace(content)) {
			// 表情符号
			// 用户发什么QQ表情，就返回什么QQ表情  
			respContent = content;
		} else {
			//respContent = content;
		}*/

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
