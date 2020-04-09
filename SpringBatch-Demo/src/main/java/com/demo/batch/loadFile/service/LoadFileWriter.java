package com.demo.batch.loadFile.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.batch.entity.TestBatch;
import com.demo.batch.service.ITestBatchService;

@Component
public class LoadFileWriter implements ItemWriter<TestBatch>{
	final static Logger logger = LoggerFactory.getLogger(LoadFileWriter.class);
	
    @Autowired
    private ITestBatchService testBatchService;
    
	@Override
	public void write(List<? extends TestBatch> list) throws Exception {
		logger.info("load file write: " + list.size());
		List<TestBatch> saveList = new ArrayList<>();
		for (TestBatch item : list) {
			saveList.add(item);
		}
		testBatchService.saveBatch(saveList);
		logger.info("开始更新卡处理状态======END======");
		
	}
}
