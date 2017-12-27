package com.fty1.ippool.schedule;


import com.fty1.ippool.service.IpSpoorCrawlerService;
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
public class IPSpoorSchedule {


    private Logger logger = LoggerFactory.getLogger(IPSpoorSchedule.class);

    public final static long ONE_MINUTE =  10 * 100;

    @Autowired
    private IpSpoorCrawlerService ipSpoorCrawlerService;


    @Scheduled(fixedDelay=ONE_MINUTE)
    @Async("iPPoolScheduleExecutor")
    public void iPSpoorScheduleFixedDelayJob(){
        logger.info("schedule|iPSpoorScheduleFixedDelayJob|start|"+System.currentTimeMillis());

        ipSpoorCrawlerService.ipSpoor();

        logger.info("schedule|iPSpoorScheduleFixedDelayJob|stop|"+System.currentTimeMillis());
    }
}
