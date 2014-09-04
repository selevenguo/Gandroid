package com.name.projectname.adapter;

import java.util.List;

import com.name.projectname.adapter.string.StringAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * T-list中的成员类型，S-指定的ViewHolder
 * 
 * @author selevenguo
 * @date 2014-8-20
 */
public abstract class GBaseAdapter<T, S> extends BaseAdapter {
	protected Context context;
	private List<T> list;
	private OnItemChildClickListener<T> onItemChildClickListener;
	private OnItemChildLongClickListener<T> onItemChildLongClickListener;

	public GBaseAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		return list == null ? null : list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		S viewHolder = null;
		if (convertView == null) {
			viewHolder = viewHolder();
			convertView = getConvertView();
			findView(viewHolder, convertView);
			convertView.setTag(viewHolder);
		} else
			viewHolder = (S) convertView.getTag();

		setViewDataAndListener(viewHolder, position, list.get(position));

		return convertView;
	}

	/**
	 * 设置本类的layout return R.Layout.id;
	 */
	protected abstract int layoutId();

	/**
	 * 获取viewHolder实例
	 * 
	 * @return new ViewHolder();
	 */
	protected abstract S viewHolder();

	/**
	 * 获取convertview对象
	 * 
	 * @return
	 */
	protected View getConvertView() {
		return LayoutInflater.from(context).inflate(layoutId(), null);
	}

	/**
	 * 绑定view
	 * 
	 * @param viewHolder
	 * @param convertView
	 */
	protected abstract void findView(S viewHolder, View convertView);

	/**
	 * 设置数据
	 */
	protected abstract void setViewDataAndListener(S viewHolder, int position,
			T t);

	/**
	 * 设置item中的点击事件
	 * 
	 * @param position
	 * @param v
	 */
	public void setClickListener(int position, View v) {
		v.setTag(position);
		v.setOnClickListener(ocl);
	}

	/**
	 * 设置item中的长点击事件
	 * 
	 * @param position
	 * @param v
	 */
	public void setLongClickListener(int position, View v) {
		v.setTag(position);
		v.setOnLongClickListener(olcl);

	}

	/**
	 * 移除view的点击事件
	 */
	public void removeClickListener(View v) {
		v.setOnClickListener(null);
	}

	/**
	 * 移除view的长点击事件
	 */
	public void removeLongClickListener(View v) {
		v.setOnLongClickListener(null);
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public OnItemChildClickListener<T> getOnItemChildClickListener() {
		return onItemChildClickListener;
	}

	public void setOnItemChildClickListener(
			OnItemChildClickListener<T> onItemChildClickListener) {
		this.onItemChildClickListener = onItemChildClickListener;
	}

	public OnItemChildLongClickListener<T> getOnItemChildLongClickListener() {
		return onItemChildLongClickListener;
	}

	public void setOnItemChildLongClickListener(
			OnItemChildLongClickListener<T> onItemChildLongClickListener) {
		this.onItemChildLongClickListener = onItemChildLongClickListener;
	}

	private OnClickListener ocl = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (onItemChildClickListener != null) {
				onItemChildClickListener.onItemChildClickListener(v,
						list.get(((Integer) v.getTag())));
			}
		}
	};

	private OnLongClickListener olcl = new OnLongClickListener() {

		@Override
		public boolean onLongClick(View v) {
			if (onItemChildLongClickListener != null) {
				onItemChildLongClickListener.onItemChildLongClickListener(v,
						list.get(((Integer) v.getTag())));
				return true;
			}
			return false;
		}
	};

}
