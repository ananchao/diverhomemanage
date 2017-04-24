package com.diverhome.util;

/**
 * ***************************************************************  
 * <p>Filename:    Constant.java
 * <p>Description: 常量工具类
 * <p>
 * <p>Copyright:   Copyright (c)2016  
 * <p>Company:     njits  
 * <p>Create at:   2017年4月4日 下午9:08:58
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2017年4月4日     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
public class Constant {
	
	public static final String CONFIG_FILENAME = "appconfig.properties";
	
	/** session中的当前登录人名 */
	public static final String SESSION_LOGIN_USER = "login_user";
	/** 处理成功 */
	public static final int SUCCESS = 1;
	/** 系统异常 */
	public static final int ERROR = 0;
	/** 业务异常 */
	public static final int BUSINESS_ERROR = 2;
	/** 获取微信openid跳转 */
	public static final int OPENID_VALID = 3;

	/** 异常信息描述 */
	public static final String EXCEPTION_MSG_LOGIN_FAILD = "用户名或密码错误，请重新输入！";
	public static final String EXCEPTION_MSG_LOGIN_SYSERROR = "登录出现异常！";
	public static final String EXCEPTION_MSG_GETEMPLOYEECOACHLIST = "获取教练信息失败！";
	public static final String EXCEPTION_MSG_UPATEORDERSTATE = "更新订单状态失败！";
	public static final String EXCEPTION_MSG_GETORDERINFO = "获取订单信息失败！";
	public static final String EXCEPTION_MSG_GETMAINDATA = "获取统计数据失败！";
	public static final String EXCEPTION_MSG_GETNOTICELIST = "获取公告列表失败！";
	
	/** 提示信息描述 */
	public static final String INFO_PRODUCT_EDITMESSAGE = "产品修改成功！";
	public static final String INFO_PRODUCT_ADDMESSAGE = "创建成功！";
	public static final String INFO_VIPMEMBER_ADDMESSAGE = "保存成功！";
	public static final String INFO_NOTICE_ADDMESSAGE = "发布成功！";
	
	/** 给微信端的信息描述 */
	public static final String INFO_WECHAT_ORDER_MESSAGE = "下单成功！";
	/** 给微信端的异常信息描述 */
	public static final String EXCEPTION_WECHAT_MESSAGE = "亲，网络发生异常！";
	public static final String EXCEPTION_WECHAT_ORDER_MESSAGE = "您的身份验证有误！";
	
	/** 产品图片存放目录 */
	public static final String PRODUCT_IMG_PATH = "product_img";
	/** 验证码图片存放目录 */
	public static final String PRODUCT_VERIFYCODEIMG_PATH = "verifycode_img";

	///////////////////////////微信相关常量/////////////////////////////
	/** OAuth 转发路径 */
	public static final String OAUTH_FORWARDURL = "/rest/validopenid";
	
	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(菜单拉取消息时的事件推送)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
	/**
	 * 事件类型：VIEW(菜单跳转链接时的事件推送)
	 */
	public static final String EVENT_TYPE_VIEW = "VIEW";
	
	/**
	 * 获取access_token的接口地址（GET） 限200（次/天）
	 */
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/**
	 * 获取jsapi_ticket的接口地址（GET） 限200（次/天）
	 */
	public static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	/**
	 * 菜单创建（POST） 限100（次/天）
	 */
	public static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/**
	 * 微信页面授权返回类型
	 */
	public static final String OAUTH_RESPONSE_TYPE = "code";
	
	/**
	 * 微信页面授权应用授权作用域snsapi_base
	 */
	public static final String OAUTH_SCOPE_SNSAPI_BASE = "snsapi_base";
	
	/**
	 * 微信页面授权应用授权作用域snsapi_userinfo
	 */
	public static final String OAUTH_SCOPE_SNSAPI_USERINFO = "snsapi_userinfo";
	
	/**
	 * 填写为authorization_code
	 */
	public static final String OAUTH_GRANT_TYPE = "authorization_code";
}
