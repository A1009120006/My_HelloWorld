package com.demo;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.ListableJobLocator;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.batch.BatchApplication;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BatchApplication.class)
@EnableBatchProcessing
@Slf4j
public class BatchAppTest {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    ListableJobLocator jobLocator;

    @Test
    public void job01000Test() throws Exception {
        log.info("====== BEGIN ======   ");
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("param1", "value2")
                .toJobParameters();
        Collection<String> jobNames = jobLocator.getJobNames();
        log.info("names:" + jobNames.toString());
        JobExecution run = jobLauncher.run(jobLocator.getJob("partitionJob"), jobParameters);
        BatchStatus status = run.getStatus();
        
        log.info("====== END  ======   " + status);
        log.info(run.toString());
    }
}
