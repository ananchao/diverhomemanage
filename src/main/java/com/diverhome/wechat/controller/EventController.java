package com.diverhome.wechat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.diverhome.controller.base.BaseController;
import com.diverhome.dao.DaoSupport;
import com.diverhome.entity.ResponseEntity;
import com.diverhome.util.Constant;
import com.diverhome.util.JavaMail;
import com.diverhome.util.StringUtil;

/**
 * ***************************************************************  
 * <p>Filename:    EventController.java
 * <p>Description: Jessea南京分享会活动
 * <p>
 * <p>Copyright:   Copyright (c)2016  
 * <p>Company:     njits  
 * <p>Create at:   2017年4月3日 上午10:32:17
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2017年4月3日     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
@Controller
@RequestMapping("/rest")
public class EventController extends BaseController {
	
	@Resource(name = "daoSupport") 
	private DaoSupport dao;

	/**
	 * 
	 * @Title: event
	 * @Description: 进入活动页面
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public ModelAndView event() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("event");
		return mv;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @Title: total
	 * @Description: 查看多少人报名
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/eventtotal", method = RequestMethod.GET)
	public ModelAndView eventTotal() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("event_total");
		int total = (int) dao.findForObject("Event.selectTotal", null);
		mv.addObject("total", total);
		return mv;
	}
	
	/**
	 * 
	 * @Title: signUp
	 * @Description: 提交报名
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity signUp(HttpServletRequest request) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			String subject = "有人报名啦";
			String receiveUser = "735863144@qq.com";
			//String receiveUser = "329293772@qq.com";
			String name = StringUtil.nvl(request.getParameter("name"), "");
			String age = StringUtil.nvl(request.getParameter("age"), "");
			String email = StringUtil.nvl(request.getParameter("email"), "");
			String wechat = StringUtil.nvl(request.getParameter("wechat"), "");
			String phone = StringUtil.nvl(request.getParameter("phone"), "");
			String sex = StringUtil.nvl(request.getParameter("sex"), "");
			String qualification = StringUtil.nvl(request.getParameter("qualification"), "");
			String message = "姓名：" + name + "，年龄：" + age + "，邮箱：" + email + "，手机号：" + phone + "，微信号：" + wechat + "，性别："
					+ sex + "，资质：" + qualification;
			JavaMail.sendMails(subject, message, receiveUser);
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("name", name);
			data.put("age", age);
			data.put("email", email);
			data.put("wechat", wechat);
			data.put("phone", phone);
			data.put("sex", sex);
			data.put("qualification", qualification);
			dao.save("Event.insertEventUser", data);
			
			// 设置成功返回信息
			responseEntity.setStatus(Constant.SUCCESS);
		} catch (Exception e) {
			//设置返回信息
			responseEntity.setStatus(Constant.ERROR);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
}
