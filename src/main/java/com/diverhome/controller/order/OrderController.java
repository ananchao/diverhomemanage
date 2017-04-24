package com.diverhome.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diverhome.controller.base.BaseController;
import com.diverhome.entity.PageData;
import com.diverhome.entity.PaginationEntity;
import com.diverhome.entity.ResponseEntity;
import com.diverhome.entity.TbOrder;
import com.diverhome.service.order.OrderService;
import com.diverhome.util.Constant;

/**
 * ***************************************************************  
 * <p>Filename:    OrderController.java
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
public class OrderController extends BaseController {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 
	 * @Title: getOrderList
	 * @Description: 条件分页查询订单
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getOrderList", method = RequestMethod.POST)
	@ResponseBody
	public PaginationEntity getOrderList() {
		PaginationEntity paginationEntity = null;
		try {
			PageData pd = this.getPageData();
			// 获取订单分页数据
			paginationEntity = orderService.getOrderList(pd);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return paginationEntity; 
	}
	
	/**
	 * 
	 * @Title: updateOrderState
	 * @Description: 更新订单状态
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/updateOrderState", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity validateOrder() {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			PageData pd = this.getPageData();
			orderService.updateOrderState(pd);
			responseEntity.setStatus(Constant.SUCCESS);
		} catch (Exception e) {
			//设置返回信息
			responseEntity.setStatus(Constant.BUSINESS_ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_MSG_UPATEORDERSTATE);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
	/**
	 * 
	 * @Title: getOrderInfo
	 * @Description: 查询订单详情
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getOrderInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity getOrderInfo() {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			PageData pd = this.getPageData();
			TbOrder orderInfo = orderService.getOrderInfo(pd);
			responseEntity.setStatus(Constant.SUCCESS);
			responseEntity.setData(orderInfo);
		} catch (Exception e) {
			//设置返回信息
			responseEntity.setStatus(Constant.BUSINESS_ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_MSG_GETORDERINFO);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
}
