package com.fty1.ippool.service.impl;

import com.fty1.ippool.constans.IPPoolConstant;
import com.fty1.ippool.entity.*;
import com.fty1.ippool.repository.IpDiscardCrawlerRepository;
import com.fty1.ippool.repository.IpEvaluateCrawlerRepository;
import com.fty1.ippool.repository.IpProductCrawlerRepository;
import com.fty1.ippool.repository.IpTestCrawlerRepository;
import com.fty1.ippool.service.IpEvaluateCrawlerService;
import com.fty1.ippool.service.IpTestCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class IpTestCrawerImplService implements IpTestCrawlerService {


    @Autowired
    private IpProductCrawlerRepository ipProCrawlerRepository;

    @Autowired
    private IpTestCrawlerRepository ipTestCrawlerRepository;

    @Autowired
    private IpEvaluateCrawlerRepository ipEvaluateCrawlerRepository;


    @Autowired
    private IpDiscardCrawlerRepository ipDiscardCrawlerRepository;


    @Override
    public void crawlerIpDiscard() {
        List<IpDiscardCrawler> ipDiscardCrawlers = ipDiscardCrawlerRepository.findAll();

        if(ipDiscardCrawlers == null || ipDiscardCrawlers.isEmpty()){
            return;
        }

        for(IpDiscardCrawler ipDiscardCrawler:ipDiscardCrawlers){
            crawlerIpDiscard(ipDiscardCrawler);
        }
    }


    @Async("ipPoolExecutor")
    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public void crawlerIpDiscard(IpDiscardCrawler ipDiscardCrawler) {

        String code = ipDiscardCrawler.getCode();
        IpTestCrawler temp = findByCode(code);
        if(temp != null){
            return;
        }

        IpTestCrawler ipTestCrawler = new IpTestCrawler();
        ipTestCrawler.setIp(ipDiscardCrawler.getIp());
        ipTestCrawler.setPort(ipDiscardCrawler.getPort());
        ipTestCrawler.setCode(ipDiscardCrawler.getCode());
        ipTestCrawler.setCreationTime(new Date());
        ipTestCrawler.setStatus(IPPoolConstant.IPCRAWLER_STATUS_UNTREATED);
        ipTestCrawlerRepository.save(ipTestCrawler);
    }

    @Override
    public void crawlerIpProduct() {
        List<IpProductCrawler> products = ipProCrawlerRepository.findAll();

        if(products == null || products.isEmpty()){
            return;
        }

        for(IpProductCrawler ipProductCrawler:products){
            crawlerIpProduct(ipProductCrawler);
        }
    }

    @Async("ipPoolExecutor")
    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public void crawlerIpProduct(IpProductCrawler ipProductCrawler) {

        String code = ipProductCrawler.getCode();
        IpTestCrawler temp = findByCode(code);
        if(temp != null){
            return;
        }

        IpTestCrawler ipTestCrawler = new IpTestCrawler();
        ipTestCrawler.setIp(ipProductCrawler.getIp());
        ipTestCrawler.setPort(ipProductCrawler.getPort());
        ipTestCrawler.setCode(ipProductCrawler.getCode());
        ipTestCrawler.setCreationTime(new Date());
        ipTestCrawler.setStatus(IPPoolConstant.IPCRAWLER_STATUS_UNTREATED);
        ipTestCrawlerRepository.save(ipTestCrawler);
    }

    public IpTestCrawler findByCode(String code) {
        return ipTestCrawlerRepository.findByCode(code);
    }


    @Override
    public List<IpTestCrawler> findDiscard() {
        return ipTestCrawlerRepository.findAll();
    }
}
