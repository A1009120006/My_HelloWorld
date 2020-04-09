package com.demo.batch.loadFile.service;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

import com.demo.batch.entity.TestBatch;

@Configuration
public class LoadFileReaderConfig {
	// @Value("#{jobParameters ['filePath']}") String filePath;
	@Bean
	public FlatFileItemReader<TestBatch> loadFileReader() {
		return new FlatFileItemReaderBuilder<TestBatch>().name("loadFileReader").resource(new ClassPathResource("load_file.txt"))
				.lineTokenizer(new DelimitedLineTokenizer("|") {
					{
						setNames(new String[] { "txnDt", "txnSn", "coreTxnDt", "coreTxnSsn", "txnAmt" });
					}
				}).fieldSetMapper(new FieldSetMapper<TestBatch>() {
					@Override
					public TestBatch mapFieldSet(FieldSet paramFieldSet) throws BindException {
						TestBatch testBatch = new TestBatch();
						testBatch.setTxnDt(paramFieldSet.readString("txnDt"));
						testBatch.setTxnSn(paramFieldSet.readString("txnSn"));
						testBatch.setCoreTxnDt(paramFieldSet.readString("coreTxnDt"));
						testBatch.setCoreTxnSsn(paramFieldSet.readString("coreTxnSsn"));
						testBatch.setTxnAmt(paramFieldSet.readBigDecimal("txnAmt"));
						return testBatch;
					}
				}).linesToSkip(1).build();
	}

}
