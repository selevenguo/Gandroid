package com.name.projectname.data;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局变量
 * 
 * @author selevenguo
 * @date 2014-8-20
 */
public class DataCenter {
	private static Map<String, Object> mMap;

	/**
	 * 获取界面传递数据用的map对象
	 */
	public static Map getMap() {
		if (mMap == null)
			mMap = new HashMap<String, Object>();
		return mMap;
	}
}
