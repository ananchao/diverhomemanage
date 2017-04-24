package com.diverhome.service.notice;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.diverhome.dao.DaoSupport;
import com.diverhome.entity.PageData;
import com.diverhome.entity.TbNotice;
import com.diverhome.service.base.BaseService;

@Service 
public class NoticeService extends BaseService {
	
	@Resource(name = "daoSupport") 
	private DaoSupport dao; 
	

	/**
	 * 
	 * @Title: getNoticeListPage
	 * @Description: 获取公告列表
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public ArrayList<TbNotice> getNoticeListPage(PageData pd) throws Exception {
		ArrayList<TbNotice> list = (ArrayList<TbNotice>) dao.findForList("TbNoticeMapper.getNoticeListPage", pd);
		return list;
	}


	/**
	 * 
	 * @Title: addNotice
	 * @Description: 新增公告
	 * @param pd
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addNotice(PageData pd) throws Exception {
		dao.save("TbNoticeMapper.addNotice", pd);
	}


	/**
	 *  
	 * @Title: getNoticeBadge
	 * @Description: 获取微信用户未读公告数
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int getNoticeBadge(PageData pd) throws Exception {
		// 获取member_id对应的已读公告
		TbNotice tbNotice = (TbNotice) dao.findForObject("TbNoticeMapper.getMemberNoticeId", pd);
		if (tbNotice == null) {
			// 插入新纪录
			try {
				dao.save("TbNoticeMapper.addMemberNotice", pd);
			} catch (Exception e) {
				if(!e.getMessage().contains("Duplicate entry")) {
					throw e;
				}
			}
		}
		int badge = (int) dao.findForObject("TbNoticeMapper.getNoticeBadge", pd);
		return badge;
	}

	/**
	 * 
	 * @Title: updateReadNotice
	 * @Description: 更新用户已读公告
	 * @param pd
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateReadNotice(PageData pd) throws Exception {
		dao.update("TbNoticeMapper.updateReadNotice", pd);
	}

}