package com.demo.batch.partitionJob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class PartitionerJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Bean
	public Job partitionJob(@Qualifier("masterStep") Step masterStep,
			@Qualifier("step2") Step step2) {
		return jobBuilderFactory.get("partitionJob").incrementer(new RunIdIncrementer()).start(masterStep)
				.next(step2).build();
	}

}