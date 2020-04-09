package com.demo.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class DefaultJobListener extends JobExecutionListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(DefaultJobListener.class);
    private long startTime;
    
    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();

        logger.info(" DefaultJobListener job[{}] start run.", jobExecution.getJobInstance().getJobName(), jobExecution.getJobParameters());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("JOB STATUS : {}", jobExecution.getStatus());
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("JOB FINISHED");
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            logger.info("JOB FAILED");
        }
        logger.info("Job Cost Time : {}ms" , (System.currentTimeMillis() - startTime));
        logger.info(" DefaultJobListener job[{}] end run.", jobExecution.getJobInstance().getJobName());
    }
}
