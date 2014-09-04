package alipay.android;

import java.net.URLEncoder;

import alipay.android.msp.Keys;
import alipay.android.msp.Rsa;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.alipay.android.app.sdk.AliPay;

/**
 * 支付宝方法 <br>
 * 
 * @author selevenguo
 * 
 */
public class Pay {
	public static final int WHAT_PAY = 101001;

	public static void pay(final Activity act, final Handler handler, Object order) {
		String info = getNewOrderInfo(order);
		String sign = Rsa.sign(info, Keys.PRIVATE);
		sign = URLEncoder.encode(sign);
		info += "&sign=\"" + sign + "\"&sign_type=\"RSA\"";

		final String orderInfo = info;
		new Thread() {
			public void run() {
				AliPay alipay = new AliPay(act, handler);
				String result = alipay.pay(orderInfo);

				Message msg = new Message();
				msg.what = WHAT_PAY;
				msg.obj = result;
				handler.sendMessage(msg);
			}
		}.start();
	}

	private static String getNewOrderInfo(Object order) {
		StringBuilder sb = new StringBuilder();
		sb.append("partner=\"");
		sb.append(Keys.DEFAULT_PARTNER);
		sb.append("\"&out_trade_no=\"");
//		sb.append(order.getOrder_sn());
		sb.append("\"&subject=\"");
		sb.append("汇龙世界手机订单号");
		sb.append("\"&body=\"");
		sb.append("null");
		sb.append("\"&total_fee=\"");
//		sb.append(order.getOrder_amount());
		sb.append("\"&notify_url=\"");
		// 网址需要做URL编码
		sb.append(URLEncoder.encode(Keys.CALLBACKIP));
		sb.append("\"&service=\"mobile.securitypay.pay");
		sb.append("\"&_input_charset=\"UTF-8");
		sb.append("\"&return_url=\"");
		sb.append(URLEncoder.encode("http://m.alipay.com"));
		sb.append("\"&payment_type=\"1");
		sb.append("\"&seller_id=\"");
		sb.append(Keys.DEFAULT_SELLER);
		// 如果show_url值为空，可不传
		// sb.append("\"&show_url=\"");
		sb.append("\"&it_b_pay=\"1m");
		sb.append("\"");

		return new String(sb);
	}
}
