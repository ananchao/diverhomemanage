package com.diverhome.controller.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diverhome.controller.base.BaseController;
import com.diverhome.entity.PageData;
import com.diverhome.entity.ResponseEntity;
import com.diverhome.entity.TbSystemUser;
import com.diverhome.service.system.MainService;
import com.diverhome.util.Constant;

/**
 * ***************************************************************  
 * <p>Filename:    MainController.java
 * <p>Description: 
 * <p>
 * <p>Copyright:   Copyright (c)2016  
 * <p>Company:     njits  
 * <p>Create at:   2017年4月2日 上午11:16:28
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2017年4月2日     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
@Controller
@RequestMapping("/manage")
public class MainController extends BaseController {
	
	@Autowired
	private MainService mainService; 

	/**
	 * 
	 * @Title: login
	 * @Description: 进入登录页面
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	/**
	 * 
	 * @Title: login_login
	 * @Description: 点击登录
	 * @param attr	存放跳转参数
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/login_login", method = RequestMethod.POST)
	public ModelAndView login_login(RedirectAttributes attr) {
		ModelAndView mv = this.getModelAndView();
		try {
			PageData pd = this.getPageData();
			logger.info("username:" + pd.getString("username"));
			// 根据用户名密码查询用户
			TbSystemUser userByUsnPwd = mainService.getUserByUsnPwd(pd);
			if (userByUsnPwd == null) {
				// 查询不到跳回登录页面，使用redirect方式跳转，用addFlashAttribute传参可以避免参数拼接在url后
				attr.addFlashAttribute("loginError", Constant.EXCEPTION_MSG_LOGIN_FAILD); 
				mv.setViewName("redirect:/manage/login");
			} else {
				// 查询成功跳转至首页
				mv.setViewName("redirect:/manage/index");
				// 设置session
				this.getRequest().getSession().setAttribute(Constant.SESSION_LOGIN_USER, userByUsnPwd);
			}
		} catch (Exception e) {
			// 查询失败跳回登录页面
			attr.addFlashAttribute("loginError", Constant.EXCEPTION_MSG_LOGIN_SYSERROR); 
			mv.setViewName("redirect:/manage/login");
			logger.error(e.getMessage(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * @Title: index
	 * @Description: 跳转至管理系统首页
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	/**
	 * 
	 * @Title: main
	 * @Description: 打开首页
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("main");
		return mv;
	}
	
	/**
	 * 
	 * @Title: modulePage
	 * @Description: 跳转到各个模块
	 * @return
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/modulepage", method = RequestMethod.GET)
	public ModelAndView modulePage() {
		PageData pd = this.getPageData();
		Iterator entries = pd.entrySet().iterator();
		ModelAndView mv = this.getModelAndView();
		mv.setViewName(pd.getString("page"));
		// 将pd中的参数传递下去
		while (entries.hasNext()) {
		    Map.Entry entry = (Map.Entry) entries.next();
		    String key = (String) entry.getKey();
		    Object value = entry.getValue();
		    mv.addObject(key, value);
		}
		return mv;
	}
	
	/**
	 * 
	 * @Title: logout
	 * @Description: 退出系统
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout() {
		ModelAndView mv = this.getModelAndView();
		// 清除session
		this.getRequest().getSession().removeAttribute(Constant.SESSION_LOGIN_USER);
		// 跳转至登录页面
		mv.setViewName("redirect:/manage/index");
		return mv;
	}
	
	/**
	 * 
	 * @Title: getMainData
	 * @Description: 获取首页数据
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getMainData", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity getMainData() {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			HashMap<String, Object> data = mainService.getMainData();
			responseEntity.setStatus(Constant.SUCCESS);
			responseEntity.setData(data);
		} catch (Exception e) {
			//设置返回信息
			responseEntity.setStatus(Constant.BUSINESS_ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_MSG_GETMAINDATA);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
}
