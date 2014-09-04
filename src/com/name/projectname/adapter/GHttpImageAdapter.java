package com.name.projectname.adapter;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.name.projectname.utils.universalimageloader.core.DisplayImageOptions;
import com.name.projectname.utils.universalimageloader.core.ImageLoader;
import com.name.projectname.utils.universalimageloader.core.assist.FailReason;
import com.name.projectname.utils.universalimageloader.core.listener.ImageLoadingListener;
import com.name.projectname.utils.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.name.projectname.utils.universalimageloader.utils.ImageOptionsUtil;
import com.name.projectname.widget.loadingImageView.core.LoadingImageView;

/**
 * 显示网络图片的adapter
 * 
 * @author selevenguo
 * @date 2014-8-20
 */
public abstract class GHttpImageAdapter<T, S> extends GBaseAdapter<T, S> {
	protected DisplayImageOptions options;
	private HashMap<Integer, Float> mProgressMap;

	public GHttpImageAdapter(Context context) {
		super(context);
		options = ImageOptionsUtil.getDefaultDisplayImageOptions();
		mProgressMap = new HashMap<Integer, Float>();
	}

	/**
	 * 加载图片
	 * 
	 * @param iv
	 * @param url
	 */
	protected void displayImage(ImageView iv, String url) {
		ImageLoader.getInstance().displayImage(url, iv, options);
	}

	protected void displayImageAndShowProgress(LoadingImageView iv, String url,
			final int position) {
		ImageLoader.getInstance().displayImage(url, iv, options,
				new ImageLoadingListener() {

					@Override
					public void onLoadingStarted(String imageUri, View view) {
						((LoadingImageView) view).drawProgressStart();
						Float f = mProgressMap.get(position);
						if (f == null) {
							f = new Float(0.0000001f);
							mProgressMap.put(position, f);
						}
						((LoadingImageView) view).setProgress(f.floatValue());
					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
						Float f = mProgressMap.get(position);
						f=new Float(-1.0f);
						mProgressMap.put(position, f);
						((LoadingImageView) view).setProgress(f.floatValue());
					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						Float f = mProgressMap.get(position);
						f=new Float(1.0f);
						mProgressMap.put(position, f);
						((LoadingImageView) view).setProgress(f.floatValue());
					}

					@Override
					public void onLoadingCancelled(String imageUri, View view) {
						Float f = mProgressMap.get(position);
						f = 0.0f;
						mProgressMap.put(position, f);
						((LoadingImageView) view).setProgress(f.floatValue());
					}
				}, new ImageLoadingProgressListener() {

					@Override
					public void onProgressUpdate(String imageUri, View view,
							int current, int total) {
						Float f = mProgressMap.get(position);
						f = ((float) current) / total;
						mProgressMap.put(position, f);
						((LoadingImageView) view).upatedLoadingProgress(f
								.floatValue());
					}
				});
	}
}
