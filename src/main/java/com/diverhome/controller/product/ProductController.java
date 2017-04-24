package com.diverhome.controller.product;

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
import com.diverhome.entity.TbProduct;
import com.diverhome.service.product.ProductService;
import com.diverhome.util.Constant;

/**
 * ***************************************************************  
 * <p>Filename:    ProductController.java
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
public class ProductController extends BaseController {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 
	 * @Title: getProductList
	 * @Description: 查询产品
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getProductList", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity getProductList() {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			PageData pd = this.getPageData();
			ArrayList<TbProduct> productList = productService.getProductList(pd);
			responseEntity.setStatus(Constant.SUCCESS);
			responseEntity.setData(productList);
		} catch (Exception e) {
			//设置返回信息
			responseEntity.setStatus(Constant.BUSINESS_ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_MSG_GETEMPLOYEECOACHLIST);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
	/**
	 * 
	 * @Title: getProductById
	 * @Description: 根据id查询产品
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getProductById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity getProductById() {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			PageData pd = this.getPageData();
			TbProduct product = productService.getProductById(pd);
			responseEntity.setStatus(Constant.SUCCESS);
			responseEntity.setData(product);
		} catch (Exception e) {
			//设置返回信息
			responseEntity.setStatus(Constant.BUSINESS_ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_MSG_GETEMPLOYEECOACHLIST);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
	/**
	 * 
	 * @Title: updateProduct
	 * @Description: 更新产品信息
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public ModelAndView updateProduct() throws Exception {
		PageData pd = this.getPageData();
		// 保存修改
		productService.updateProduct(pd);
		ModelAndView mv = this.getModelAndView();
		mv.addObject("id", pd.getString("id"));
		// 传递修改成功提示信息
		mv.addObject("editMessage", Constant.INFO_PRODUCT_EDITMESSAGE);
		// 修改完成后跳转回修改页面
		mv.setViewName("redirect:/manage/modulepage?page=product/productEdit");
		return mv;
	}
	
	/**
	 * 
	 * @Title: addProduct
	 * @Description: 新增产品
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView addProduct() throws Exception {
		PageData pd = this.getPageData();
		// 保存修改
		productService.addProduct(pd);
		ModelAndView mv = this.getModelAndView();
		// 传递修改成功提示信息
		mv.addObject("addMessage", Constant.INFO_PRODUCT_ADDMESSAGE);
		mv.addObject("type", pd.getString("type"));
		mv.addObject("typeName", pd.getString("typeName"));
		// 跳转回新增页面
		mv.setViewName("redirect:/manage/modulepage?page=product/productAdd");
		return mv;
	}
}
