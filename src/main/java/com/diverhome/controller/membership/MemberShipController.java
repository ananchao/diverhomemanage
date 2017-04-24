package com.diverhome.controller.membership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.diverhome.controller.base.BaseController;
import com.diverhome.entity.PageData;
import com.diverhome.entity.PaginationEntity;
import com.diverhome.service.membership.MemberShipService;
import com.diverhome.util.Constant;

/**
 * ***************************************************************  
 * <p>Filename:    MemberShipController.java
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
public class MemberShipController extends BaseController {
	
	@Autowired
	private MemberShipService memberShipService;
	
	/**
	 * 
	 * @Title: getVipMemberList
	 * @Description: 获取vip会员列表
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getVipMemberList", method = RequestMethod.POST)
	@ResponseBody
	public PaginationEntity getVipMemberList() {
		PaginationEntity paginationEntity = null;
		try {
			PageData pd = this.getPageData();
			// 获取vip会员分页数据
			paginationEntity = memberShipService.getVipMemberList(pd);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return paginationEntity; 
	}
	
	/**
	 * 
	 * @Title: getRegularMemberList
	 * @Description: 获取普通会员列表
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getRegularMemberList", method = RequestMethod.POST)
	@ResponseBody
	public PaginationEntity getRegularMemberList() {
		PaginationEntity paginationEntity = null;
		try {
			PageData pd = this.getPageData();
			// 获取普通会员分页数据
			paginationEntity = memberShipService.getRegularMemberList(pd);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return paginationEntity; 
	}
	
	/**
	 * 
	 * @Title: addVipMember
	 * @Description: 新增vip会员
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/addVipMember", method = RequestMethod.POST)
	public ModelAndView addVipMember() throws Exception {
		PageData pd = this.getPageData();
		// 新增
		memberShipService.addVipMember(pd);
		ModelAndView mv = this.getModelAndView();
		// 传递保存成功提示信息
		mv.addObject("addMessage", Constant.INFO_VIPMEMBER_ADDMESSAGE);
		// 跳转回新增页面
		mv.setViewName("membership/vip_memberAdd");
		return mv;
	}
}
