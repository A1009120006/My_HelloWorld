package org.fire.api.dto.base;

import org.fire.api.dto.base.core.Req;
import org.fire.api.dto.base.core.ReqDto;

import lombok.ToString;

/**
 * 请求接口基类
 * @author leo
 *
 * @param <E>
 */
@ToString
public class BaseReq<E extends ReqDto> implements Req<ReqDto> {
	/**
	 * 客户端应用ID
	 */
	private String appId;
	/**
	 * 请求ID
	 */
	private String reqId;
	/**
	 * session令牌
	 */
	private String token;
	/**
	 * 数字签名
	 */
	private String sign;
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
}
