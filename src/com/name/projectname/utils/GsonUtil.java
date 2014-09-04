package com.name.projectname.utils;

import com.google.gson.Gson;

/**
 * gson单例<br>
 * @author selevenguo
 * @date 2014-8-8
 */
public class GsonUtil {
	private static Gson mGson;

	public static Gson getGson() {
		if (mGson == null)
			mGson = new Gson();
		return mGson;
	}
}
