package com.diverhome.wechat.core;

import java.util.Map;

import com.diverhome.wechat.handler.IHandler;

/**
 * 抽象工厂类
 * @author anchao
 *
 */
public abstract class AbstractFactory {
	public abstract IHandler createHandler(Map<String, String> requestMap);
}
