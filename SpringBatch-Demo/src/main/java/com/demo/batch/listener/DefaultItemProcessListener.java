package com.demo.batch.listener;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

/**
 * @Author:95780
 * @Date: 18:05 2019/12/13
 * @Description:
 */
@Component
public class DefaultItemProcessListener implements ItemProcessListener<Object, Object> {
    private static final Logger logger = LoggerFactory.getLogger(DefaultItemProcessListener.class);

    @Override
    public void beforeProcess(Object o) {
        logger.info(" ====== DefaultItemProcessListener beforeProcess ====== " + o);
    }

    @Override
    public void afterProcess(Object o, Object o2) {
        logger.info(" ====== DefaultItemProcessListener afterProcess ====== " + o);
    }

    @Override
    public void onProcessError(Object o, Exception e) {
        logger.info(" ====== DefaultItemProcessListener onProcessError ====== " + o + " Exception " + e);
    }
}
