package com.name.projectname.adapter;

import android.view.View;

/**
 * item中的view点击事件
 * @author selevenguo
 * @date 2014-8-20
 */
public interface OnItemChildClickListener<T> {
	public void onItemChildClickListener(View view, T t);
}
