package com.fty1.ippool.schedule;


import com.fty1.ippool.service.IpProductCrawerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 对采集的代理进行IP评估
 */
@Component
public class IPProductCrawlerSchedule {


    private Logger logger = LoggerFactory.getLogger(IPProductCrawlerSchedule.class);

    public final static long ONE_MINUTE =  60 * 1000;

    @Autowired
    private IpProductCrawerService ipProductCrawerService;


    @Scheduled(fixedDelay=ONE_MINUTE)
    public void fixedDelayJob(){
        logger.info("schedule|fixedDelayJob|start|"+System.currentTimeMillis());

        ipProductCrawerService.ipCrawler();

        logger.info("schedule|fixedDelayJob|stop|"+System.currentTimeMillis());
    }
}
