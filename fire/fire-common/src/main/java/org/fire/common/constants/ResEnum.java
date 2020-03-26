package org.fire.common.constants;

public enum ResEnum {
	SUCC("000000", "成功"),
	VALID_LOGIN("000001", "登录无效"),
	SYS_ERROR("999999", "系统异常"),
	;
	
	private String retCode;
	private String retMsg;

	private ResEnum(String retCode, String retMsg) {
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

	public String getRetCode() {
		return retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}
}
