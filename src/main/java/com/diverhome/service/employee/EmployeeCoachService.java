package com.diverhome.service.employee;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.diverhome.dao.DaoSupport;
import com.diverhome.entity.TbEmployee;
import com.diverhome.service.base.BaseService;

@Service 
public class EmployeeCoachService extends BaseService {
	
	@Resource(name = "daoSupport") 
	private DaoSupport dao; 
	
	/**
	 * 
	 * @Title: getEmployeeList
	 * @Description: 根据类型查询员工信息
	 * @return	TbSystemUser
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public ArrayList<TbEmployee> getEmployeeList(String type) throws Exception {
		return (ArrayList<TbEmployee>) dao.findForList("TbEmployeeMapper.getEmployeeList", type);
	}
}