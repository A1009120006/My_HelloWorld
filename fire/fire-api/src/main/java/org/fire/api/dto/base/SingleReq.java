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
public class SingleReq<E extends ReqDto> extends BaseReq<ReqDto> implements Req<ReqDto> {

	/**
	 * 请求业务参数
	 */
	E data;

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}
	
	
}
