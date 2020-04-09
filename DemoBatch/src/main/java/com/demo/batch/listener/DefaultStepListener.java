package com.demo.batch.listener;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @Author:95780
 * @Date: 11:19 2019/12/13
 * @Description: 对Step的监听实现类
 */
@Component
public class DefaultStepListener implements StepExecutionListener {
    private static final Logger logger = LoggerFactory.getLogger(DefaultStepListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info(" ====== DefaultStepLIstener beforeStep ====== " + stepExecution);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info(" ====== DefaultStepLIstener afterStep ====== " + stepExecution);
        ExitStatus exitStatus = stepExecution.getExitStatus();
        return exitStatus;
    }
}
