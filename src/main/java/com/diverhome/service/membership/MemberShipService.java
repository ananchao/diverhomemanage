package com.diverhome.service.membership;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.diverhome.dao.DaoSupport;
import com.diverhome.entity.PageData;
import com.diverhome.entity.PaginationEntity;
import com.diverhome.entity.TbMemberShip;
import com.diverhome.service.base.BaseService;
import com.diverhome.util.StringUtil;

@Service 
public class MemberShipService extends BaseService {
	
	@Resource(name = "daoSupport") 
	private DaoSupport dao; 
	
	/**
	 * 
	 * @Title: getVipMemberList
	 * @Description: 获取vip会员列表
	 * @return	PaginationEntity
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public PaginationEntity getVipMemberList(PageData pd) throws Exception {
		// 获取总数
		int total = getMemberListTotal(pd).size();
		ArrayList<TbMemberShip> list = (ArrayList<TbMemberShip>) dao.findForList("TbMemberShipMapper.getVipMemberList", pd);
		PaginationEntity paginationEntity = new PaginationEntity();
		paginationEntity.setTotal(total);
		paginationEntity.setRows(list);
		return paginationEntity;
	}
	
	/**
	 * 
	 * @Title: getMemberListTotal
	 * @Description: 条件查询会员列表
	 * @param pd
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ArrayList<TbMemberShip> getMemberListTotal(PageData pd) throws Exception {
		return (ArrayList<TbMemberShip>) dao.findForList("TbMemberShipMapper.getMemberListTotal", pd);
	}

	/**
	 * 
	 * @Title: getRegularMemberList
	 * @Description: 获取普通会员列表
	 * @return	PaginationEntity
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public PaginationEntity getRegularMemberList(PageData pd) throws Exception {
		// 获取总数
		int total = getMemberListTotal(pd).size();
		ArrayList<TbMemberShip> list = (ArrayList<TbMemberShip>) dao.findForList("TbMemberShipMapper.getRegularMemberList", pd);
		PaginationEntity paginationEntity = new PaginationEntity();
		paginationEntity.setTotal(total);
		paginationEntity.setRows(list);
		return paginationEntity;
	}
	
	/**
	 * 
	 * @Title: addVipMember
	 * @Description: 新增vip会员
	 * @param pd
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED,readOnly=false)
	public void addVipMember(PageData pd) throws Exception {
		// 会员级别为vip会员
		pd.put("membership_level", 1);
		// 加入方式为现场报名
		pd.put("join_way", 0);
		dao.save("TbMemberShipMapper.addVipMember", pd);
	}
	
	/**
	 * 
	 * @Title: getAvgAge
	 * @Description: 按条件计算平均年龄
	 * @param pd
	 * @return 
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public float getAvgAge(PageData pd) throws Exception {
		return (float) dao.findForObject("TbMemberShipMapper.getAvgAge", pd);
	}
	
	/**
	 * 
	 * @Title: getMemberAreaList
	 * @Description: 会员所在地区分布排行
	 * @return	
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ArrayList<PageData> getMemberAreaList() throws Exception {
		return (ArrayList<PageData>) dao.findForList("TbMemberShipMapper.getMemberAreaList", null);
	}
	
	/**
	 * 
	 * @Title: wechatLogin
	 * @Description: 微信登录
	 * @param pd
	 * @return 
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public TbMemberShip wechatLogin(PageData pd) throws Exception {
		return (TbMemberShip) dao.findForObject("TbMemberShipMapper.wechatLogin", pd);
	}
	
	/**
	 * 
	 * @Title: getMemberById
	 * @Description: 根据id获取会员信息
	 * @param pd
	 * @return 
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public TbMemberShip getMemberById(PageData pd) throws Exception {
		return (TbMemberShip) dao.findForObject("TbMemberShipMapper.getMemberById", pd);
	}
	
	/**
	 * 
	 * @Title: updateMemberInfo
	 * @Description: 更新会员信息
	 * @param pd
	 * @return 
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateMemberInfo(PageData pd) throws Exception {
		dao.update("TbMemberShipMapper.updateMemberInfo", pd);
	}

	/**
	 * 
	 * @Title: checkMemberShip
	 * @Description: 根据phone和openid更新会员信息
	 * @param pd
	 * @return String
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int checkMemberShip(String openid, String phone) throws Exception {
		PageData pd = new PageData();
		pd.put("openid", openid);
		pd.put("phone", phone);
		TbMemberShip memberShip = wechatLogin(pd);
		if (memberShip == null) {
			// 新增会员
			pd.put("membership_level", "0");
			pd.put("join_way", "1");
			dao.save("TbMemberShipMapper.addRegularMember", pd);
			memberShip = wechatLogin(pd);
			return memberShip.getId();
		} else if (!StringUtil.isValid(memberShip.getOpenid())) {
			// 会员没有openid则更新openid
			pd.remove("phone");
			pd.put("member_id", memberShip.getId());
			dao.update("TbMemberShipMapper.updateMemberInfo", pd);
		}
		return memberShip.getId();
	}
	
}