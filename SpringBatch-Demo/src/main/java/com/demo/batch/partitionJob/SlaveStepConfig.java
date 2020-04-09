package com.demo.batch.partitionJob;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.demo.batch.entity.TestBatch;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableBatchProcessing
@Slf4j
public class SlaveStepConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private DataSource dataSource;
	
	@Bean(name = "slaveStep")
	public Step slaveStep() {
		log.info("...........called slave .........");

		return stepBuilderFactory.get("slaveStep").<TestBatch, TestBatch> chunk(100).reader(slaveReader(null))
				.processor(slaveProcessor(null)).writer(slaveWriter(null)).build();
	}


	@Bean
	@StepScope
	public SlaveProcessor slaveProcessor(@Value("#{stepExecutionContext[txnDt]}") String txnDt) {
		log.info("********called slave processor **********");
		SlaveProcessor TestBatchProcessor = new SlaveProcessor();
		TestBatchProcessor.setThreadName(txnDt);
		return TestBatchProcessor;
	}

	@Bean
	@StepScope
	public JdbcPagingItemReader<TestBatch> slaveReader(@Value("#{stepExecutionContext[txnDt]}") final String txnDt) {
		log.info("slaveReader start " + txnDt);
		JdbcPagingItemReader<TestBatch> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(dataSource);
		reader.setQueryProvider(queryProvider());
		Map<String, Object> parameterValues = new HashMap<>();
		parameterValues.put("txnDt", txnDt);
		log.info("Parameter Value " + txnDt + parameterValues);
		reader.setParameterValues(parameterValues);
		reader.setPageSize(1000);
		reader.setRowMapper(new BeanPropertyRowMapper<TestBatch>() {
			{
				setMappedClass(TestBatch.class);
			}
		});
		log.info("slaveReader end " + txnDt);
		return reader;
	}

	@Bean
	public PagingQueryProvider queryProvider() {
		log.info("queryProvider start ");
		SqlPagingQueryProviderFactoryBean provider = new SqlPagingQueryProviderFactoryBean();
		provider.setDataSource(dataSource);
		provider.setSelectClause("SELECT TXN_DT, TXN_SN, CORE_TXN_DT, CORE_TXN_SSN, TXN_AMT");
		provider.setFromClause("FROM test_batch");
		provider.setWhereClause("where TXN_DT = :txnDt");
		provider.setSortKey("TXN_SN");
		log.info("queryProvider end ");
		try {
			return provider.getObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Bean
	@StepScope
	public FlatFileItemWriter<TestBatch> slaveWriter(@Value("#{stepExecutionContext[txnDt]}") final String txnDt) {
		FlatFileItemWriter<TestBatch> reader = new FlatFileItemWriter<>();
		reader.setResource(new FileSystemResource("target/Partition.processed" + txnDt + ".csv"));
		// reader.setAppendAllowed(false);
		reader.setLineAggregator(new DelimitedLineAggregator<TestBatch>() {
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<TestBatch>() {
					{
						setNames(new String[] { "txnDt", "txnSn", "coreTxnDt", "coreTxnSsn", "txnAmt" });
					}
				});
			}
		});
		return reader;
	}
}
