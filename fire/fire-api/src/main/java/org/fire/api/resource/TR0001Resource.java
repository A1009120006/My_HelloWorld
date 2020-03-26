package org.fire.api.resource;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(value = "/api")
public interface TR0001Resource {
	
	/**
	 * 获取制卡准备文件
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/v1/TR0001")
	Object execute(Map<String, Object> param);
	
}
