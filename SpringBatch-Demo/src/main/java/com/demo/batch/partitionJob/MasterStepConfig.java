package com.demo.batch.partitionJob;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.demo.batch.partitionJob.service.RangePartitioner;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableBatchProcessing
@Slf4j
public class MasterStepConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").tasklet(dummyTask()).build();
	}

	@Bean
	public Tasklet dummyTask() {
		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				log.info("invoke dummyTask");
				return null;
			}
		};
	}

	@Bean
	public Step masterStep(@Qualifier("slaveStep") Step slaveStep) {
		return stepBuilderFactory.get("masterStep").partitioner(slaveStep.getName(), rangePartitioner())
				.partitionHandler(masterSlaveHandler(null)).build();
	}

	@Bean
	public PartitionHandler masterSlaveHandler(@Qualifier("slaveStep") Step slaveStep) {
		TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
		handler.setGridSize(10);
		handler.setTaskExecutor(taskExecutor());
		handler.setStep(slaveStep);
		try {
			handler.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return handler;
	}


	@Bean
	public RangePartitioner rangePartitioner() {
		return new RangePartitioner();
	}

	@Bean
	public SimpleAsyncTaskExecutor taskExecutor() {
		return new SimpleAsyncTaskExecutor();
	}
}
