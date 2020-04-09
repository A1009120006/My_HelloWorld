package com.demo.batch.exportFile;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.batch.entity.TestBatch;

@Configuration
public class ExportFileStepConfig {


    @Bean(name = "exportFileStep")
    public Step exportFileStep(@Autowired StepBuilderFactory stepBuilderFactory,
    		@Qualifier("exportFileReader") ItemReader<TestBatch> itemReader, 
    		@Qualifier("exportFileWriter") ItemWriter<TestBatch> itemWriter, 
    		@Qualifier("exportFileProcesser") ItemProcessor<TestBatch, TestBatch> itemProcessor) {

        return stepBuilderFactory.get("exportFileStep")
        		.<TestBatch, TestBatch>chunk(1)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }
    
}
