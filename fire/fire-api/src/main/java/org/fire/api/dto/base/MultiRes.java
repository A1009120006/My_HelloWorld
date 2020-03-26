package org.fire.api.dto.base;

import java.util.List;

import org.fire.api.dto.base.core.Res;
import org.fire.api.dto.base.core.ResDto;

/**
 * 多个结果返回
 * @author leo
 *
 * @param <E>
 */
public class MultiRes<E extends ResDto> extends BaseRes<ResDto> implements Res<ResDto> {

	/**
	 * 请求业务参数
	 */
	List<E> data;
	
	public MultiRes() {
		super();
	}
	
	public MultiRes(List<E> data) {
		super();
		this.data = data;
	}
	
	public MultiRes(String code, String msg) {
		super(code, msg);
	}
	
	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

}
