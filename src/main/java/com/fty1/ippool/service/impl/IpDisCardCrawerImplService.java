package com.fty1.ippool.service.impl;

import com.fty1.ippool.constans.IPPoolConstant;
import com.fty1.ippool.entity.IpDiscardCrawler;
import com.fty1.ippool.entity.IpEvaluateCrawler;
import com.fty1.ippool.repository.IpDiscardCrawlerRepository;
import com.fty1.ippool.repository.IpEvaluateCrawlerRepository;
import com.fty1.ippool.service.IpDiscardCrawlerService;
import com.fty1.ippool.service.IpEvaluateCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class IpDisCardCrawerImplService implements IpDiscardCrawlerService {


    @Autowired
    private IpEvaluateCrawlerService ipEvaluateCrawlerService;

    @Autowired
    private IpEvaluateCrawlerRepository ipEvaluateCrawlerRepository;

    @Autowired
    private IpDiscardCrawlerRepository ipDiscardCrawlerRepository;

    @Override
    public void ipDiscard() {

        //负责抓取IP到IP测试池
        List<IpEvaluateCrawler> IpEvaluateCrawler=ipEvaluateCrawlerService.findDiscard();
        if(IpEvaluateCrawler == null || IpEvaluateCrawler.isEmpty()){
            return;
        }
        for(IpEvaluateCrawler ipEvaluateCrawler:IpEvaluateCrawler){
            ipDiscard(ipEvaluateCrawler);
        }
    }


    @Transactional(readOnly = false)
    @Async("ipPoolExecutor")
    public void ipDiscard(IpEvaluateCrawler ipEvaluateCrawler) {
        String code = ipEvaluateCrawler.getCode();
        ipEvaluateCrawlerRepository.delete(ipEvaluateCrawler);

        IpDiscardCrawler temp=ipDiscardCrawlerRepository.findTop300ByCode(code);
        if(temp != null){
            return;
        }

        IpDiscardCrawler ipDiscardCrawler = new IpDiscardCrawler();
        ipDiscardCrawler.setIp(ipEvaluateCrawler.getIp());
        ipDiscardCrawler.setPort(ipEvaluateCrawler.getPort());
        ipDiscardCrawler.setCode(ipEvaluateCrawler.getCode());
        ipDiscardCrawler.setCreationTime(new Date());
        ipDiscardCrawler.setStatus(IPPoolConstant.IPCRAWLER_STATUS_UNTREATED);
        ipDiscardCrawlerRepository.save(ipDiscardCrawler);
    }
}
