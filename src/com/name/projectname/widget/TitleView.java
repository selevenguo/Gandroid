package com.name.projectname.widget;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.name.projectname.BaseActivity;
import com.name.projectname.GApplication;
import com.selevenguo.R;

public class TitleView extends LinearLayout {
	protected Context context;
	private Button btn_back, btn_right1, btn_right2;
	private TextView tv_middle, tv_left;
	private LinearLayout lly_right;
	public static final int NONE = -1;
	public static final int LEFT = 0;
	public static final int RIGHT1 = 1;
	public static final int RIGHT2 = 2;
	public static final int MIDDLE = 3;
	public static int title_tv_max_width = 0;
	private final float minTitleSize = 10.0f;
	private float leftDefaultSize;
	private float middleDefaultSize;
	private TextView mTitleTv;

	public TitleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	public TitleView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	private void init() {
		View titleView = LayoutInflater.from(context).inflate(
				R.layout.widget_title, this);
		LinearLayout.LayoutParams params_title = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		titleView.setLayoutParams(params_title);
		// 初始化title中的子控件
		btn_back = (Button) titleView.findViewById(R.id.btn_title_left);
		btn_right1 = (Button) titleView.findViewById(R.id.btn_title_right1);
		btn_right2 = (Button) titleView.findViewById(R.id.btn_title_right2);
		tv_left = (TextView) titleView.findViewById(R.id.tv_title_left);
		tv_middle = (TextView) titleView.findViewById(R.id.tv_title_middle);
		lly_right = (LinearLayout) titleView.findViewById(R.id.lly_title_right);
		btn_back.setOnClickListener(finishClickListener);
		tv_left.setOnClickListener(finishClickListener);

		leftDefaultSize = tv_left.getTextSize();
		middleDefaultSize = tv_middle.getTextSize();
	}

	private OnClickListener finishClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			((BaseActivity) context).finish();
		}
	};

	/** 设置按钮点击事件 **/
	public void setTitleBtnOnclickListener(int whichBtn,
			OnClickListener onClickListener) {
		Button bt = getBtn(whichBtn);
		if (bt != null) {
			bt.setOnClickListener(onClickListener);
			if (whichBtn == LEFT)
				tv_left.setOnClickListener(onClickListener);
		}
	}

	/** 设置按钮点长击事件 **/
	public void setTitleBtnOnclickListener(int whichBtn,
			OnLongClickListener onLongClickListener) {
		Button bt = getBtn(whichBtn);
		if (bt != null) {
			bt.setOnLongClickListener(onLongClickListener);
		}
	}

	/** 设置按钮显示状态 **/
	public void setTitleBtnVisibility(int whichBtn, int visibility) {
		Button bt = getBtn(whichBtn);
		if (bt != null) {
			bt.setVisibility(visibility);
			setTitleTextSize(mTitleTv.getText().toString());
		}
	}

	public int getBtnId(int whichBtn) {
		switch (whichBtn) {
		case LEFT:
			return btn_back.getId();
		case RIGHT1:
			return btn_right1.getId();
		case RIGHT2:
			return btn_right2.getId();
		default:
			return -1;
		}
	}

	private Button getBtn(int whichBtn) {
		switch (whichBtn) {
		case LEFT:
			return btn_back;
		case RIGHT1:
			return btn_right1;
		case RIGHT2:
			return btn_right2;
		default:
			return null;
		}
	}

	/** 设置title内容 **/
	public void setTitle(int whichTitle, String title) {
		selectTitleTextView(whichTitle);
		setTitle(title);
	}

	/** 设置title内容 **/
	public void setTitle(final String title) {
		if (mTitleTv == null)
			return;
		if (title == null) {
			mTitleTv.setText("");
			return;
		}
		// 根据宽度适配字体大小
		if (mTitleTv == null || mTitleTv.getWidth() == 0) {
			mTitleTv.getViewTreeObserver().addOnGlobalLayoutListener(
					new OnGlobalLayoutListener() {
						@Override
						public void onGlobalLayout() {
							setTitleTextSize(title);
							mTitleTv.getViewTreeObserver()
									.removeGlobalOnLayoutListener(this);
						}
					});
		} else {
			setTitleTextSize(title);
		}
	}

	/** 根据字符数和控件宽度设置字体大小 **/
	private void setTitleTextSize(String text) {
		if (mTitleTv == null)
			return;
		if (mTitleTv.getId() == tv_middle.getId())
			refreshTitleTextViewParams();
		title_tv_max_width = mTitleTv.getWidth() - 20;
		if (title_tv_max_width < 0)
			return;
		Paint paint = new Paint();
		paint.set(mTitleTv.getPaint());
		paint.setTextSize(getDefaultTitleSize());
		float nowWidth = paint.measureText(text);
		if (nowWidth <= title_tv_max_width) {
			mTitleTv.setText(text);
			return;
		}
		// 若大于则计算新的size
		float newSize = (float) title_tv_max_width / nowWidth
				* getDefaultTitleSize();
		if (newSize < minTitleSize)
			newSize = minTitleSize;
		mTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, newSize);
		mTitleTv.setText(text);

	}

	/** 根据title中控件显示数量设置title_tv的宽度参数 **/
	private void refreshTitleTextViewParams() {
		Button btright = btn_right2;
		if (btn_right2.getVisibility() == View.GONE)
			btright = btn_right1;
		float width = GApplication.DISPLAY_WIDHT
				- (GApplication.DISPLAY_WIDHT - (lly_right.getX() - GApplication.DISPLAY_DENSITY))
				* 2;
		if (btright.getVisibility() == View.GONE
				&& btn_back.getVisibility() == View.VISIBLE) {
			width = (int) (GApplication.DISPLAY_WIDHT - (btn_back.getX() + btn_back
					.getWidth()) * 2);
		} else if (btn_right1.getVisibility() == View.GONE
				&& btn_right2.getVisibility() == View.GONE
				&& btn_back.getVisibility() == View.GONE) {
			width = GApplication.DISPLAY_WIDHT
					- getResources().getDimensionPixelSize(
							R.dimen.dp_screen_padding_lr);
		}
		mTitleTv.setLeft((int) ((GApplication.DISPLAY_WIDHT - width) / 2));
		mTitleTv.setRight((int) ((GApplication.DISPLAY_WIDHT + width) / 2));
	}

	/** 更换当前需要显示的titletextview **/
	public void selectTitleTextView(int whichTitle) {
		mTitleTv = getTitle(whichTitle);
		tv_left.setVisibility(View.GONE);
		tv_middle.setVisibility(View.GONE);
		if (mTitleTv != null)
			mTitleTv.setVisibility(View.VISIBLE);
	}

	/** 获取当前的title的字体最大大小 **/
	private float getDefaultTitleSize() {
		if (mTitleTv == null)
			return 0;
		if (mTitleTv.getId() == tv_left.getId())
			return leftDefaultSize;
		return middleDefaultSize;
	}

	/**
	 * 根据变量获取titletextview
	 * 
	 * @param whichTitle
	 * @return
	 */
	private TextView getTitle(int whichTitle) {
		switch (whichTitle) {
		case LEFT:
			return tv_left;
		case MIDDLE:
			return tv_middle;
		default:
			return null;
		}
	}

}
