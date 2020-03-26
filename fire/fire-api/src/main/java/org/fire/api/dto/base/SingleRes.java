package org.fire.api.dto.base;

import org.fire.api.dto.base.core.Res;
import org.fire.api.dto.base.core.ResDto;

/**
 * 单个结果返回
 * @author leo
 *
 * @param <E>
 */
public class SingleRes<E extends ResDto> extends BaseRes<ResDto> implements Res<ResDto> {

	/**
	 * 请求业务参数
	 */
	E data;

	public SingleRes() {
		super();
	}
	public SingleRes(String code, String msg) {
		super(code, msg);
	}
	
	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}
	
}
