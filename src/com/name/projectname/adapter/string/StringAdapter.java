package com.name.projectname.adapter.string;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.name.projectname.adapter.GBaseAdapter;
import com.name.projectname.adapter.string.StringAdapter.ViewHolder;
import com.name.projectname.exception.StringAdapterException;
import com.selevenguo.R;

/**
 * 只显示一行字的adapter
 * 
 * @author selevenguo
 * @date 2014-8-20
 */
public class StringAdapter extends GBaseAdapter<Object, ViewHolder> {
	private int layoutId;
	private int textId;
	private IGetString mGetString = null;

	public StringAdapter(Context context) {
		super(context);
		layoutId = layoutId();
		textId = R.id.tv_string;
	}

	@Override
	public int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.item_string;
	}

	@Override
	public ViewHolder viewHolder() {
		// TODO Auto-generated method stub
		return new ViewHolder();
	}

	@Override
	protected View getConvertView() {
		if (useDefaultLayou())
			return super.getConvertView();

		return LayoutInflater.from(context).inflate(layoutId, null);
	}

	@Override
	public void findView(ViewHolder viewHolder, View convertView) {
		viewHolder.tv = (TextView) convertView.findViewById(textId);
	}

	@Override
	public void setViewDataAndListener(ViewHolder viewHolder, int position,
			Object t) {
		if (t instanceof String) {
			viewHolder.tv.setText((String) t);
			return;
		}
		if (mGetString == null) {
			throw new StringAdapterException(
					context.getString(R.string.StringAdapterException_errorparam));
		}
		viewHolder.tv.setText(mGetString.getString(t));

	}

	/**
	 * 设置custom的layout
	 */
	public void setCustomLayoutAndTextId(int layoutId, int textId) {
		this.layoutId = layoutId;
		this.textId = textId;
	}

	private boolean useDefaultLayou() {
		return layoutId == R.layout.item_string && textId == R.id.tv_string;
	}

	public IGetString getMIGetString() {
		return mGetString;
	}

	public void setMIGetString(IGetString mGetString) {
		this.mGetString = mGetString;
	}

	public class ViewHolder {
		TextView tv;
	}
}
