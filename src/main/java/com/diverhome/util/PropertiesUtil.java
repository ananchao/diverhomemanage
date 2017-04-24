package com.diverhome.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {
	
	protected final static Logger logger = Logger.getLogger(Properties.class);

	/**
	 * 获取classes下的properties文件
	 * @param name
	 * @return
	 */
	public static Properties getProperties(String name) {
		InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(name);
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return p;
	}
	
}
