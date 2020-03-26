package org.fire.api.dto.base;

import java.util.List;

import org.fire.api.dto.base.core.Req;
import org.fire.api.dto.base.core.ReqDto;

/**
 * 多个结果返回
 * @author leo
 *
 * @param <E>
 */
public class MultiReq<E extends ReqDto> extends BaseReq<ReqDto> implements Req<ReqDto> {

	/**
	 * 请求业务参数
	 */
	List<E> data;
	
	public MultiReq() {
		super();
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

}
