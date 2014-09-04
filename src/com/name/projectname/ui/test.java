/**
 * 
 */
package com.name.projectname.ui;

import org.apache.http.Header;
import org.json.JSONObject;

import android.util.Log;
import android.widget.ListView;

import com.name.projectname.BaseActivity;
import com.name.projectname.data.DataUrls;
import com.name.projectname.net.HttpClientManager;
import com.name.projectname.net.http.JsonHttpResponseHandler;
import com.name.projectname.widget.TitleView;
import com.selevenguo.R;
import com.umeng.update.UmengUpdateAgent;

/**
 * @author Administrator
 * 
 */
public class test extends BaseActivity {
	private ListView lv;

	@Override
	public int layoutId() {
		return R.layout.test;
	}

	@Override
	public boolean hasTitle() {
		return true;
	}

	@Override
	public int title() {
		return R.string.title;
	}

	@Override
	public int selectTitle() {
		return TitleView.LEFT;
	}

	@Override
	public void getParams() {

	}

	@Override
	public void findView() {
		lv = (ListView) f(R.id.lv);
	}

	@Override
	public void init() {
		UmengUpdateAgent.update(this);
		// List list = new ArrayList<TestBean>();
		// list.add(new
		// TestBean("http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/15/c0/spcgroup/20957260_1368608378249_1920x1200.jpg",
		// "aaaaaaaaaaaaaaaaaaaaaaaa"));
		// list.add(new
		// TestBean("http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/15/c0/spcgroup/20957260_1368608378248_1920x1200.jpg",
		// "bbbbbbbbbbbbbbbbbbbbbbbb"));
		// list.add(new
		// TestBean("http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/15/c0/spcgroup/20957260_1368608378247_1920x1200.jpg",
		// "cccccccccccccccccccccccc"));
		// list.add(new
		// TestBean("http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/15/c0/spcgroup/20942607_1368590559893_1920x1200.jpg",
		// "iiiiiiiiiiiiiiiiiiiiiiii"));
		// list.add(new
		// TestBean("http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/15/c0/spcgroup/20942607_1368590559896_1920x1200.jpg",
		// "jjjjjjjjjjjjjjjjjjjjjjjj"));
		// list.add(new
		// TestBean("http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/15/c0/spcgroup/20942607_1368590559894_1920x1200.jpg",
		// "kkkkkkkkkkkkkkkkkkkkkkkk"));
		// list.add(new
		// TestBean("http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/15/c0/spcgroup/20942607_1368590559894_1920x1200.jpg",
		// "kkkkkkkkkkkkkkkkkkkkkkkk"));
		// list.add(new
		// TestBean("http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/15/c0/spcgroup/20942607_1368590559894_1920x1200.jpg",
		// "kkkkkkkkkkkkkkkkkkkkkkkk"));
		// list.add(new
		// TestBean("http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/15/c0/spcgroup/20942607_1368590559894_1920x1200.jpg",
		// "kkkkkkkkkkkkkkkkkkkkkkkk"));
		// list.add(new
		// TestBean("http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/15/c0/spcgroup/20942607_1368590559894_1920x1200.jpg",
		// "kkkkkkkkkkkkkkkkkkkkkkkk"));
		// list.add(new
		// TestBean("http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/15/c0/spcgroup/20942607_1368590559894_1920x1200.jpg",
		// "kkkkkkkkkkkkkkkkkkkkkkkk"));
		// list.add(new
		// TestBean("http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/15/c0/spcgroup/20942607_1368590559894_1920x1200.jpg",
		// "kkkkkkkkkkkkkkkkkkkkkkkk"));
		// list.add(new
		// TestBean("http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/15/c0/spcgroup/20942607_1368590559894_1920x1200.jpg",
		// "kkkkkkkkkkkkkkkkkkkkkkkk"));
		// TestAdapter adapter = new TestAdapter(context);
		// adapter.setList(list);
		// lv.setAdapter(adapter);
		// HttpClientManager.post(DataUrls.HOME_CATEGORY, null, new
		// JsonHttpResponseHandler(){
		//
		// @Override
		// public void onSuccess(int statusCode, Header[] headers,
		// JSONObject response) {
		// super.onSuccess(statusCode, headers, response);
		// Log.i("test", response.toString());
		// }
		//
		// @Override
		// public void onFailure(int statusCode, Header[] headers,
		// String responseString, Throwable throwable) {
		// super.onFailure(statusCode, headers, responseString, throwable);
		// }});
	}

	class BB {
		public BB(String a, String b) {
			this.a = a;
			this.b = b;
		}

		String a;
		String b;
	}
}
