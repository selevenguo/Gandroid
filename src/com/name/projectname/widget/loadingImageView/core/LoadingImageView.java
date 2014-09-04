package com.name.projectname.widget.loadingImageView.core;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 显示下载进度的imageview<br>
 * 
 * @author selevenguo
 * @date 2014-8-22
 */
public class LoadingImageView extends ImageView {
	private IDrawLoading mDrawLoading;
	private AttributeSet mAttributeSet;
	private Context mContext;
	private float progress;

	public LoadingImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		mAttributeSet = attrs;
	}

	public LoadingImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		mAttributeSet = attrs;
	}

	public LoadingImageView(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (isDrawProgress()) {
			mDrawLoading
					.drawProgress(getWidth(), getHeight(), progress, canvas);
		}
	}

	public IDrawLoading getDrawLoading() {
		return mDrawLoading;
	}

	public void setDrawLoading(IDrawLoading mDrawLoading) {
		this.mDrawLoading = mDrawLoading;
		if (this.mDrawLoading != null)
			mDrawLoading.init(mContext, this.mAttributeSet);
	}

	/**
	 * 开始初始化
	 */
	public void drawProgressStart() {
		if (mDrawLoading != null && mDrawLoading.isInit())
			mDrawLoading.init(mContext, mAttributeSet);
	}

	/**
	 * 停止显示进度
	 */
	public void drawProgressOver() {
		invalidate();
	}
	
	public boolean isDrawProgress() {
		return progress >= 0f && progress <= 0.9999f;
	}
	
	public void setProgress(float progress){
		this.progress = progress;
	}

	/**
	 * 设置进度
	 * 
	 */
	public void upatedLoadingProgress(float progress) {
		setProgress(progress);
		if (alowDrawProgress()) {
			if (mDrawLoading.getInvalidateRect() != null)
				invalidate(mDrawLoading.getInvalidateRect());
			else
				invalidate();
		}

	}

	private boolean alowDrawProgress() {
		return mDrawLoading != null && getWidth() != 0 && getHeight() != 0;
	}

}
