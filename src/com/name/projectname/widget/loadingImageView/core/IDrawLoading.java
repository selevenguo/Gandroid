package com.name.projectname.widget.loadingImageView.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;

/**
 * 具体显示方式<br>
 * 
 * @author selevenguo
 * @date 2014-8-22
 */
public interface IDrawLoading {
	public boolean isInit();
	
	public void init(Context context, AttributeSet attrs);

	/**
	 * 如何绘制
	 * 
	 * @param width
	 *            控件宽
	 * @param height
	 *            控件高
	 * @param progress
	 *            进度
	 * @param attrs
	 *            属性
	 */
	public void drawProgress(int width, int height, float progress,Canvas canvas);

	/**
	 * 获取刷新界面的区域，节省资源
	 * 
	 * @return
	 */
	public Rect getInvalidateRect();
}
