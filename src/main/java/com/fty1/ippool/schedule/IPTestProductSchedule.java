package com.fty1.ippool.schedule;


import com.fty1.ippool.service.IpTestCrawlerService;
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
public class IPTestProductSchedule {


    private Logger logger = LoggerFactory.getLogger(IPTestProductSchedule.class);

    public final static long ONE_MINUTE =  10 * 1000;

    @Autowired
    private IpTestCrawlerService ipTestCrawlerService;


    @Scheduled(fixedDelay=ONE_MINUTE)
    @Async("iPPoolScheduleExecutor")
    public void iPDiscardScheduleFixedDelayJob(){
        logger.info("schedule|iPDiscardScheduleFixedDelayJob|start|"+System.currentTimeMillis());

        try {
            ipTestCrawlerService.crawlerIpProduct();
        } catch (Exception e) {
            logger.info("iPDiscardScheduleFixedDelayJob:",e);
        }

        logger.info("schedule|iPDiscardScheduleFixedDelayJob|stop|"+System.currentTimeMillis());
    }
}
