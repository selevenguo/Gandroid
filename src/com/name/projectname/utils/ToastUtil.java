package com.name.projectname.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast显示工具类<br>
 * @author selevenguo
 * @date 2014-8-8
 */
public class ToastUtil {
	/**
	 * 自定义toast
	 */
	private static Toast mToast = null;
	private static String mText = "";
	private static long mShowTime = 0;// 显示toast的时间点
	private static long mduration = 0;// 显示时间长度

	/**
	 * 初始化ToastUtil，尽量写入application
	 */
	public static void init(Context context) {
		if (mToast == null)
			mToast = Toast.makeText(context, null, 0);
	}

	public static void showToastShort(String text) {
		showToast(text, Toast.LENGTH_SHORT);
	}

	public static void showToastLong(String text) {
		showToast(text, Toast.LENGTH_LONG);
	}

	private static void showToast(String text, int duration) {
		// 如果传入text不等于null或者即有toast在显示且显示文字也相同则不再进行toast的显示
		if (text == null || System.currentTimeMillis() - mShowTime < mduration
				&& !mText.equals(text))
			return;
		mToast.setText(text);
		mToast.setDuration(duration);
		mToast.show();
		mText = text;
		mShowTime = System.currentTimeMillis();
		mduration = duration;

	}
}
