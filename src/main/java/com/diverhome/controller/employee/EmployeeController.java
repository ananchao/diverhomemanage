package com.diverhome.controller.employee;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diverhome.controller.base.BaseController;
import com.diverhome.entity.PageData;
import com.diverhome.entity.ResponseEntity;
import com.diverhome.entity.TbEmployee;
import com.diverhome.service.employee.EmployeeCoachService;
import com.diverhome.util.Constant;
import com.diverhome.util.StringUtil;

/**
 * ***************************************************************  
 * <p>Filename:    EmployeeController.java
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
public class EmployeeController extends BaseController {
	
	@Autowired
	private EmployeeCoachService employeeCoachService; 

	/**
	 * 
	 * @Title: getEmployeeList
	 * @Description: 根据类型查询员工信息
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getEmployeeList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity getEmployeeList() {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			PageData pd = this.getPageData();
			// 员工类型 0 潜水教练 1 其他员工
			String type = StringUtil.nvl(pd.getString("type"), "0", 2);
			ArrayList<TbEmployee> employeeList = employeeCoachService.getEmployeeList(type);
			responseEntity.setStatus(Constant.SUCCESS);
			responseEntity.setData(employeeList);
		} catch (Exception e) {
			//设置返回信息
			responseEntity.setStatus(Constant.BUSINESS_ERROR);
			responseEntity.setMessage(Constant.EXCEPTION_MSG_GETEMPLOYEECOACHLIST);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
	}
}
