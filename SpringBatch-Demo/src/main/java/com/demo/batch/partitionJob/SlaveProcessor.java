package com.demo.batch.partitionJob;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.ListableJobLocator;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.demo.batch.entity.TestBatch;

@Component
public class SlaveProcessor implements ItemProcessor<TestBatch, TestBatch> {

	private final static Logger logger = LoggerFactory.getLogger(SlaveProcessor.class);

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	
	private String threadName;
	
	@Override
	public TestBatch process(TestBatch item) throws Exception {
		logger.info("slave process {}={}",threadName , JSON.toJSONString(item));
		
		return item;
	}
}
