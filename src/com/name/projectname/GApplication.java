//
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//           佛祖保佑    永无BUG    永不修改
//
//

package com.name.projectname;

import java.io.File;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.name.projectname.utils.ToastUtil;
import com.name.projectname.utils.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.name.projectname.utils.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.name.projectname.utils.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.name.projectname.utils.universalimageloader.core.DisplayImageOptions;
import com.name.projectname.utils.universalimageloader.core.ImageLoader;
import com.name.projectname.utils.universalimageloader.core.ImageLoaderConfiguration;
import com.name.projectname.utils.universalimageloader.core.assist.QueueProcessingType;
import com.name.projectname.utils.universalimageloader.core.download.BaseImageDownloader;
import com.name.projectname.utils.universalimageloader.utils.StorageUtils;

/**
 * 
 * @author selevenguo
 * 
 */
public class GApplication extends Application {
	public static Context context;
	public static int DISPLAY_WIDHT;
	public static int DISPLAY_HEIGHT;
	public static float DISPLAY_DENSITY;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		context = getApplicationContext();

		init();
	}

	private void init() {
		initScreenInfo();
		initUtil();
	}

	/**
	 * 获得屏幕宽高及密度
	 */
	private void initScreenInfo() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		DISPLAY_WIDHT = dm.widthPixels; // 屏幕宽度（像素）
		DISPLAY_HEIGHT = dm.heightPixels; // 屏幕高度（像素）
		DISPLAY_DENSITY = dm.density;
	}

	/**
	 * 初始化常用工具
	 */
	private void initUtil() {
		// 初始化toast工具
		ToastUtil.init(context);
		// 初始化imageloader
		initImageLoader();
	}

	/**
	 * 初始化imageloader参数
	 */
	private void initImageLoader() {
		// 建立图片缓存路径
		File cacheDir = StorageUtils.getCacheDirectory(context);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.threadPoolSize(5)
				// 设置线程数量
				.threadPriority(Thread.NORM_PRIORITY - 1)
				// 设置线程优先级
				.tasksProcessingOrder(QueueProcessingType.FIFO)
				// 设置图片下载和显示的工作队列排序
				// .denyCacheImageMultipleSizesInMemory()//当同一个url返回多个大小不一的图片，设置只缓存一个
				.memoryCache(
						new LruMemoryCache((int) (Runtime.getRuntime()
								.maxMemory() / 8)))
				.memoryCacheSizePercentage(13) // default
				.discCache(new UnlimitedDiscCache(cacheDir)) // default
				.discCacheSize(50 * 1024 * 1024)//
				.discCacheFileCount(200)// sd卡上缓存图片数量
				.discCacheFileNameGenerator(new Md5FileNameGenerator()) // sd卡上图片名称
				.imageDownloader(new BaseImageDownloader(context)) // default
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
				.build();

		ImageLoader.getInstance().init(config);
	}

}
