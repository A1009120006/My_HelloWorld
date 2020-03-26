package org.fire.server.model1.controller;

import java.util.List;
import java.util.UUID;

import org.fire.api.dto.DemoInput;
import org.fire.api.dto.LoginInput;
import org.fire.api.dto.LoginOutput;
import org.fire.api.dto.base.BaseRes;
import org.fire.api.dto.base.SingleReq;
import org.fire.api.dto.base.core.ResDto;
import org.fire.api.resource.DemoResource;
import org.fire.server.model1.service.IDemoService;
import org.fire.server.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v1/demo")
@Slf4j
public class DemoController implements DemoResource {
	
	@Autowired
	private IDemoService demoService;

	@Override
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Object test() {
		log.info("enter test controller");
		return "test success";
	}

	@Override
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public BaseRes<ResDto> Hello(@RequestBody SingleReq<DemoInput> req) {
		log.info("enter hello controller {}", JSON.toJSONString(req));
		List<ResDto> result = demoService.returnCollection(req.getData());
		return ApiUtil.buildRes(result);
	}
	@Override
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public BaseRes<ResDto> Login(@RequestBody SingleReq<LoginInput> req) {
		log.info("enter hello controller {}", JSON.toJSONString(req));
		LoginOutput loginOutput = new LoginOutput();
		loginOutput.setToken(UUID.randomUUID().toString());
		return ApiUtil.buildRes(loginOutput);
	}


}
