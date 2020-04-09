package com.demo.batch.exportFile.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.demo.batch.entity.TestBatch;

@Configuration
public class ExportFileReaderConfig {
	
//	@Value("#{jobParameters ['date']}") String date
	
    @Bean
    @StepScope
    public JdbcCursorItemReader<TestBatch> exportFileReader(@Autowired DataSource dataSource    		){
    	
    	JdbcCursorItemReader<TestBatch> reader = new JdbcCursorItemReader<TestBatch>();
    	reader.setDataSource(dataSource);
    	reader.setSql("SELECT TXN_DT, TXN_SN, CORE_TXN_DT, CORE_TXN_SSN, TXN_AMT FROM test_batch where TXN_DT = ? ");
    	reader.setPreparedStatementSetter(new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				arg0.setString(1, "20200329");
			}
		});
    	reader.setRowMapper(new RowMapper<TestBatch>() {
					@Override
					public TestBatch mapRow(ResultSet arg0, int arg1) throws SQLException {
						TestBatch testBatch = new TestBatch();
						testBatch.setTxnSn(arg0.getString("TXN_SN"));
						testBatch.setTxnDt(arg0.getString("TXN_DT"));
						testBatch.setTxnAmt(arg0.getBigDecimal("TXN_AMT"));
						testBatch.setCoreTxnSsn(arg0.getString("CORE_TXN_SSN"));
						testBatch.setCoreTxnDt(arg0.getString("CORE_TXN_DT"));
						return testBatch;
					}
		});
    	return reader;
    }
}
