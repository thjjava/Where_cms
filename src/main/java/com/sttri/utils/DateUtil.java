package com.sttri.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author thj
 *
 */
public class DateUtil {
	public static String getDatetime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	//将Date格式的日期 转换成以type类型的字符串格式的日期 type可以是yyyy-MM-dd HH:mm:ss或者yyyy-MM-dd等格式
	public static String format(Date date,String type){
		return new SimpleDateFormat(type).format(date);
	}
}
