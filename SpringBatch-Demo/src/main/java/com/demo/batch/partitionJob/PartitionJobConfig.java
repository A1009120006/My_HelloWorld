package com.demo.batch.partitionJob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PartitionJobConfig {

    /**
     * 构建job并配置step
     * @return
     */
    @Bean
    public Job loadFileJob(@Autowired JobBuilderFactory jobBuilderFactory,
    		@Qualifier("loadFileStep") Step loadFileStep,
    		JobExecutionListener jobListener) {
    	String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
    	return jobBuilderFactory.get(funcName)
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
    			.start(loadFileStep)
    			.build();
    }
    
}
