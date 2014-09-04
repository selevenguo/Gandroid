/*
 * Copyright (C) 2010 The MobileSecurePay Project
 * All right reserved.
 * author: shiqun.shi@alipay.com
 *
 *  提示：如何获取安全校验码和合作身份者id
 *  1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *  2.点击“商家服务”(https://b.alipay.com/order/myorder.htm)
 *  3.点击“查询合作者身份(pid)”、“查询安全校验码(key)”
 */

package alipay.android.msp;

//
// 请参考 Android平台安全支付服务(msp)应用开发接口(4.2 RSA算法签名)部分，并使用压缩包中的openssl RSA密钥生成工具，生成一套RSA公私钥。
// 这里签名时，只需要使用生成的RSA私钥。
// Note: 为安全起见，使用RSA私钥进行签名的操作过程，应该尽量放到商家服务器端去进行。
public final class Keys {

	// 合作身份者id，以2088开头的16位纯数字
	public static final String DEFAULT_PARTNER = "2088511401545978";
//	 public static final String DEFAULT_PARTNER = "2088411215788382";

	// 收款支付宝账号
	public static final String DEFAULT_SELLER = "18852968888@139.com";
//	 public static final String DEFAULT_SELLER = "2758288882@qq.com";

	// 商户私钥，自助生成
	public static final String PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKdx6mjZRlIIz8xa4NlD4T/ESZtIBIkTF1cvx1oHc9KC1aKEakzTvngZU3Zz42emNONmhLIYJtjGuymNafQhTRYn1oQYt18jBpjfy8XHnOaFy2mYba6ljje2r4NplI8rWgevm4qN3CYhfL8F7p/cdSj7shjgjGrBEgUnUhqhXZgFAgMBAAECgYBbw6Y/R7OuCX439CX9a6HqHFxqaRFzPa8w7NJHmIX0/BawNfk7mV9s2hRPejtJ9mBNr0VRhMLlUiQIJaPHUui3NJNHhhOg4FNWnlwR5gLFL+TR/V4Lq0lrwvExIQhAScLZ32cLyhs4C36IXBBjU5rJMRSKv6y7DOvV5tljA8eQkQJBANZ9LJzLu+eVQTozUfGMkR4mqMsMpRe3DUXbHErXA94QA0khI4Slwe9OVoyT8zSKBtukHHvbI0trzx2r9abCSIsCQQDH2fQ6S1PxinCGW1oE2HU0QKM/0mO5ij/+EPWouUISRw3+EH1v+Rshrv5dwBBUkNylGlxjYnvhJMBL+HiJSSOvAkEAoBXZAEYHPvdlMZ0wIcq6XPiZrsMHTUXwgBzjZvBIEKt+6Ev1iIF217duXYnnUWVbbP91yvNpylbyxMCdnhMPtQJAasRh2ZLMscnH+79qBBSv6M5vaHU5mbHmJhFhbFYsmh8jtouQt/VZLVGTyvoEv2j0t6+xk9bVX56GrQ99NbrBOQJBAJfgDEOWdPciH/9rGWwx8ZKoiWPUgLWv3tzUULv0fFifi45md0fzcezoa3sU/A6NRS8oX5xJyaKMKbAIgBAiD9Y=";
	//	 public static final String PRIVATE =
//	 "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALbSRiL0Ys22SzPLMvFNx5udgfH1szW9LZbKXg5D6N6FEgPEE0fGxDl9SkcT8Pw3xVITTHq0ZIEiTeoxN1IJaPqQbJaZEeKOdM5cIJEJjtVCyPJTw3yffVP/ZF5xdd/XQsNbPyRNV1aqDBGPwnV4FerySyugLmuoAmdT1Sb4ghI5AgMBAAECgYEAidshO/ZgEpJlPpb4wPBPlc49UEBfdCREnjHWnpcin7hkDybpCvScvmT1f4dP7j/3sJ4Qy23/Y0iEzBV0i43A8/lGV+ApDgIkNJ1JTTjpR8Z5L4XtDHb9F6RHmWHmozb6fA+AlRxkDqvyjrZgupb/RAWGNtl/9smBLaRXwQvGBQECQQDfnRPN796tAM8JmTUIMmduz6ZvXLk5yN6R50gCoqHDW8WYSj9bisVtoK0YWrsWzJ8b1joxq7zqWk8pL5ld/TNpAkEA0Uy+bS2bmFZXZoPISGasDhd/Y5MlYzskqOPN6Uw1APxEPAGqbuWyVo2MRFne9FB14yW2su16ZuBeAoXyNRqeUQJBAL44Ww6GjMBSvcyGfex5JC/JiOWW1SJo05z+R+2l90G+obpI/tialhQJ6PWcySY97uO5O0vhshURueFpUmf36nECQA7hCMyUKHKM+Mul5LAY9HRQAVNf25GRW8rR3wqNy1rVlDg72hTmKWZzy5v0U6BEtAzA2psfvMrhem1tfkl+KfECQEg8QHW/gZAeRx9t8Sn88i5ewOlpi3EJQJTG9Rze7aqCvDa6S/+jILbWCcJRkpkeDlQSUM5obaw1TyTwqkNwnkk=";

	public static final String PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
//	public static final String PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
//	 public static final String PUBLIC =
//	 "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	public static final String CALLBACKIP = "http://www.hl-021.com/app/respond.php";
}
