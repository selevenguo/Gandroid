package com.name.projectname.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.selevenguo.R;

/**
 * 获取dialog<br>
 * 
 * @author selevenguo
 * @date 2014-8-18
 */
@SuppressLint("NewApi")
public class DialogUtil {
	/**
	 * 設置默認dailog
	 * 
	 * @return
	 */
	public static ProgressDialog createDefaultProgressDialog(Context context) {
		return createProgressDialog(context, 0);
	}

	public static ProgressDialog createProgressDialog(Context context, int themeId) {
		ProgressDialog progressDialog = new ProgressDialog(context,themeId);
		Drawable drawable = context.getResources().getDrawable(
				R.anim.loading_animation);
		progressDialog.setIndeterminateDrawable(drawable);
		progressDialog.setIndeterminate(true);
		return progressDialog;
	}

	/**
	 * 获取title-msg-2button形式dialog
	 * 
	 * @param context
	 * @return
	 */
	public static Builder createDefaultMsgDialog(Context context) {
		return createMsgDialog(context, false, 0);
	}

	public static Builder createMsgDialog(Context context, boolean isCancelable,
			int themeId) {
		Builder builder = new AlertDialog.Builder(context, themeId);
		builder.setCancelable(isCancelable);
		return builder;
	}
}
