package com.nanning.commonLib.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 签名工具类
 * 
 * @author dengsaiyun
 *
 */
public class SignUtil {

	public static final String MD5_CHARSET = "UTF-8";

	public static final String MD5_KEY = "123456";

	/**
	 * 生成签名
	 * 
	 * @param appId
	 *            客户应用ID
	 * @param reqId
	 *            请求唯一流水
	 * @param timestamp
	 *            请求时间
	 * @return 签名串
	 */
	public static String generateSign(String appId, String reqId, String timestamp) {
		StringBuffer signBuffer = new StringBuffer();
		signBuffer.append("appId=" + appId + "&");
		signBuffer.append("reqId=" + reqId + "&");
		signBuffer.append("timestamp=" + timestamp + "&");
		signBuffer.append(MD5_KEY);
		String sign = Md5Util.sign(signBuffer.toString(), MD5_CHARSET);
		return sign;
	}

	/**
	 * 校验签名
	 * 
	 * @param appId
	 *            客户应用ID
	 * @param reqId
	 *            请求唯一流水
	 * @param timestamp
	 *            请求时间
	 * @param sign
	 *            签名串
	 * @return 校验结果
	 */
	public static boolean verifySign(String appId, String reqId, String timestamp, String sign) {
		if (StringUtils.isBlank(appId) || StringUtils.isBlank(reqId) 
				|| StringUtils.isBlank(timestamp) || StringUtils.isBlank(sign)) {
			return false;
		}
		String newSign = generateSign(appId, reqId, timestamp);
		if (newSign.equals(sign)) {
			return true;
		} else {
			return false;
		}

	}
	
	public static void main(String[] args) {
		System.out.println(generateSign("APPCLIENT", "201804180001", "15365332162"));
	}

}
