package com.diverhome.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class JavaMail {
	
	protected final static Logger logger = Logger.getLogger(JavaMail.class);

	private static String protocol = "";
	private static String sslEnable = "";
	private static String mailHost = "";
	private static String mailPort = "";
	private static String mailAuth = "";
	private static String mailPassword = "";
	private static String sender_username = "";
	private static String mailDebug = "";

	private static Properties properties = null;

	/*
	 * 初始化方法
	 */
	static {
		properties = PropertiesUtil.getProperties("appconfig.properties");
		protocol = properties.getProperty("mail.transport.protocol");
		sslEnable = properties.getProperty("mail.smtp.ssl.enable");
		mailHost = properties.getProperty("mail.smtp.host");
		mailPort = properties.getProperty("mail.smtp.port");
		mailAuth = properties.getProperty("mail.smtp.auth");
		mailPassword = properties.getProperty("mail.smtp.password");
		sender_username = properties.getProperty("mail.user");
		mailDebug = properties.getProperty("mail.debug");
	}

	/**
	 * 发送邮件
	 * 
	 * @param subject 邮件主题
	 * @param sendHtml 邮件内容
	 * @param receiveUser 收件人地址
	 */
	public static void sendMails(String subject, String sendHtml, String receiveUser) {
		try {
			Properties properties = new Properties();
			properties.put("mail.transport.protocol", protocol);// 连接协议
			properties.put("mail.smtp.host", mailHost);// 主机名
			properties.put("mail.smtp.port", mailPort);// 端口号
			properties.put("mail.smtp.auth", mailAuth);
			properties.put("mail.smtp.ssl.enable", sslEnable);// 设置是否使用ssl安全连接
															// ---一般都使用
			properties.put("mail.debug", mailDebug);// 设置是否显示debug信息 true
													// 会在控制台显示相关信息
			// 得到回话对象
			Session session = Session.getInstance(properties);
			// 获取邮件对象
			Message message = new MimeMessage(session);
			// 设置发件人邮箱地址
			message.setFrom(new InternetAddress(sender_username));
			// 设置收件人地址
			message.setRecipients(RecipientType.TO, new InternetAddress[] { new InternetAddress(receiveUser) });
			// 设置邮件标题
			message.setSubject(subject);
			// 设置邮件内容
			message.setText(sendHtml);
			// 得到邮差对象
			Transport transport = session.getTransport();
			// 连接自己的邮箱账户
			transport.connect(sender_username, mailPassword);// 密码为刚才得到的授权码
			// 发送邮件
			transport.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		JavaMail.sendMails("北风之神", "圣诞快乐2222", "anming1600@sina.com");
	}
}