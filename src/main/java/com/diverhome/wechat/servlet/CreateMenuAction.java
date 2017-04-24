package com.diverhome.wechat.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.diverhome.util.PropertiesUtil;
import com.diverhome.util.WeiXinUtil;
import com.diverhome.wechat.pojo.Button;
import com.diverhome.wechat.pojo.CommonButton;
import com.diverhome.wechat.pojo.ComplexButton;
import com.diverhome.wechat.pojo.Menu;

/**
 * 
 * ***************************************************************  
 * <p>Filename:    CreateMenuAction.java
 * <p>Description: 菜单创建
 * <p>
 * <p>Copyright:   Copyright (c)2015  
 * <p>Company:     njits  
 * <p>Create at:   2015年11月26日 下午1:45:00
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2015年11月26日     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
public class CreateMenuAction extends HttpServlet {
	
	private static final long serialVersionUID = -7537072557277314402L;
	private static final Logger logger = LoggerFactory.getLogger(CreateMenuAction.class);
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		try {
			WeiXinUtil.createMenu(getMenu(), WeiXinUtil.getCacheAccessToken()); 
		} catch (Exception e) {
			logger.error("create menu error:{}", e);
		}
	}
	
	public Menu getMenu() {
		
		String rootUrl = PropertiesUtil.getProperties("wechatconfig.properties").getProperty("WX_ROOT_URL");
		
		ComplexButton bt1 = new ComplexButton();
		bt1.setName("我是学员");
		
		//主菜单按钮一
		CommonButton bt11 = new CommonButton();
		bt11.setName("报名学车");
		bt11.setType("view");
		bt11.setKey("11");
		bt11.setUrl(rootUrl + "/uuwx/rest/customer/enroll/indexlist.do");
		
		CommonButton bt12 = new CommonButton();
		bt12.setName("报名订单");
		bt12.setType("view");
		bt12.setKey("12");
		bt12.setUrl(rootUrl + "/uuwx/rest/customer/user/mypayform.do");
		
		CommonButton bt13 = new CommonButton();
		bt13.setName("预约考试");
		bt13.setType("view");
		bt13.setKey("13");
		bt13.setUrl("https://nkg.122.gov.cn/m/login");
		
		CommonButton bt14 = new CommonButton();
		bt14.setName("预约教练");
		bt14.setType("view");
		bt14.setKey("14");
		//bt14.setUrl("http://www.njsljy.com:8080");
		bt14.setUrl(rootUrl + "/uuwx/rest/stu/reservation/gotonotice.do");
		
		bt1.setSub_button(new Button[]{bt11, bt12, bt13, bt14});
		
		ComplexButton bt2 = new ComplexButton();
		bt2.setName("我是驾校");
		
		CommonButton bt21 = new CommonButton();
		bt21.setName("驾校认领");
		bt21.setType("view");
		bt21.setKey("21");
		bt21.setUrl(rootUrl + "/uuwx/rest/customer/enrolljx/gotoclaimapplyindex.do");
		
		CommonButton bt22 = new CommonButton();
		bt22.setName("我的学员");
		bt22.setType("view");
		bt22.setKey("22");
		bt22.setUrl(rootUrl + "/uuwx/rest/customer/enrolljx/gotocarschoolorderlist.do");
		
		CommonButton bt23 = new CommonButton();
		bt23.setName("教练工单");
		bt23.setType("view");
		bt23.setKey("23");
		bt23.setUrl(rootUrl + "/uuwx/rest/coach/enrolljx/gotocoachcourse.do");
		
		bt2.setSub_button(new Button[]{bt21, bt22, bt23});
		
		ComplexButton bt3 = new ComplexButton();
		bt3.setName("更多");
		
		CommonButton bt31 = new CommonButton();
		bt31.setName("关于我们");
		bt31.setType("view");
		bt31.setKey("31");
		bt31.setUrl(rootUrl + "/uuwx/jsps/aboutus.jsp");
		
		CommonButton bt32 = new CommonButton();
		bt32.setName("产品简介");
		bt32.setType("view");
		bt32.setKey("32");
		bt32.setUrl(rootUrl + "/uuwx/jsps/productionintruduction.jsp");
		
		CommonButton bt33 = new CommonButton();
		bt33.setName("APP下载");
		bt33.setType("view");
		bt33.setKey("33");
		bt33.setUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.njits.yyxc");
		
		bt3.setSub_button(new Button[]{bt31, bt32, bt33});
		
		/*CommonButton bt22 = new CommonButton();
		bt22.setName("测试");
		bt22.setType("view");
		bt22.setKey("122");
		bt22.setUrl(rootUrl + "/uuwx/jsps/jsapi.jsp");*/
		
		/*//主菜单按钮二
		ComplexButton bt2 = new ComplexButton();
		bt2.setName("我的爱车");
		CommonButton bt21 = new CommonButton();
		bt21.setName("扫一扫");
		bt21.setType("scancode_push");
		bt21.setKey("121");
		
		CommonButton bt22 = new CommonButton();
		bt22.setName("我的车位");
		bt22.setType("view");
		bt22.setKey("122");
		bt22.setUrl(Constant.WX_ROOT_URL + "/ESPWX/wx/ESPWXController?pageFlag=" + Constant.PAGE_FLAG_USERCENTER);*/
		
		//bt2.setSub_button(new Button[]{bt21, bt22});
		//主菜单按钮二
		/*CommonButton bt2 = new CommonButton();
		bt2.setName("产品购买");
		bt2.setType("view");
		bt2.setKey("12");
		bt2.setUrl(Constant.WX_ROOT_URL + "/ESPWX/wx/ESPWXController?pageFlag=" + Constant.PAGE_FLAG_PRODUCT);
		
		//主菜单按钮三
		ComplexButton bt3 = new ComplexButton();
		bt3.setName("超享停");
		
		CommonButton bt31 = new CommonButton();
		bt31.setName("个人中心");
		bt31.setType("view");
		bt31.setKey("131");
		bt31.setUrl(Constant.WX_ROOT_URL + "/ESPWX/wx/ESPWXController?pageFlag=" + Constant.PAGE_FLAG_USERCENTER);
		
		CommonButton bt33 = new CommonButton();
		bt33.setName("app下载");
		bt33.setType("view");
		bt33.setKey("133");
		bt33.setUrl("http://www.baidu.com");
		
		CommonButton bt34 = new CommonButton();
		bt34.setName("联系我们");
		bt34.setType("view");
		bt34.setKey("134");
		bt34.setUrl(Constant.WX_ROOT_URL + "/ESPWX/wx/jsps/about.html");
		
		CommonButton bt35 = new CommonButton();
		bt35.setName("朋友分享");
		bt35.setType("view");
		bt35.setKey("135");
		bt35.setUrl("http://www.baidu.com");
		
		//bt3.setSub_button(new Button[]{bt31, bt32, bt33, bt34, bt35});
		bt3.setSub_button(new Button[]{bt31, bt34});*/
		Menu menu = new Menu();
		menu.setButton(new Button[]{bt1, bt2, bt3});
		return menu;
	}
	
}
