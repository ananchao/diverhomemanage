package com.diverhome.service.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.diverhome.dao.DaoSupport;
import com.diverhome.entity.PageData;
import com.diverhome.entity.PaginationEntity;
import com.diverhome.entity.TbMemberShip;
import com.diverhome.entity.TbOrder;
import com.diverhome.service.base.BaseService;
import com.diverhome.service.membership.MemberShipService;
import com.diverhome.util.Constant;
import com.diverhome.util.StringUtil;
import com.diverhome.wechat.exception.WXComponentException;

@Service 
public class OrderService extends BaseService {
	
	@Resource(name = "daoSupport") 
	private DaoSupport dao; 
	@Autowired
	private MemberShipService memberShipService;
	
	/**
	 * 
	 * @Title: getOrderList
	 * @Description: 条件分页查询订单
	 * @return	PaginationEntity
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public PaginationEntity getOrderList(PageData pd) throws Exception {
		// 获取总数
		int total = getOrderTotal(pd);
		ArrayList<TbOrder> list = (ArrayList<TbOrder>) dao.findForList("TbOrderMapper.getOrderList", pd);
		PaginationEntity paginationEntity = new PaginationEntity();
		paginationEntity.setTotal(total);
		paginationEntity.setRows(list);
		return paginationEntity;
	}
	
	/**
	 * 
	 * @Title: getOrderTotal
	 * @Description: 获取订单总数
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public int getOrderTotal(PageData pd) throws Exception {
		int total = (int) dao.findForObject("TbOrderMapper.getOrderTotal", pd);
		return total;
	}

	/**
	 * 
	 * @Title: updateOrderState
	 * @Description: 更新订单状态
	 * @param pd
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,readOnly=false)
	public void updateOrderState(PageData pd) throws Exception {
		// 如果是将状态改为已验证，需要更新验证时间
		if ("1".equals(pd.getString("state"))) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String validate_time =  format.format(new Date());
			pd.put("validate_time", validate_time);
		}
		dao.update("TbOrderMapper.updateOrderState", pd);
	}
	
	/**
	 * 
	 * @Title: getOrderInfo
	 * @Description: 查询订单详情
	 * @param pd
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public TbOrder getOrderInfo(PageData pd) throws Exception {
		return (TbOrder) dao.findForObject("TbOrderMapper.getOrderInfo", pd);
	}
	
	/**
	 * 
	 * @Title: getOrderBarList
	 * @Description: 近30订单
	 * @return	
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Map<String, ArrayList> getOrderBarList() throws Exception {
		ArrayList<PageData> list = (ArrayList<PageData>) dao.findForList("TbOrderMapper.getOrderBarList", null);
		// 存放柱状图x轴天的名称
		ArrayList<String> dateList = new ArrayList<String>();
		// 存放柱状图每天订单数
		ArrayList<Long> countList = new ArrayList<Long>();
		for (PageData pd : list) {
			dateList.add(pd.getString("insert_time"));
			countList.add((Long) pd.get("count"));
		}
		Map<String, ArrayList> map = new HashMap<String, ArrayList>();
		map.put("dateList", dateList);
		map.put("countList", countList);
		return map;
	}

	/**
	 * 
	 * @Title: addWechatOrder
	 * @Description: 微信下单
	 * @param pd
	 * @param membership_level 0 普通会员，1 vip会员
	 * @throws Exception
	 * @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public TbOrder addWechatOrder(PageData pd, String membership_level) throws Exception {
		// 验证member_id
		TbMemberShip memberShip = memberShipService.getMemberById(pd);
		if (memberShip == null) {
			throw new WXComponentException(Constant.EXCEPTION_WECHAT_ORDER_MESSAGE);
		}
		// 生成订单号
		String order_no = StringUtil.getSequence();
		pd.put("order_no", order_no);
		// 生成6位验证码
		int verification_code = StringUtil.generateVerifyCode(6);
		pd.put("verification_code", verification_code);
		pd.put("member_id", memberShip.getId());
		dao.save("TbOrderMapper.addWechatOrder", pd);
		// 普通会员需要更变成vip会员
		if (membership_level.equals("0")) {
			memberShipService.updateMemberInfo(pd);
		}
		// 查询刚生成的订单
		pd.clear();
		pd.put("order_no", order_no);
		return getOrderInfo(pd);
	}

}