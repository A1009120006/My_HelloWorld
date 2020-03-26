package com.nanning.commonLib.constant;

/**
 * 接口返回码定义
 * 
 * 0开头的为成功返回码
 * 1开头的为公共错误返回码
 * 2开头的接口错误返回码
 * 9开头的为系统异常
 * 
 * @author leo
 *
 */
public enum RtnEnum {

		/**
		 * 
		 */
		RTN_000000("000000", "成功"),
		RTN_000001("000001", "登录无效"),
		RTN_999999("999999", "系统异常");
		
		private String retCode;
		private String retMsg;

		private RtnEnum(String retCode, String retMsg) {
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
