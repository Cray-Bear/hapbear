package com.fty1.ippool.schedule;


import com.fty1.ippool.service.IpProductCrawerService;
import com.fty1.ippool.service.IpSourceCrawlerService;
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
public class IPSourceCrawlerSchedule {


    private Logger logger = LoggerFactory.getLogger(IPSourceCrawlerSchedule.class);

    public final static long ONE_MINUTE =  10 * 1000;

    @Autowired
    private IpSourceCrawlerService ipSourceCrawlerService;


    @Scheduled(fixedDelay=ONE_MINUTE)
    @Async("iPPoolScheduleExecutor")
    public void iPProductCrawlerScheduleFixedDelayJob(){
        logger.info("schedule|iPProductCrawlerScheduleFixedDelayJob|start|"+System.currentTimeMillis());

        ipSourceCrawlerService.ipCrawler();

        logger.info("schedule|iPProductCrawlerScheduleFixedDelayJob|stop|"+System.currentTimeMillis());
    }
}
