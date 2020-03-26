package org.fire.api.resource;

import org.fire.api.dto.DemoInput;
import org.fire.api.dto.LoginInput;
import org.fire.api.dto.base.SingleReq;

public interface DemoResource {
	/**
	 * @param req
	 * @return
	 */
	Object test();
	
	/**
	 * @param req
	 * @return
	 */
	Object Hello(SingleReq<DemoInput> req);
	
	/**
	 * @param req
	 * @return
	 */
	Object Login(SingleReq<LoginInput> req);
	
}
