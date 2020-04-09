package com.demo.batch.exportFile;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExportFileJobConfig {

    /**
     * 构建job并配置step
     * @return
     */
    @Bean
    public Job exportFileJob(@Autowired JobBuilderFactory jobBuilderFactory,
    		@Qualifier("exportFileStep") Step exportFileStep,
    		JobExecutionListener jobListener) {
    	return jobBuilderFactory.get("exportFileJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
    			.start(exportFileStep)
    			.build();
    }
    
}
