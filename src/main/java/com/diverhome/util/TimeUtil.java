package com.diverhome.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.LoggerFactory;

/**
 * 用于时间的各种操作
 */
public class TimeUtil {
	/**
	 * 默认构造器
	 */
	public TimeUtil() {
	}

	/**
	 * 获得系统当前时间串,时间格式为yyyyMMddHHmmss [20041120203020]
	 * 
	 * @return String
	 */
	public static String getCurrentTime() {
		java.util.Date date = new java.util.Date();
		return date2String(date, "yyyyMMddHHmmss");
	}

	/**
	 * 使用制定的格式获取当前的系统时间串
	 * 
	 * @param format String 制定的时间格式
	 * @return String 返回的时间
	 */
	public static String getCurrentTime(String format) {
		java.util.Date date = new java.util.Date();
		return date2String(date, format);
	}

	/**
	 * 取昨天的日期，格式YYYYMMDD
	 * <p>
	 * Create/Modify at: 2009-3-24 下午04:35:40
	 * 
	 * @return String 昨天
	 */
	public static String getYesterday() {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		long today = calendar.getTimeInMillis();
		java.util.Date date = new java.util.Date(today);
		String yesterday = dateFormater.format(date);
		return yesterday;
	}

	/**
	 * 取今天的日期，格式YYYYMMDD
	 * <p>
	 * Create/Modify at: 2009-3-24 下午04:35:40
	 * 
	 * @return String 今天
	 */
	public static String getToday() {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMdd");
		java.util.Date date = new java.util.Date();
		return dateFormater.format(date);
	}

	/**
	 * 把时间串按照响应的格式转换成日期对象
	 * 
	 * @param dateStr 时间串
	 * @param format 指定的格式
	 * @return 返回java.util.Date的对象,转换失败时返回当前的时间对象
	 */
	public static java.util.Date string2Date(String dateStr, String format) throws Exception {
		if (dateStr == null || format == null) {
			{
				throw new Exception("日期转换失败:dateStr or format is null");
			}

		}
		try {
			SimpleDateFormat dateFormater = new SimpleDateFormat(format);
			return dateFormater.parse(dateStr);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 根据时间字符串长度，自动判断日期格式转换成日期对象
	 * 
	 * @param dateStr 时间串
	 * @return 返回java.util.Date的对象,转换失败时返回当前的时间对象
	 */
	public static java.util.Date string2Date(String str) throws Exception {
		if (str == null)
			return null;
		String format = null;
		
		if (str.length() == 19) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else if (str.length() == 14) {
			format = "yyyyMMddHHmmss";
		} else if (str.length() == 12) {
			format = "yyyyMMddHHmm";
		} else if (str.length() == 10) {
			format = "yyyyMMddHH";
		} else if (str.length() == 8) {
			format = "yyyyMMdd";
		} else if (str.length() == 6) {
			format = "yyyyMM";
		} else if (str.length() == 4) {
			format = "yyyy";
		} else {
			throw new Exception("时间格式错误:" + str);
		}

		return string2Date(str,format);
	}
	 
	/**
	 * 使用指定的格式格式当前的日期对象
	 * 
	 * @param obj Date 要格式化的时间对象 为空时返回但前时间
	 * @param format String 指定的格式
	 * @return String 返回的时间串
	 */
	public static String date2String(java.util.Date obj, String format) {
		if (obj == null) {
			obj = new java.util.Date();
		} // yyyyMMddHHmmss
		SimpleDateFormat dateFormater = new SimpleDateFormat(format);
		return dateFormater.format(obj);
	}

	/**
	 * 把util的Date类型转换成java.sql.Timestam
	 * 
	 * @param date Date
	 * @return Timestamp
	 */
	public static Timestamp utilDate2Timestamp(java.util.Date date) {
		if (date == null) {
			date = new java.util.Date();
		}
		return new java.sql.Timestamp(date.getTime());
	}

	/**
	 * 得到当前系统时间戳
	 * 
	 * @return java.sql.Timestamp
	 */
	public static Timestamp getCurrentTimestamp() {
		return new java.sql.Timestamp(new java.util.Date().getTime());
	}

	/**
	 * 得到当前时间的java.sql.Date对象
	 * 
	 * @return
	 */
	public static java.sql.Date getCurrentSQLDate() {
		return new java.sql.Date(new java.util.Date().getTime());
	}

	/**
	 * 将指定的util时间对象转换成java.sql.Date
	 * 
	 * @param date Date
	 * @return Date
	 */
	public static java.sql.Date utilDate2SQLDate(java.util.Date date) {
		if (date == null) {
			return getCurrentSQLDate();
		}
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 将字符串的时间值转换成java.sql.Timestamp类型
	 * 
	 * @param dateStr String 如果dateStr长度为8转换yyyyMMdd,如果长度为14转换为yyyyMMddHHmmss
	 * @return Date
	 */
	public static java.sql.Timestamp string2SQLDate(String dateStr) throws Exception {

		String format = null;
		if (dateStr.length() == 8) {
			format = "yyyyMMdd";
		} else if (dateStr.length() == 14) {
			format = "yyyyMMddHHmmss";
		}

		return string2SQLDate(dateStr, format);
	}

	/**
	 * 将字符串的时间值按照指定的格式转换成java.sql.Timestamp类型
	 * 
	 * @param dateStr String
	 * @param format String
	 * @return Timestamp
	 * @throws Exception
	 */
	public static java.sql.Timestamp string2SQLDate(String dateStr, String format) throws Exception {
		if (dateStr == null) {
			throw new Exception("日期转换失败:dateStr is null");
		}
		java.util.Date date = null;
		java.sql.Timestamp timestamp = null;
		try {
			date = string2Date(dateStr, format);
			timestamp = utilDate2Timestamp(date);
		} catch (Exception ex) {
			throw ex;
		}
		return timestamp;
	}

	/**
	 * 
	 * @Title: convertDateForm
	 * @Description: sdfsdfdsfdsf
	 * @param @param dateStr
	 * @param @param origFormat
	 * @param @param targetFormat
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @throws
	 */
	public static String convertDateForm(String dateStr, String origFormat, String targetFormat) throws Exception {
		SimpleDateFormat origFormater = new SimpleDateFormat(origFormat);
		origFormater.setLenient(true);
		Date date = origFormater.parse(dateStr);
		SimpleDateFormat targetFormater = new SimpleDateFormat(targetFormat);
		return targetFormater.format(date);
	}

	// private static final String sqlStatement = SqlManager.getSqlStmtDirect("dual", "TimeProcessor-sqlStatement");

	/**
	 * 根据输入的时间获得星期几，返回汉字 一、二、三……
	 * <p>Create/Modify at: 2010-10-22 下午06:21:40
	 * @param dateStr String 时间格式：yyyyMMdd
	 * @return String 一、二、三、四、五、六、日，异常返回""
	 * @author              wanglj
	 */
	@SuppressWarnings("static-access")
	public static String getDayOfWeek(String dateStr){
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(TimeUtil.string2Date(dateStr));
			int dayOfWeek = calendar.get(calendar.DAY_OF_WEEK);
			switch (dayOfWeek){
			case 1:
				return "日";
			case 2:
				return "一";
			case 3:
				return "二";
			case 4:
				return "三";
			case 5:
				return "四";
			case 6:
				return "五";
			case 7:
				return "六";
			}
		} catch (Exception e) {
			LoggerFactory.getLogger(TimeUtil.class).error("根据输入时间获得星期几出错["+dateStr+"]返回\"\"", e);
		}
		return "";
	}
	
}
