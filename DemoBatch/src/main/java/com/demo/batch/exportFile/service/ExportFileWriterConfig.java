package com.demo.batch.exportFile.service;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.demo.batch.entity.TestBatch;

@Configuration
public class ExportFileWriterConfig {

	@Bean
	public FlatFileItemWriter<TestBatch> exportFileWriter() {
		FlatFileItemWriterBuilder<TestBatch> builder = new FlatFileItemWriterBuilder<TestBatch>()
		        .name("exportFileWriter")
		        .resource(new FileSystemResource("target/export_file.txt"))
		        ;
		builder.delimited().delimiter("|").names(new String[]{"txnDt", "txnSn", "coreTxnDt", "coreTxnSsn", "txnAmt"});
		return builder.build();
	}
}
