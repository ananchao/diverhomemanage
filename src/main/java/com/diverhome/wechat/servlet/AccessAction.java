package com.diverhome.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.diverhome.util.SignUtil;
import com.diverhome.wechat.core.WeChatService;
import com.diverhome.wechat.core.WxMessageDuplicateChecker;
import com.diverhome.wechat.core.WxMessageInMemoryDuplicateChecker;

/**
 * 
 * ***************************************************************  
 * <p>Filename:    AccessAction.java
 * <p>Description: 用于接入微信公众平台的接口类
 * <p>
 * <p>Copyright:   Copyright (c)2015  
 * <p>Company:     njits  
 * <p>Create at:   2015年11月26日 上午9:49:27
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2015年11月26日     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
@SuppressWarnings("serial")
public class AccessAction extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(AccessAction.class);

	private WxMessageDuplicateChecker messageDuplicateChecker;

	/**
	 * 接入微信平台校验
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戮
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		// 通过检验 signature 对请求进行校验，若校验成功则原样返回 echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}

		out.close();
		out = null;

	}

	/**
	 * 用户向公众平台发信息并自动返回信息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 响应消息
		PrintWriter out = response.getWriter();

		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戮
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 通过检验 signature 对请求的真实性进行校验
		if (!SignUtil.checkSignature(signature, timestamp, nonce)) {
			logger.info("本条消息不是来自微信服务器，不进行处理");
			return;
		}
		// 调用核心业务类接收消息、处理消息
		String respMessage = WeChatService.processRequest(request, messageDuplicateChecker);
		if (respMessage == null) {
			return;
		}
		out.print(respMessage);
		out.flush();
		out.close();
	}

	/**
	 * 初始化方法
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		this.messageDuplicateChecker = new WxMessageInMemoryDuplicateChecker();
	}

}
