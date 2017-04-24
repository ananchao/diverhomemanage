package com.diverhome.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.diverhome.util.StringUtil;

import net.sf.json.JSONObject;

@SuppressWarnings("rawtypes")
public class PageData extends HashMap implements Map, Serializable {

	private static final long serialVersionUID = 1L;

	Map map = null;
	HttpServletRequest request;

	@SuppressWarnings("unchecked")
	public PageData(HttpServletRequest request) {
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value += values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
			value = "";
		}

		map = returnMap;
	}

	@SuppressWarnings("unchecked")
	public PageData(HttpServletRequest request, String type) {
		if (type.equalsIgnoreCase("json")) {
			this.request = request;
			Map returnMap = new HashMap();

			BufferedReader reader = null;
			try {
				reader = request.getReader();
				String str = reader.readLine();

				// System.out.println("getReader = " + str +"
				// length="+contentLength );

				// 解析str
				JSONObject jsonObject = JSONObject.fromObject(str);
				Iterator<String> iterator = jsonObject.keys();
				while (iterator.hasNext()) {
					String key = iterator.next();
					String value = jsonObject.getString(key);
					returnMap.put(key, value);
				}
				map = returnMap;
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("json解析失败，请检查！");
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new RuntimeException("PageData构造方法调用错误！");
		}
	}

	public PageData() {
		map = new HashMap();
	}

	@Override
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}

	public Date getDate(Object key) {
		return (Date) get(key);
	}

	public Timestamp getTimestamp(Object key) {
		return (Timestamp) get(key);
	}

	public String getString(Object key) {
		return StringUtil.nvl((String) get(key), "");
	}

	public Integer getInt(Object key) {
		return (Integer) get(key);
	}

	public int getBigDecimal(Object key) {
		return ((BigDecimal) get(key)).intValue();
	}

	public Float getFloat(Object key) {
		return (Float) get(key);
	}

	public Double getDouble(Object key) {
		return (Double) get(key);
	}

	public String getToString(Object key) {
		Object source = get(key);
		return source == null ? "" : get(key).toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	public int getToInt(Object key) {
		return Integer.valueOf((String) get(key));
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public Set entrySet() {
		return map.entrySet();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Set keySet() {
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		map.putAll(t);
	}

	public int size() {
		return map.size();
	}

	public Collection values() {
		return map.values();
	}

	@SuppressWarnings("unchecked")
	public String toString() {
		String result = "{";
		if (map != null && map.size() > 0) {
			Iterator<String> iter = map.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				result += key + ":" + map.get(key) + ",";
			}
			result = result.substring(0, result.length() - 1);
		}
		result += "}";
		return result;
	}

}
