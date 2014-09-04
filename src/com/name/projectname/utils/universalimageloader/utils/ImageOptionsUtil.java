package com.name.projectname.utils.universalimageloader.utils;

import android.graphics.Bitmap;
import android.os.Handler;

import com.name.projectname.utils.universalimageloader.core.DisplayImageOptions;
import com.name.projectname.utils.universalimageloader.core.assist.ImageScaleType;
import com.name.projectname.utils.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.selevenguo.R;

public class ImageOptionsUtil {
	public static DisplayImageOptions getDefaultDisplayImageOptions() {
		return new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher) // resource or
				.showImageForEmptyUri(R.drawable.ic_launcher) // resource or
				.showImageOnFail(R.drawable.ic_launcher) // resource or drawable
				.resetViewBeforeLoading(false) // default
				.cacheInMemory(true) // default
				.cacheOnDisc(true) // default
				.considerExifParams(false) // default
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
				.bitmapConfig(Bitmap.Config.RGB_565) // default
				.displayer(new SimpleBitmapDisplayer()) // 图片显示方式
				.handler(new Handler()) // default
				.build();
	}
}
