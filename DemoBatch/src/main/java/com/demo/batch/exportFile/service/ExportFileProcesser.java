package com.demo.batch.exportFile.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.demo.batch.entity.TestBatch;

@Component
public class ExportFileProcesser implements ItemProcessor<TestBatch, TestBatch> {
	private final static Logger logger = LoggerFactory.getLogger(ExportFileProcesser.class);

	
	@Override
	public TestBatch process(TestBatch item) throws Exception {
		logger.info("load file process :" + JSON.toJSONString(item));
		return item;
	}
}
