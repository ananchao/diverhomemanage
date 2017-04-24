package com.diverhome.wechat.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.diverhome.controller.base.BaseController;
import com.diverhome.entity.PageData;
import com.diverhome.entity.PaginationEntity;
import com.diverhome.entity.ResponseEntity;
import com.diverhome.entity.TbMemberShip;
import com.diverhome.entity.TbNotice;
import com.diverhome.entity.TbOrder;
import com.diverhome.entity.TbProduct;
import com.diverhome.service.membership.MemberShipService;
import com.diverhome.service.notice.NoticeService;
import com.diverhome.service.order.OrderService;
import com.diverhome.service.product.ProductService;
import com.diverhome.util.Constant;
import com.diverhome.util.StringUtil;
import com.diverhome.util.VerifyCodeUtils;
import com.diverhome.wechat.exception.WXComponentException;
import com.diverhome.wechat.jwt.Jwt;
import com.tencent.WXSdk;
import com.tencent.protocol.oauth2_protocol.OAuth2AccessTokenReqData;
import com.tencent.protocol.oauth2_protocol.OAuth2AccessTokenResData;

/**
 * ***************************************************************  
 * <p>Filename:    WeChatRestController.java
 * <p>Description: 微信服务api
 * <p>
 * <p>Copyright:   Copyright (c)2016  
 * <p>Company:     njits  
 * <p>Create at:   2017年4月13日 上午11:54:39
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2017年4月13日     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
@RestController
@RequestMapping("/rest")
public class WeChatRestController extends BaseController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private MemberShipService memberShipService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private NoticeService noticeService;
	
	/**
	 * 
	 * @Title: getProductList
	 * @Description: 分页获取产品列表
	 * @param category	产品类型
	 * @param startLimit	开始记录行的偏移量
	 * @param pageSize	记录行的最大数目
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getProductList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity getProductList(@RequestParam("category") String category,
			@RequestParam("startLimit") int startLimit, @RequestParam("pageSize") Integer pageSize) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			PageData pd = new PageData();
			// 查询状态为1的已上架
			pd.put("state", "1");
			pd.put("type", "course".equals(category) ? "0" : "1");
			pd.put("startLimit", startLimit);
			pd.put("pageSize", pageSize);
			ArrayList<TbProduct> productList = productService.getProductList(pd);
			responseEntity.setStatus(Constant.SUCCESS);
			responseEntity.setData(productList);
		} catch (Exception e) {
			// 设置返回信息
			responseEntity.setStatus(Constant.ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_WECHAT_MESSAGE);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
	/**
	 * 
	 * @Title: getProductById
	 * @Description: 根据id获取产品
	 * @param id	产品id
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getProductById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity getProductById(@PathVariable("id") int id) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			PageData pd = new PageData();
			pd.put("id", id);
			TbProduct tbProduct = productService.getProductById(pd);
			responseEntity.setStatus(Constant.SUCCESS);
			responseEntity.setData(tbProduct);
		} catch (Exception e) {
			// 设置返回信息
			responseEntity.setStatus(Constant.ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_WECHAT_MESSAGE);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
	/**
	 * 
	 * @Title: getWeChatVerifyCode
	 * @Description: 获取微信登录/注册验证码
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getWeChatVerifyCode", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity getWeChatVerifyCode() {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			int w = 107, h = 44;
			String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
			// 文件在服务器上的绝对路径
			String filePath = System.getProperty("app.root") + Constant.PRODUCT_VERIFYCODEIMG_PATH;
			File file = new File(filePath, verifyCode + ".jpg");
			VerifyCodeUtils.outputImage(w, h, file, verifyCode);
			Map<String, String> data = new HashMap<>();
			// 生成验证码图片的url
			String img_url = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
					.getContextPath() + File.separator + Constant.PRODUCT_VERIFYCODEIMG_PATH + File.separator
					+ verifyCode + ".jpg";
			data.put("img_url", img_url);
			data.put("verifyCode", verifyCode);
			responseEntity.setData(data);
			responseEntity.setStatus(Constant.SUCCESS);
		} catch (Exception e) {
			//设置返回信息
			responseEntity.setStatus(Constant.ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_WECHAT_MESSAGE);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
	/**
	 * 
	 * @Title: wechatLogin
	 * @Description: 微信登录
	 * @param phone 手机号
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/wechatLogin", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity wechatLogin(@RequestParam("phone") String phone) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			PageData pd = new PageData();
			pd.put("phone", phone);
			TbMemberShip memberShip = memberShipService.wechatLogin(pd);
			if (memberShip != null) {
				// 生成token
				Map<String, Object> payload = new HashMap<String, Object>();
				Date date = new Date();
				payload.put("phone", phone);// 手机号
				payload.put("iat", date.getTime());// 生成时间
				// payload.put("ext", date.getTime() + 1000 * 60 * 60);// 过期时间1小时
				payload.put("ext", -1);// -1 表示永远不过期
				String customToken = Jwt.createToken(payload);
				Map<String, Object> data = new HashMap<>();
				// 将customToken和用户member_id传回前端
				data.put("customToken", customToken);
				data.put("member_id", memberShip.getId());
				responseEntity.setData(data);
				responseEntity.setStatus(Constant.SUCCESS);
			} else {
				responseEntity.setStatus(Constant.BUSINESS_ERROR);
			}
		} catch (Exception e) {
			//设置返回信息
			responseEntity.setStatus(Constant.ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_WECHAT_MESSAGE);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
	/**
	 * 
	 * @Title: addWechatOrder
	 * @Description: 微信下单
	 * @param productId 产品id
	 * @param order_price 订单价格
	 * @param member_id 会员id
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/addWechatOrder", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addWechatOrder(@RequestParam("product_id") String product_id,
			@RequestParam("order_price") String order_price, @RequestParam("member_id") String member_id,
			@RequestParam("name") String name, @RequestParam("age") int age, @RequestParam("email") String email,
			@RequestParam("qualification") String qualification, @RequestParam("sex") String sex,
			@RequestParam("area_code") String area_code, @RequestParam("membership_level") String membership_level) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			if (!StringUtil.isValid(membership_level)) {
				throw new WXComponentException(Constant.EXCEPTION_WECHAT_ORDER_MESSAGE);
			}
			PageData pd = new PageData();
			pd.put("product_id", product_id);
			pd.put("order_price", order_price);
			pd.put("member_id", member_id);
			if (membership_level.equals("0")) {
				// 普通会员需要更新信息
				pd.put("name", name);
				pd.put("age", age);
				pd.put("email", email);
				pd.put("qualification", qualification);
				pd.put("sex", sex);
				pd.put("area_code", area_code);
				pd.put("membership_level", "1");
			}
			TbOrder tbOrder = orderService.addWechatOrder(pd, membership_level);
			responseEntity.setStatus(Constant.SUCCESS);
			responseEntity.setMessage(Constant.INFO_WECHAT_ORDER_MESSAGE);
			responseEntity.setData(tbOrder);
		}  catch (WXComponentException e) {
			//设置返回信息
			responseEntity.setStatus(Constant.BUSINESS_ERROR);
			responseEntity.setMessage(e.getMessage());
			logger.info(e.getMessage(), e);
		} catch (Exception e) {
			//设置返回信息
			responseEntity.setStatus(Constant.ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_WECHAT_MESSAGE);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
	/**
	 * 
	 * @Title: getOrderList
	 * @Description: 分页获取我的订单列表
	 * @param member_id 会员id
	 * @param state	订单类型
	 * @param startLimit	开始记录行的偏移量
	 * @param pageSize	记录行的最大数目
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity getOrderList(@RequestParam("member_id") String member_id,
			@RequestParam("state") String state, @RequestParam("startLimit") int startLimit,
			@RequestParam("pageSize") Integer pageSize) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			PageData pd = new PageData();
			// 0：待验证 1：已验证
			pd.put("member_id", member_id);
			pd.put("state", state);
			pd.put("startLimit", startLimit);
			pd.put("pageSize", pageSize);
			PaginationEntity orderList = orderService.getOrderList(pd);
			responseEntity.setStatus(Constant.SUCCESS);
			responseEntity.setData(orderList.getRows());
		} catch (Exception e) {
			// 设置返回信息
			responseEntity.setStatus(Constant.ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_WECHAT_MESSAGE);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
	/**
	 * 
	 * @Title: getOrderInfo
	 * @Description: 根据id获取订单详细信息
	 * @param id	订单id
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getOrderInfo/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity getOrderInfo(@PathVariable("id") int id) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			PageData pd = new PageData();
			pd.put("id", id);
			TbOrder orderInfo = orderService.getOrderInfo(pd);
			responseEntity.setStatus(Constant.SUCCESS);
			responseEntity.setData(orderInfo);
		} catch (Exception e) {
			// 设置返回信息
			responseEntity.setStatus(Constant.ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_WECHAT_MESSAGE);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
	/**
	 * 
	 * @Title: getMemberById
	 * @Description: 根据id获取会员详细信息
	 * @param member_id	用户id
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getMemberById/{member_id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity getMemberById(@PathVariable("member_id") int member_id) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			PageData pd = new PageData();
			pd.put("member_id", member_id);
			TbMemberShip memberShip = memberShipService.getMemberById(pd);
			responseEntity.setStatus(Constant.SUCCESS);
			responseEntity.setData(memberShip);
		} catch (Exception e) {
			// 设置返回信息
			responseEntity.setStatus(Constant.ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_WECHAT_MESSAGE);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
	/**
	 * 
	 * @Title: getNoticeBadge
	 * @Description: 获取用户未读公告数
	 * @param member_id	用户id
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getNoticeBadge/{member_id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity getNoticeBadge(@PathVariable("member_id") int member_id) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			PageData pd = new PageData();
			pd.put("member_id", member_id);
			int badge = noticeService.getNoticeBadge(pd);
			responseEntity.setStatus(Constant.SUCCESS);
			responseEntity.setData(badge);
		} catch (Exception e) {
			// 设置返回信息
			responseEntity.setStatus(Constant.ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_WECHAT_MESSAGE);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
	/**
	 * 
	 * @Title: getNoticeListPage
	 * @Description: 微信用户获取公告列表
	 * @param startLimit	开始记录行的偏移量
	 * @param pageSize	记录行的最大数目
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getNoticeListPage", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity getNoticeListPage(@RequestParam("startLimit") int startLimit,
			@RequestParam("pageSize") Integer pageSize) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			PageData pd = this.getPageData();
			pd.put("startLimit", startLimit);
			pd.put("pageSize", pageSize);
			ArrayList<TbNotice> noticeListPage = noticeService.getNoticeListPage(pd);
			responseEntity.setStatus(Constant.SUCCESS);
			responseEntity.setData(noticeListPage);
		} catch (Exception e) {
			// 设置返回信息
			responseEntity.setStatus(Constant.BUSINESS_ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_MSG_GETNOTICELIST);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
	/**
	 * 
	 * @Title: updateReadNotice
	 * @Description: 更新用户已读公告
	 * @param member_id 会员id
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/updateReadNotice", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity updateReadNotice(@RequestParam("member_id") String member_id) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			PageData pd = this.getPageData();
			pd.put("member_id", member_id);
			noticeService.updateReadNotice(pd);
			responseEntity.setStatus(Constant.SUCCESS);
		} catch (Exception e) {
			// 设置返回信息
			responseEntity.setStatus(Constant.BUSINESS_ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_MSG_GETNOTICELIST);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
	
	/**
	 * 
	 * @Title: validopenid
	 * @Description: 获取微信openid
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/validopenid")
	@ResponseBody
	public ResponseEntity validopenid(@RequestParam("code") String code, @RequestParam("phone") String phone)
			throws Exception {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			if (code == null || code.equals("")) {
				throw new WXComponentException("微信OAuth授权接口没有传递code");
			}
			logger.debug("[ValidOpenIdController]code=" + code);
			
			OAuth2AccessTokenReqData oAuth2AccessTokenReqData = new OAuth2AccessTokenReqData(code, Constant.OAUTH_GRANT_TYPE);
			OAuth2AccessTokenResData oAuth2AccessTokenResData = WXSdk.requestOAuth2AccessTokenService(oAuth2AccessTokenReqData);
			String openid = oAuth2AccessTokenResData.getOpenid();
			
			logger.debug("[ValidOpenIdController]根据code获取用户openid :" + openid);
			if (openid == null || openid.equals("")) {
				throw new WXComponentException("[ValidOpenIdController]获取openid失败");
			}
			// 根据phone和openid更新用户信息
			int member_id = memberShipService.checkMemberShip(openid, phone);
			Map<String, Object> payload = new HashMap<String, Object>();
			Date date = new Date();
			payload.put("phone", phone);// 手机号
			payload.put("iat", date.getTime());// 生成时间
			// payload.put("ext", date.getTime() + 1000 * 60 * 60);// 过期时间1小时
			payload.put("ext", -1);// -1 表示永远不过期
			String customToken = Jwt.createToken(payload);
			Map<String, Object> data = new HashMap<>();
			data.put("member_id", member_id);
			data.put("customToken", customToken);
			responseEntity.setData(data);
			responseEntity.setStatus(Constant.SUCCESS);
		} catch (Exception e) {
			// 设置返回信息
			responseEntity.setStatus(Constant.BUSINESS_ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_WECHAT_MESSAGE);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
}
