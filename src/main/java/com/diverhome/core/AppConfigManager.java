package com.diverhome.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.diverhome.util.PropertiesUtil;

/**
 * 
 * ***************************************************************  
 * <p>Filename:    AppConfigManager.java
 * <p>Description: appconfig.properties配置文件加载器
 * <p>
 * <p>Copyright:   Copyright (c)2015  
 * <p>Company:     njits  
 * <p>Create at:   2015年11月26日 下午2:06:28
 * <p> 
 * <p>Modification History:  
 * <p>Date          Author     Version   Description  
 * <p>------------------------------------------------------------------  
 * <p>2015年11月26日     anchao     1.0       first Version 
 * <p>------------------------------------------------------------------
********************************************************************
 */
public class AppConfigManager {
	/**
     * 日志记录器
     */
    private static final Logger logger = Logger.getLogger(AppConfigManager.class);
    
    /**
     * 单例模式的唯一实例
     */
    private static AppConfigManager SINGLE = new AppConfigManager();
    
	/**
	 * 单例模式获取唯一实例的方法
	 * @return AppConfigManager 返回单例的唯一实例
	 */
    public static synchronized AppConfigManager getInstance() {
        return SINGLE;
    }
    
    /**
     * 配置项的容器.
     */
    private Map<String,String> paramMap = new HashMap<String,String>();
    
    /**
     * 默认构造器
     */
    private AppConfigManager() {
    	loadConfig();
    }
    
    /**
	 * 读取配置,重新读取配置
	 * @return boolean 读取参数是否成功
	 */
	public synchronized boolean loadConfig(){
		boolean succ=true;
		try {
			paramMap=getConfigFromProperties();
		} catch (Exception e) {
			succ=false;
			logger.error("[AppConfigManager] 从wechatconfig.properties加载配置失败", e);
		}
		return succ;
	}
	
	/**
	 * 从wechatconfig.properties获取配置
	 * @return Map 配置信息
	 * @throws Exception
	 */
    @SuppressWarnings("rawtypes")
	private Map<String,String> getConfigFromProperties() throws Exception{
		Map<String,String> tmpMap = new HashMap<String, String>();
		int configCount=0;
		try {
			Properties prop = PropertiesUtil.getProperties("wechatconfig.properties");
			Set keyset = prop.keySet(); 
			for (Object object : keyset) { 
				String propValue= prop.getProperty(object.toString()).toString(); 
				tmpMap.put(object.toString(), propValue);
				configCount++;
			}
		} catch (Exception e) {
			throw new Exception("[AppConfigManager] 查询配置出错",e);
		}
		logger.info("[AppConfigManager] 从数据库加载配置---End---配置数="+configCount);
		return tmpMap;
    }
    
    /**
     * 此方法返回key对应的配置项
     * @param key 配置项名
     * @return 返回基站key对应的配置项.如果查找相应的失败返回null.
     */
    public static String getParamValueDirect(String key) {
        return getInstance().paramMap.get(key);
    }
    
}
