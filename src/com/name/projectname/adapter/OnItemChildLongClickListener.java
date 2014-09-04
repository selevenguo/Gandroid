package com.name.projectname.adapter;

import android.view.View;


/**
 * item中的view长点击事件
 * @author selevenguo
 * @date 2014-8-20
 */
public interface OnItemChildLongClickListener<T> {
	public void onItemChildLongClickListener(View view, T t);
}
