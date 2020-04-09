package com.demo.batch.partitionJob.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RangePartitioner implements Partitioner {

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		log.info("partition called gridsize= " + gridSize);

		Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();

		ExecutionContext value1 = new ExecutionContext();

		value1.putString("txnDt", "20200329");
		value1.putString("name", "Name20200329");
		result.put("partition20200329", value1);

		ExecutionContext value2 = new ExecutionContext();
		value2.putString("txnDt", "20200328");
		value2.putString("name", "Name20200328");
		result.put("partition20200328", value2);

		return result;
	}
}