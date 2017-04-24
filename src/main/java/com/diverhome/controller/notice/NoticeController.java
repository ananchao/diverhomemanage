package com.diverhome.controller.notice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.diverhome.controller.base.BaseController;
import com.diverhome.entity.PageData;
import com.diverhome.entity.ResponseEntity;
import com.diverhome.entity.TbNotice;
import com.diverhome.service.notice.NoticeService;
import com.diverhome.util.Constant;

/**
 * ***************************************************************  
 * <p>Filename:    NoticeController.java
 * <p>Description: 
 * <p>
 * <p>Copyright:   Copyright (c)2016  
 * <p>Company:     njits  
 * <p>Create at:   2017年4月4日 下午8:28:56
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2017年4月4日     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
@Controller
@RequestMapping("/manage")
public class NoticeController extends BaseController {
	
	@Autowired
	private NoticeService noticeService;
	
	/**
	 * 
	 * @Title: getNoticeListPage
	 * @Description: 获取公告列表
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getNoticeListPage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity getNoticeListPage() {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			PageData pd = this.getPageData();
			ArrayList<TbNotice> noticeListPage = noticeService.getNoticeListPage(pd);
			responseEntity.setStatus(Constant.SUCCESS);
			responseEntity.setData(noticeListPage);
		} catch (Exception e) {
			//设置返回信息
			responseEntity.setStatus(Constant.BUSINESS_ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_MSG_GETNOTICELIST);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
	/**
	 * 
	 * @Title: addNotice
	 * @Description: 新增公告
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/addNotice", method = RequestMethod.POST)
	public ModelAndView addNotice() throws Exception {
		PageData pd = this.getPageData();
		// 保存修改
		noticeService.addNotice(pd);
		ModelAndView mv = this.getModelAndView();
		// 传递修改成功提示信息
		mv.addObject("addMessage", Constant.INFO_NOTICE_ADDMESSAGE);
		// 跳转回新增页面
		mv.setViewName("notice/noticeAdd");
		return mv;
	}
}
