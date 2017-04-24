package com.diverhome.service.system;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.diverhome.dao.DaoSupport;
import com.diverhome.entity.PageData;
import com.diverhome.entity.TbSystemUser;
import com.diverhome.service.base.BaseService;
import com.diverhome.service.membership.MemberShipService;
import com.diverhome.service.order.OrderService;
import com.diverhome.service.product.ProductService;

@Service 
public class MainService extends BaseService {
	
	@Resource(name = "daoSupport") 
	private DaoSupport dao; 
	
	@Autowired
	private MemberShipService memberShipService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	
	/**
	 * 
	 * @Title: getUserByUsnPwd
	 * @Description: 根据用户名密码查询用户
	 * @param pd
	 * @return	TbSystemUser
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public TbSystemUser getUserByUsnPwd(PageData pd) throws Exception {
		return (TbSystemUser) dao.findForObject("TbSystemUserMapper.getUserByUsnPwd", pd);
	}

	/**
	 * 
	 * @Title: getMainData
	 * @Description: 获取首页数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public HashMap<String, Object> getMainData() throws Exception {
		// 存放响应数据的Map
		HashMap<String, Object> data = new HashMap<String, Object>();
		// 获取vip会员总数
		PageData pd = new PageData();
		pd.put("membership_level", "1");
		int vipMemberTotal = memberShipService.getMemberListTotal(pd).size();
		data.put("vipMemberTotal", vipMemberTotal);
		// 获取普通会员总数
		pd.put("membership_level", "0");
		int regularMemberTotal = memberShipService.getMemberListTotal(pd).size();
		// 总会员数=vip+普通
		double memberTotal = Double.parseDouble(vipMemberTotal + regularMemberTotal + "");
		data.put("memberTotal", memberTotal);
		// vip会员占比
		double vipRate = memberTotal == 0 ? 0 : Math.round(vipMemberTotal / memberTotal * 100);
		data.put("vipRate", vipRate);

		pd.clear();
		// 订单总数
		double orderTotal = Double.parseDouble(orderService.getOrderTotal(pd) + "");
		data.put("orderTotal", orderTotal);
		// 已验证的订单数
		pd.put("state", "1");
		int validatedOrderTotal = orderService.getOrderTotal(pd);
		data.put("validatedOrderTotal", validatedOrderTotal);
		// 已验证订单数占比
		double validatedOrderRate = orderTotal == 0 ? 0 : Math.round(validatedOrderTotal / orderTotal * 100);
		data.put("validatedOrderRate", validatedOrderRate);
		
		pd.clear();
		// 男会员的数量
		pd.put("sex", "0");
		int manTotal = memberShipService.getMemberListTotal(pd).size();
		data.put("manTotal", manTotal);
		// 女会员的数量
		pd.put("sex", "1");
		int womanTotal = memberShipService.getMemberListTotal(pd).size();
		data.put("womanTotal", womanTotal);
		// 男会员占比
		double manRate = memberTotal == 0 ? 0 : Math.round(manTotal / memberTotal * 100);
		data.put("manRate", manRate);
		// 女会员占比
		double womanRate = manRate == 0 ? 0 : 100 - manRate;
		data.put("womanRate", womanRate);
		
		pd.clear();
		// 现场报名人数
		pd.put("join_way", "0");
		int siteRegistrationTotal = memberShipService.getMemberListTotal(pd).size();
		data.put("siteRegistrationTotal", siteRegistrationTotal);
		// 微信报名人数
		pd.put("join_way", "1");
		int wechatTotal = memberShipService.getMemberListTotal(pd).size();
		data.put("wechatTotal", wechatTotal);
		// 现场报名人数占比
		double siteRegistrationRate = memberTotal == 0 ? 0 : Math.round(siteRegistrationTotal / memberTotal * 100);
		data.put("siteRegistrationRate", siteRegistrationRate);
		// 微信报名人数占比
		double wechatRate = siteRegistrationRate == 0 ? 0 : 100 - siteRegistrationRate;
		data.put("wechatRate", wechatRate);
		
		pd.clear();
		// 水肺潜水员人数
		pd.put("qualification", "0");
		int scubaDiverTotal = memberShipService.getMemberListTotal(pd).size();
		data.put("scubaDiverTotal", scubaDiverTotal);
		// 水肺潜水员占比
		double scubaDiverRate = memberTotal == 0 ? 0
				: Math.round(scubaDiverTotal / memberTotal * 100);
		data.put("scubaDiverRate", scubaDiverRate);
		// 自由潜水员人数
		pd.put("qualification", "1");
		int freeDiverTotal = memberShipService.getMemberListTotal(pd).size();
		data.put("freeDiverTotal", freeDiverTotal);
		// 自由潜水员占比
		double freeDiverRate = memberTotal == 0 ? 0
				: Math.round(freeDiverTotal / memberTotal * 100);
		data.put("freeDiverRate", freeDiverRate);
		// 非潜水员人数
		pd.put("qualification", "2");
		int notDiverTotal = memberShipService.getMemberListTotal(pd).size();
		data.put("notDiverTotal", notDiverTotal);
		// 非潜水员占比
		double notDiverRate = memberTotal == 0 ? 0 : 100 - freeDiverRate - scubaDiverRate;
		data.put("notDiverRate", notDiverRate);
		
		pd.clear();
		// 男的平均年龄
		pd.put("sex", "0");
		float manAvgAge = memberShipService.getAvgAge(pd);
		data.put("manAvgAge", manAvgAge);
		// 女的平均年龄
		pd.put("sex", "1");
		float womanAvgAge = memberShipService.getAvgAge(pd);
		data.put("womanAvgAge", womanAvgAge);
		// 总平均年龄
		NumberFormat nf = NumberFormat.getNumberInstance();
		// 保留一位小数，四舍五入
		nf.setMaximumFractionDigits(1);
		float avgAge = (manAvgAge + womanAvgAge) / 2;
		data.put("avgAge", nf.format(avgAge));
		
		// 畅销产品Top10
		ArrayList<PageData> bestSellProductList = productService.getBestSellProductList();
		data.put("bestSellProductList", bestSellProductList);
		
		// 进30天订单柱状图
		data.put("orderBarList", orderService.getOrderBarList());
		
		// 会员所在地区分布排行
		data.put("memberAreaList", memberShipService.getMemberAreaList());
		return data;
	}
}