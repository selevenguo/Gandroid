package com.name.projectname.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期转化工具<br>
 * 
 * @author selevenguo
 * @date 2014-8-5
 */
public class DateUtil {

	/**
	 * 根据指定格式的时间获取相应datetime
	 * 
	 * @param time
	 *            已经格式化后的时间字符串
	 * @param from
	 *            原格式
	 * @param to
	 *            新的格式
	 * @return
	 */
	public static String formatConvert(String time, String from, String to) {
		long t = getTimeFromTimeStr(time, from);
		if (t < 0)
			return "error params";
		return format(t, to);
	}

	/**
	 * 根据指定格式的时间获取相应datetime
	 * 
	 * @param time
	 *            已经格式化后的时间字符串
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static long getTimeFromTimeStr(String time, String pattern) {
		Date date = getDateFromTimeStr(time, pattern);
		if (date == null)
			return -1;
		return date.getTime();
	}

	/**
	 * 根据指定格式的时间获取相应date
	 * 
	 * @param time
	 *            已经格式化后的时间字符串
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static Date getDateFromTimeStr(String time, String pattern) {
		if (time == null || time.equals(""))
			return null;
		try {
			return new SimpleDateFormat(pattern).parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将unix时间转换为yy-MM-dd HH:mm:ss时间格式
	 * 
	 * @param time
	 *            时间
	 * @param pattern
	 *            时间格式
	 * @return
	 */
	public static String formatUnxiTime(long time) {
		return format(time, "yy-MM-dd HH:mm:ss", true);
	}

	/**
	 * 将unix时间转换成指定格式的字符串
	 * 
	 * @param time
	 *            时间
	 * @param pattern
	 *            时间格式
	 * @return
	 */
	public static String formatUnxiTime(long time, String pattern) {
		return format(time, pattern, true);
	}

	/**
	 * 转化为yy-MM-dd HH:mm:ss时间格式
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public static String format(long time) {
		return format(time, "yy-MM-dd HH:mm:ss", false);
	}

	/**
	 * 转化为指定时间格式
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public static String format(long time, String pattern) {
		return format(time, pattern, false);
	}

	/**
	 * 将时间转换成指定格式的字符串
	 * 
	 * @param time
	 *            时间
	 * @param pattern
	 *            时间格式
	 * @param isUnixTime
	 *            是否为unix时间戳
	 * @return
	 */
	public static String format(long time, String pattern, boolean isUnixTime) {
		if (time < 0 || pattern == null || pattern.trim().equals("")) {
			return "error params";
		}

		if (isUnixTime)
			time *= 1000;

		return new SimpleDateFormat(pattern).format(new Date(time));

	}

}
