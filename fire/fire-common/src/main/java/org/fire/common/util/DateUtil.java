package org.fire.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期相关工具类
 * @author leo
 *
 */
public class DateUtil extends DateUtils {
	public static final String DATETIME_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_DEFAULT = "yyyy-MM-dd";
	public static final String TIME_DEFAULT = "HH:mm:ss";
	public static final String DATETIME_SIMPLE = "yyyyMMddHHmmss";
	public static final String DATETIME_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	public static final String DATE_SIMPLE = "yyyyMMdd";
	public static final String TIME_SIMPLE = "HHmmss";
	public static final String DATETIME_FORMAT = "yyyy/MM/dd HH:mm:ss";

	public static String getCurDateTime(){
		Date date = new Date(System.currentTimeMillis());
		DateFormat format = new SimpleDateFormat(DATETIME_DEFAULT);
		String dateStr = format.format(date);
		return dateStr;
	}

	public static String getCurDateTime(String formatStr){
		Date date = new Date(System.currentTimeMillis());
		DateFormat format = new SimpleDateFormat(formatStr);
		String dateStr = format.format(date);
		return dateStr;
	}
	public static String getCurSimpleDateTime(){
		Date date = new Date(System.currentTimeMillis());
		DateFormat format = new SimpleDateFormat(DATETIME_SIMPLE);
		String dateStr = format.format(date);
		return dateStr;
	}
	public static String getCurSimpleDate(){
		Date date = new Date(System.currentTimeMillis());
		DateFormat format = new SimpleDateFormat(DATE_SIMPLE);
		String dateStr = format.format(date);
		return dateStr;
	}
	public static Date getCurDate(){
		Date date = new Date(System.currentTimeMillis());
		return date;
	}
	
}
