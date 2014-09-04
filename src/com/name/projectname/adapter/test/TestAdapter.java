package com.name.projectname.adapter.test;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.name.projectname.adapter.GHttpImageAdapter;
import com.name.projectname.adapter.test.TestAdapter.ViewHolder;
import com.name.projectname.entity.TestBean;
import com.name.projectname.widget.loadingImageView.LineLoadingProgress;
import com.name.projectname.widget.loadingImageView.core.LoadingImageView;
import com.selevenguo.R;

public class TestAdapter extends GHttpImageAdapter<TestBean, ViewHolder> {
	private LineLoadingProgress loadingProgress;

	public TestAdapter(Context context) {
		super(context);
		loadingProgress = new LineLoadingProgress();
	}

	@Override
	protected int layoutId() {
		return R.layout.item_test;
	}

	@Override
	protected ViewHolder viewHolder() {
		return new ViewHolder();
	}

	@Override
	protected void findView(ViewHolder viewHolder, View convertView) {
		viewHolder.iv = (LoadingImageView) convertView.findViewById(R.id.iv);
		viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
	}

	@Override
	protected void setViewDataAndListener(ViewHolder viewHolder, int position,
			TestBean t) {
		viewHolder.tv.setText(t.getText());
		viewHolder.iv.setDrawLoading(loadingProgress);
//		if (position == 0)
			displayImageAndShowProgress(viewHolder.iv, t.getUrl(),position);
//		else
//			displayImage(viewHolder.iv, t.getUrl());
	}

	public class ViewHolder {
		LoadingImageView iv;
		TextView tv;
	}

}
