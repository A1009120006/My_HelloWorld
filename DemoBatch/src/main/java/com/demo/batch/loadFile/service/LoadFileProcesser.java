package com.demo.batch.loadFile.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.demo.batch.entity.TestBatch;

@Component
public class LoadFileProcesser implements ItemProcessor<TestBatch, TestBatch> {
	private final static Logger logger = LoggerFactory.getLogger(LoadFileProcesser.class);

	
	@Override
	public TestBatch process(TestBatch item) throws Exception {
		logger.info("load file process :" + JSON.toJSONString(item));
		return item;
	}
}
