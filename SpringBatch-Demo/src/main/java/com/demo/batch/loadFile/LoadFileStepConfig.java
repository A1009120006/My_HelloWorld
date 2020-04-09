package com.demo.batch.loadFile;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.batch.entity.TestBatch;

@Configuration
public class LoadFileStepConfig {


    @Bean(name = "loadFileStep")
    public Step loadFileStep(@Autowired StepBuilderFactory stepBuilderFactory,
    		@Qualifier("loadFileReader") FlatFileItemReader<TestBatch> itemReader, 
    		@Qualifier("loadFileWriter") ItemWriter<TestBatch> itemWriter, 
    		@Qualifier("loadFileProcesser") ItemProcessor<TestBatch, TestBatch> itemProcessor) {
        return stepBuilderFactory.get("loadFileStep")
        		.<TestBatch, TestBatch>chunk(1)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }
    
}
