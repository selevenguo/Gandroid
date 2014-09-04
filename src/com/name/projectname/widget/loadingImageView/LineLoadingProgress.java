package com.name.projectname.widget.loadingImageView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;

import com.name.projectname.widget.loadingImageView.core.IDrawLoading;
import com.selevenguo.R;

/**
 * 线性的实现类<br>
 * 
 * @author selevenguo
 * @date 2014-8-22
 */
public class LineLoadingProgress implements IDrawLoading {
	private int mProgressColor = Color.WHITE;
	private int mProgressStorkeColor = Color.BLACK;
	private int mProgressWidth = 8;
	private int mProgressStorkeWidth = 1;
	private int mProgressLength = 100;
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	private int mOrientation;
	private Rect mRect;
	private Paint paint;
	private boolean isInit=false;

	public LineLoadingProgress() {
		mOrientation = HORIZONTAL;
	}

	public LineLoadingProgress(int orientation) {
		mOrientation = orientation;
	}

	public int getOrientation() {
		return mOrientation;
	}

	public void setOrientation(int mOrientation) {
		this.mOrientation = mOrientation;
	}

	@Override
	public void init(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.loadingImageView);
		mProgressColor = a.getColor(R.styleable.loadingImageView_progressColor,
				Color.WHITE);
		mProgressStorkeColor = a.getColor(
				R.styleable.loadingImageView_progressColor, Color.BLACK);
		mProgressWidth = a.getInteger(
				R.styleable.loadingImageView_progressWidth, 8);
		mProgressStorkeWidth = a.getInteger(
				R.styleable.loadingImageView_progressStorkeWidth, 1);
		mProgressLength = a.getInteger(
				R.styleable.loadingImageView_progresslength, 100);

		paint = new Paint();
		paint.setStyle(Style.FILL);
		isInit=true;
	}
	
	@Override
	public boolean isInit() {
		return isInit;
	}

	@Override
	public void drawProgress(int width, int height, float progress,
			Canvas canvas) {
		initRect(width, height);
		paint.setColor(mProgressStorkeColor);
		canvas.drawRect(mRect, paint);
		if (mOrientation == VERTICAL) {
			paint.setColor(mProgressColor);
			canvas.drawRect(width / 2 - mProgressWidth / 2, height / 2
					- mProgressLength / 2, width / 2 + mProgressWidth / 2,
					height / 2 - mProgressLength / 2 + mProgressLength
							* progress, paint);
		} else {
			paint.setColor(mProgressColor);
			canvas.drawRect(width / 2 - mProgressLength / 2, height / 2
					- mProgressWidth / 2, width / 2 - mProgressLength / 2
					+ mProgressLength * progress, height / 2 + mProgressWidth
					/ 2, paint);
		}
	}

	private void initRect(int width, int height) {
		if (mOrientation == VERTICAL) {
			if (mRect == null)
				mRect = new Rect(width / 2 - mProgressWidth / 2
						- mProgressStorkeWidth, height / 2 - mProgressLength
						/ 2 - mProgressStorkeWidth, width / 2 + mProgressWidth
						/ 2 + mProgressStorkeWidth, height / 2
						+ mProgressLength / 2 + mProgressStorkeWidth);
		} else {
			if (mRect == null)
				mRect = new Rect(width / 2 - mProgressLength / 2
						- mProgressStorkeWidth, height / 2 - mProgressWidth / 2
						- mProgressStorkeWidth, width / 2 + mProgressLength / 2
						+ mProgressStorkeWidth, height / 2 + mProgressWidth / 2
						+ mProgressStorkeWidth);
		}
	}

	@Override
	public Rect getInvalidateRect() {
		return mRect;
	}

	public int getProgressColor() {
		return mProgressColor;
	}

	public void setProgressColor(int progressColor) {
		mProgressColor = progressColor;
	}

	public int getProgressStorkeColor() {
		return mProgressStorkeColor;
	}

	public void setProgressStorkeColor(int progressStorkeColor) {
		mProgressStorkeColor = progressStorkeColor;
	}

	public int getmProgressWidth() {
		return mProgressWidth;
	}

	public void setmProgressWidth(int mProgressWidth) {
		this.mProgressWidth = mProgressWidth;
	}

	public int getmProgressStorkeWidth() {
		return mProgressStorkeWidth;
	}

	public void setmProgressStorkeWidth(int mProgressStorkeWidth) {
		this.mProgressStorkeWidth = mProgressStorkeWidth;
	}

}
