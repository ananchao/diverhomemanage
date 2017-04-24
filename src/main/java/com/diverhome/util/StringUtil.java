package com.diverhome.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtil {
	
	/**
	 * 字符串是否有效(null,""时无效)
	 * @param str String 字符串
	 * @return boolean 是否有效
	 */
    public static boolean isValid(String str){
    	if(str==null||"".equals(str)||"null".equals(str))
    		return false;
    	else
    		return true; 
    }
    
	/**
	 * 当字符串为空时,返回defaultValue,否则返回原字符串
	 * @param str 原字符串 
	 * @param defaultValue 被替换的字符
	 * @return String
	 */
    public static String nvl(String str, String defaultValue) {
        if (str==null){
        	str = defaultValue;
        }
        return str;
    }
    
	/**
	 * 当字符串为空时,返回defaultValue,否则返回原字符串
	 * @param str 原字符串 
	 * @param defaultValue 被替换的字符
	 * @param level 0-null,1-null&"",2-null&""&trim""
	 * @return String
	 */
    public static String nvl(String str, String defaultValue ,int level) {
        if (str==null){
        	str = defaultValue;
        }else if(level==1&&"".equals(str)){
        	str = defaultValue;
        }else if(level==2&&("".equals(str)||"".equals(str.trim()))){
        	str = defaultValue;
        }
        return str;
    }
    
    /**
     * 判断两个字符串是否相等
     * @param str1 字符串1
     * @param str2 字符串2
     * @return boolean 是否相等
     */
    public static boolean equals(String str1,String str2){
    	if(str1==null&&str2==null||str1!=null&&str1.equals(str2)) return true;
    	else return false;
    }
    
    /**
     * 判断两个字符串trim是否相等
     * @param str1 字符串1
     * @param str2 字符串2
     * @return boolean 是否相等
     */
    public static boolean equalsWithTrim(String str1,String str2){
    	if(str1==null&&str2==null||str1!=null&&str1.trim().equals(str2.trim())) return true;
    	else return false;
    }
    
    /**
     * 按字节长度得到子字符串(不允许半个字符)
     * @param str 原字符串
     * @param length 字节长度
     * @return String 子字符串
     */
    public static String subStringByByteLength(String str,int length){
    	return subStringByByteLength(str,length,false,true);
    }
    
    /**
     * 按字节长度得到子字符串
     * @param str 原字符串
     * @param length 字节长度
     * @param allowHalf 是否允许半个字符
     * @param fromHead 是否从头开始截取,true从头部,false从尾部
     * @return String 子字符串
     */
	public static String subStringByByteLength(String str,int length,boolean allowHalf,boolean fromHead){
    	if(str==null||length<0||str.getBytes().length<=length) return str;
    	StringBuffer sb=new StringBuffer();
    	byte[] strByte=str.getBytes();
    	if(fromHead){
    		sb.append(new String(strByte,0,length));
        	if(!allowHalf){
    	    	int endPos=Math.min(sb.length()-1,str.length()-1);
    	    	while(endPos>=0&&sb.charAt(endPos)!=str.charAt(endPos)){
    	    		sb.deleteCharAt(endPos);
    	    		endPos--;
    	    	}
        	}
    	}else{
    		sb.append(new String(strByte,strByte.length-length,length));
        	if(!allowHalf){
    	    	int minLength=Math.min(sb.length(),str.length());
    	    	while(minLength>=1&&sb.charAt(sb.length()-minLength)!=str.charAt(str.length()-minLength)){
    	    		sb.deleteCharAt(sb.length()-minLength);
    	    		minLength--;
    	    	}
        	}
    	}
    	return sb.toString();
	}
    
    /**
     * 制造重复字符串
     * @param dupStr 需要重复的字符串
     * @param splitStr 分隔符
     * @param dupTime 重复次数
     * @param needEnd 是否需要分隔符结束
     * @return String 重复字符串
     */
    public static String productDupString(String dupStr,String splitStr,int dupTime,boolean needEnd){
		if(dupTime<1) return "";
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<dupTime;i++){
			sb.append(dupStr);
			if(!needEnd&&i!=dupTime-1) sb.append(splitStr);
		}
		return sb.toString();
	}
    
	/**
	 * 格式化字符串,添加空格
	 * @param str 原字符串
	 * @param totalLen 结果总长
	 * @param isappend 是否附加,true:加在后面,false:加在前面
	 * @return String 格式化的字符串
	 */
    public static String addBlank(String str, int totalLen,boolean isappend){
    	return addSingleStr(str," ",totalLen,isappend);
    }
    
	/**
	 * 格式化字符串,添加单字符
	 * @param str 原字符串
	 * @param sstr 单字符串
	 * @param totalLen 结果总长
	 * @param isappend 是否附加,true:加在后面,false:加在前面
	 * @return String 格式化的字符串
	 */
    public static String addSingleStr(String str,String sstr, int totalLen,boolean isappend){
    	if(str==null) str="null";
    	if(sstr==null||sstr.length()!=1) return str;
        int len = str.length();
        StringBuffer sb = new StringBuffer();
        if(!isappend) sb.append(str);
        for (int i = 0; i <totalLen-len; i++){
            sb.append(sstr);
        }
        if(isappend) sb.append(str);
        return sb.toString();
    }
    
    /**
     * 通过标志获取xml中的第一个字符串
     * @param xml xml字符串
     * @param flag 标志
     * @return String 获取到的字符串值,非null
     */
	public static String getFirstXmlStrByFlag(String xml,String flag) {
		if(!isValid(xml)||!isValid(flag)) return "";
		String beginFlag=spellXmlName(flag,true);
		String endFlag=spellXmlName(flag,false);
		String retStr="";
		int beginPos=xml.indexOf(beginFlag)+beginFlag.length();
		int endPos=xml.indexOf(endFlag);
		if(beginPos>-1&&endPos>=beginPos){
			retStr=xml.substring(beginPos,endPos);
		}
		return retStr;
	}
	
	/**
	 * 通过标志设置xml中的第一个字符串
	 * @param xml xml字符串
	 * @param flag 标志
	 * @param str 字符串值
	 * @return String 设置入字符串值的xml
	 */
	public static String setFirstXmlStrByFlag(String xml,String flag,String str){
		if(!isValid(xml)||!isValid(flag)) return xml;
		String startFlag=spellXmlName(flag,true);
		String endFlag=spellXmlName(flag,false);
		int startPos=xml.indexOf(startFlag)+startFlag.length();
		int endPos=xml.indexOf(endFlag);
		if(startPos>-1&&endPos>=startPos){
			xml=new StringBuffer().append(xml.substring(0,startPos)).append(str).append(xml.substring(endPos)).toString();
		}
		return xml;
	}
	
	/**
	 * 拼写xml名
	 * @param flag 标志
	 * @param isBegin 是否是开始节点
	 * @return String xml名
	 */
	public static String spellXmlName(String flag,boolean isBegin){
		flag=nvl(flag,"");
		StringBuffer sb=new StringBuffer();
		if(isBegin) sb.append("<").append(flag).append(">");
		else sb.append("</").append(flag).append(">");
		return sb.toString();
	}
	
	/**
	 * decode函数,参数数目为0时返回null,参数数目小于3时返回第一个参数,默认返回null,其余同oracle中decode
	 * @param strs 字符串
	 * @return 
	 */
	public static String decode(String... strs){
		if(strs==null||strs.length==0) return null;
		if(strs.length<3) return strs[0];
		String sourceStr=strs[0];
		String retStr=null;
		if(strs.length%2==0){
			retStr=strs[strs.length-1];
		}
		for(int i=1;i+2<=strs.length;i=i+2){
			if(StringUtil.equals(sourceStr, strs[i])){
				retStr=strs[i+1];
				break;
			}
		}
		return retStr;
	}
	
	/**
	 * 通过正则表达式获取第一个匹配的字符串
	 * @param str 原字符串
	 * @param regex 正则表达式
	 * @return 匹配到的字符串
	 */
	public static String getFirstStrByregex(String str,String regex){
		if(!StringUtil.isValid(str)||!StringUtil.isValid(regex)) return str;
		String retStr=null;
		Matcher matcher=Pattern.compile(regex).matcher(str);
		if(matcher.find()){
			retStr=matcher.group();
		}
		return retStr;
	}
	
	/**
	 * 获取流水号
	 * @return
	 */
	public synchronized static String getSequence() {
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
		String id = TimeUtil.getCurrentTime("yyMMddHHmmssSSS") + rannum;
		return id;
	}
	
	/**
	 * 
	 * @Title: generateVerifyCode
	 * @Description: 生成指定位数的随机整数
	 * @param len 长度
	 * @throws
	 */
	public synchronized static int generateVerifyCode(int len) {
		String str = "123456789";
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(new Random().nextInt(str.length()));
			sb.append(ch);
		}
		return Integer.parseInt(sb.toString());
	}
}
