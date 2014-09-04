package com.name.projectname.utils;

import java.io.File;

import android.os.Environment;
import android.os.StatFs;

/**
 * 关于sd卡的一些操作：获取sd卡是否有效，sd卡的空间等信息
 */
public class SDCardUtil {
	
	/**
	 * 判断sd卡是否有效
	 * @return
	 */
	public static boolean getExternalStorageCard() {
		return Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}

	/**
	 * 检查sd卡是否存储已满或者可用
	 * @return
	 */
	public static boolean diskSpaceAvailable() {
		File mSDCardDirectory = getESD();
		if (mSDCardDirectory == null) {
			return false;
		}
		StatFs fs = new StatFs(mSDCardDirectory.getAbsolutePath());
		// keep one free block
		return fs.getAvailableBlocks() > 1;
	}

	/**
	 * 返回当前sdcard 可用空间大小， 单位byte
	 */
	public static long getSdcardFreeSize() {
		// 取得SDCard当前的状态
		String sDcString = Environment.getExternalStorageState();
		long nSDFreeSize = 0;
		if (sDcString.equals(Environment.MEDIA_MOUNTED)) {
			// 取得sdcard文件路径
			File pathFile = getESD();
			android.os.StatFs statfs = new android.os.StatFs(pathFile.getPath());
			long nBlocSize = statfs.getBlockSize();
			long nAvailaBlock = statfs.getAvailableBlocks();
			nSDFreeSize = nAvailaBlock * nBlocSize;
		}
		return nSDFreeSize;
	}

	/**
	 * 获取sd卡的路径
	 * @return
	 */
	public static String getESDString() {
		return getESD().toString();
	}

	/**
	 * 获取sd卡文件路径
	 * @return
	 */
	public static File getESD() {
		return Environment.getExternalStorageDirectory();
	}
}
