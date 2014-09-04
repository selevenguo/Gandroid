package com.name.projectname;

import com.google.gson.Gson;
import com.name.projectname.exception.PutErrorParamsException;
import com.selevenguo.R;

/**
 * activity或fragmen需实现此接口<br>
 * 
 * @author selevenguo
 * @date 2014-8-8
 */
public interface IBase {
	/**
	 * 调用ToastUtil中showToastShort
	 */
	public void showToastShort(String text);

	/**
	 * 调用ToastUtil中showToastLong
	 */
	public void showToastLong(String text);

	/**
	 * 获得唯一gson实例
	 */
	public Gson getGson();

	/**
	 * 打开新的activity
	 */
	public void startAct(Class<?> act);

	/**
	 * 打开新的activity
	 */
	public void startActWithAni(Class<?> act);

	/**
	 * 带flag打开新的activity
	 * 
	 * @param keyAndParams
	 */
	public void startAct(Class<?> act,  int... flags);
	
	/**
	 * 带flag,anim打开新的activity
	 * 
	 * @param keyAndParams
	 */
	public void startActWithAni(Class<?> act,  int... flags);

	/**
	 * 打开新的activity基方法
	 * 
	 * @param keyAndParams
	 */
	public void startAct(Class<?> act, boolean isAnim, int... flags);

	/**
	 * 如需传参数，可在startact前调用此方法
	 * 
	 * @param obj
	 *            保证
	 */
	public void putDataBeforeStartAct(Object... obj);
	
	/**
	 * 关闭activity
	 */
	public void defaultFinish();

	/**
	 * 入场动画
	 */
	public void defaultActIn();

	/**
	 * 出场动画
	 */
	public void defaultActout();

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
	public String formatConvert(String time, String from, String to);

	/**
	 * 将unix时间转换为yy-MM-dd HH:mm:ss时间格式
	 * 
	 * @param time
	 *            时间
	 * @param pattern
	 *            时间格式
	 * @return
	 */
	public String formatUnxiTime(long time);

	/**
	 * 将unix时间转换成指定格式的字符串
	 * 
	 * @param time
	 *            时间
	 * @param pattern
	 *            时间格式
	 * @return
	 */
	public String formatUnxiTime(long time, String pattern);

	/**
	 * 转化为yy-MM-dd HH:mm:ss时间格式
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public String format(long time);

	/**
	 * 转化为指定时间格式
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public String format(long time, String pattern);
}
