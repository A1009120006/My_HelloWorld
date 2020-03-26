package org.fire.api.dto.base;

import org.fire.api.dto.base.core.Res;
import org.fire.api.dto.base.core.ResDto;

/**
 * 请求接口基类
 * @author leo
 *
 * @param <E>
 */
public abstract class BaseRes<E extends ResDto> implements Res<ResDto> {
	/**
	 * 返回码
	 */
	private String ret;
	/**
	 * 返回消息
	 */
	private String msg;
	/**
	 * 请求ID
	 */
	private String reqId;

	public BaseRes() {
		super();
	}
	public BaseRes(String ret, String msg) {
		super();
		this.ret = ret;
		this.msg = msg;
	}
	
	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	
}
