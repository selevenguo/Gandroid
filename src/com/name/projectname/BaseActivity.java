package com.name.projectname;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.name.projectname.data.DataCenter;
import com.name.projectname.exception.PutErrorParamsException;
import com.name.projectname.utils.DateUtil;
import com.name.projectname.utils.DialogUtil;
import com.name.projectname.utils.GsonUtil;
import com.name.projectname.utils.ToastUtil;
import com.name.projectname.widget.TitleView;
import com.selevenguo.R;
import com.umeng.analytics.MobclickAgent;

/**
 * @author selevenguo
 * @date 2014-8-8
 */
@SuppressLint("NewApi")
public abstract class BaseActivity extends Activity implements IBase {
	protected Context context;
	private ProgressDialog mProgressDialog;
	private TitleView titleView;
	private String title;

	/** 设置layout的id **/
	public abstract int layoutId();

	/**
	 * 是否启用title栏，若设置false，本activity将不存在title栏<br>
	 * 若只是当前不显示，请返回true，并调用setTitleBarVisibility设置隐藏
	 */
	public abstract boolean hasTitle();

	/** 设置title内容 **/
	public int title() {
		return R.string.app_name;
	}

	/** 选择title显示位置 NONE不显示，LEFT左边title，MIDDLE中央title **/
	public int selectTitle() {
		return TitleView.MIDDLE;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		this.title = getString(title());
		inflateView();

		getParams();
		findView();
		init();
	}

	private void inflateView() {
		if (!hasTitle()) {
			setContentView(layoutId());
			return;
		}
		// 初始化父容器
		LinearLayout viewgroup = new LinearLayout(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		viewgroup.setLayoutParams(params);
		viewgroup.setOrientation(LinearLayout.VERTICAL);
		// 初始化title
		titleView = new TitleView(context);
		LinearLayout.LayoutParams params_title = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		titleView.setLayoutParams(params_title);
		viewgroup.addView(titleView);
		// 初始化内容
		View content = LayoutInflater.from(context).inflate(layoutId(), null);
		LinearLayout.LayoutParams params_content = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, 0);
		params_content.weight = 1;
		content.setLayoutParams(params_content);
		viewgroup.addView(content);
		setContentView(viewgroup);

		// 设置选中title
		setTitle(selectTitle(), this.title);

	}

	/** 获取传递参数，tampcache传递 **/
	public void getParams() {
	}

	/** findviewbyid **/
	public abstract void findView();

	/** 初始化相关操作 **/
	public abstract void init();

	public Object f(int id) {
		return findViewById(id);
	}

	/** 设置按钮点击事件 **/
	public void setTitleBtnOnclickListener(int whichBtn,
			OnClickListener onClickListener) {
		titleView.setTitleBtnOnclickListener(whichBtn, onClickListener);
	}

	/** 设置按钮点长击事件 **/
	public void setTitleBtnOnclickListener(int whichBtn,
			OnLongClickListener onLongClickListener) {
		titleView.setTitleBtnOnclickListener(whichBtn, onLongClickListener);
	}

	/** 设置按钮显示状态 **/
	public void setTitleBtnVisibility(int whichBtn, int visibility) {
		titleView.setTitleBtnVisibility(whichBtn, visibility);
	}

	/** 设置title内容 **/
	public void setTitle(int whichTitle, String title) {
		titleView.setTitle(whichTitle, title);
	}

	/** 设置title内容 **/
	public void setTitle(final String title) {
		titleView.setTitle(title);
	}

	/** 更换当前需要显示的titletextview **/
	public void selectTitleTextView(int whichTitle) {
		titleView.selectTitleTextView(whichTitle);
	}

	/** 控制title显示 **/
	public void setTitleBarVisibility(int visibility) {
		titleView.setVisibility(visibility);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		closeProgressDialog();
		super.onDestroy();
	}

	/**
	 * 显示等待对话框,点击屏幕后消失
	 */
	public void showProgressDialog() {
		showProgressDialog(true, getString(R.string.loading), null);
	}

	/** 显示等待对话框,点击屏幕后消失,可设置内容 **/
	public void showProgressDialog(String msg) {
		showProgressDialog(true, msg, null);
	}

	/** 显示等待对话框，并控制点击事件,可设置内容 **/
	public void showProgressDialogNonCancelable() {
		showProgressDialog(true, getString(R.string.loading), null);
	}

	public void showProgressDialogNonCancelable(String msg) {
		showProgressDialog(true, msg, null);
	}

	/** 关闭progressdialog **/
	public void closeProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()
				&& this != null && !this.isFinishing()) {
			mProgressDialog.dismiss();
		}
	}

	/**
	 * 显示等待对话框
	 * 
	 * @param isCancelable
	 * @param msg
	 * @param cancelListener
	 */
	public void showProgressDialog(boolean isCancelable, String msg,
			OnCancelListener cancelListener) {
		if (mProgressDialog == null) {
			mProgressDialog = DialogUtil.createDefaultProgressDialog(this);
		}
		mProgressDialog.setCancelable(isCancelable);
		mProgressDialog.setMessage(msg);
		mProgressDialog.setOnCancelListener(cancelListener);
		try {
			mProgressDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*----------以下是工具方法,base接口中方法---------*/

	/** 调用ToastUtil中showToastShort **/
	@Override
	public void showToastShort(String text) {
		ToastUtil.showToastShort(text);
	}

	/** 调用ToastUtil中showToastLong **/
	@Override
	public void showToastLong(String text) {
		ToastUtil.showToastLong(text);
	}

	/** 获得唯一gson实例 **/
	@Override
	public Gson getGson() {
		return GsonUtil.getGson();
	}

	/** 打开新的activity **/
	@Override
	public void startAct(Class<?> act) {
		startAct(act, null);
	}

	/** 打开新的activity **/
	@Override
	public void startActWithAni(Class<?> act) {
		startAct(act, true, null);
	}

	/** 带flag打开新的activity **/
	@Override
	public void startAct(Class<?> act, int... flags) {
		startAct(act, false, flags);
	}

	/** 带flag,anim打开新的activity **/
	@Override
	public void startActWithAni(Class<?> act, int... flags) {
		startAct(act, true, flags);

	}

	/** 打开新的activity基方法 **/
	@Override
	public void startAct(Class<?> act, boolean isAnim, int... flags) {
		Intent intent = new Intent();
		if (flags != null) {
			for (int flag : flags) {
				intent.setFlags(flag);
			}
		}
		intent.setClass(context, act);
		startActivity(intent);
		if (isAnim)
			defaultActIn();
	}

	/** 本默认关闭activity **/
	public void defaultFinish() {
		this.finish();
		defaultActout();
	}

	/** 出场动画 **/
	@Override
	public void defaultActIn() {
		this.overridePendingTransition(R.anim.slide_in_from_right,
				R.anim.slide_out_to_bottom);
	}

	/** 出场动画 **/
	@Override
	public void defaultActout() {
		this.overridePendingTransition(R.anim.slide_in_from_bottom,
				R.anim.slide_in_from_right);
	}

	/** 如需传参数，可在startact前调用此方法 **/
	@Override
	public void putDataBeforeStartAct(Object... obj) {
		if (obj.length % 2 == 1)
			throw new PutErrorParamsException(
					getString(R.string.PutErrorParamsException_errorlenth));
		try {
			for (int i = 0; i < obj.length / 2; i++) {
				DataCenter.getMap().put(((String) obj[i]), obj[i + 1]);
			}
		} catch (ClassCastException e) {
			throw new PutErrorParamsException(
					getString(R.string.PutErrorParamsException_errorparam));
		}
	}

	/** 根据指定格式的时间获取相应datetime **/
	@Override
	public String formatConvert(String time, String from, String to) {
		return DateUtil.formatConvert(time, from, to);
	}

	/** 将unix时间转换为yy-MM-dd HH:mm:ss时间格式 **/
	@Override
	public String formatUnxiTime(long time) {
		return DateUtil.formatUnxiTime(time);
	}

	/** 将unix时间转换成指定格式的字符串 **/
	@Override
	public String formatUnxiTime(long time, String pattern) {
		return DateUtil.formatUnxiTime(time, pattern);
	}

	/** 转化为yy-MM-dd HH:mm:ss时间格式 **/
	@Override
	public String format(long time) {
		return DateUtil.format(time);
	}

	/** 转化为指定时间格式 **/
	@Override
	public String format(long time, String pattern) {
		return DateUtil.format(time, pattern);
	}
}
