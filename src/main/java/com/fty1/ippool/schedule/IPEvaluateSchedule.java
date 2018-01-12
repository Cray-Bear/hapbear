package com.fty1.ippool.schedule;


import com.fty1.ippool.service.IpEvaluateCrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 对采集的代理进行IP评估
 */
@Component
public class IPEvaluateSchedule {


    private Logger logger = LoggerFactory.getLogger(IPEvaluateSchedule.class);

    public final static long ONE_MINUTE =  10 * 1000;

    @Autowired
    private IpEvaluateCrawlerService ipEvaluateCrawlerService;


    @Scheduled(fixedDelay=ONE_MINUTE)
    @Async("iPPoolScheduleExecutor")
    public void iPEvaluateScheduleFixedDelayJob(){
        logger.info("schedule|iPEvaluateScheduleFixedDelayJob|start|"+System.currentTimeMillis());

        try {
            ipEvaluateCrawlerService.ipEvaluate();
        } catch (Exception e) {
            logger.info("iPEvaluateScheduleFixedDelayJob",e);
        }


        logger.info("schedule|iPEvaluateScheduleFixedDelayJob|stop|"+System.currentTimeMillis());
    }
}
