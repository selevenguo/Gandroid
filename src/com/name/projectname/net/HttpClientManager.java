package com.name.projectname.net;

import com.name.projectname.data.DataUrls;
import com.name.projectname.net.http.AsyncHttpClient;
import com.name.projectname.net.http.AsyncHttpResponseHandler;
import com.name.projectname.net.http.RequestParams;

/**
 * http请求静态方法
 * 
 * @author selevenguo
 * @date 2014-9-3
 */
public class HttpClientManager {
	private static String BASEURL = DataUrls.BASE_URL;
	private static AsyncHttpClient client = new AsyncHttpClient();
	static {
		client.setTimeout(15000);
	}

	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASEURL + relativeUrl;
	}
}
