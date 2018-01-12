package com.fty1.ippool.service.impl;

import com.fty1.ippool.constans.IPPoolConstant;
import com.fty1.ippool.entity.IpEvaluateCrawler;
import com.fty1.ippool.entity.IpSourceCrawler;
import com.fty1.ippool.repository.IpEvaluateCrawlerRepository;
import com.fty1.ippool.repository.IpSourceCrawlerRepository;
import com.fty1.ippool.service.IpEvaluateCrawlerService;
import com.fty1.ippool.service.IpSourceCrawlerService;
import com.fty1.proxy.CheckSimpleProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class IpEvaluateCrawerImplService implements IpEvaluateCrawlerService {


    @Autowired
    private IpSourceCrawlerService ipSourceCrawlerService;


    @Autowired
    private IpEvaluateCrawlerRepository ipEvaluateCrawlerRepository;

    @Autowired
    private IpSourceCrawlerRepository ipSrcCrawlerRepository;


    @Autowired
    private CheckSimpleProxy checkSimpleProxy;

    @Override
    public void ipCrawler() {
        //负责抓取IP到IP测试池
        List<IpSourceCrawler> sourceCrawlerList=ipSourceCrawlerService.findUntreated();
        if(sourceCrawlerList == null || sourceCrawlerList.isEmpty()){
            return;
        }
        for(IpSourceCrawler ipSourceCrawler:sourceCrawlerList){
            ipCrawler(ipSourceCrawler);
        }
    }

    @Override
    public void ipEvaluate() {
        List<IpEvaluateCrawler> evaluateCrawlers=findUntreated();
        if(evaluateCrawlers == null || evaluateCrawlers.isEmpty()){
            return;
        }
        for(IpEvaluateCrawler ipEvaluateCrawler:evaluateCrawlers){
            ipEvaluate(ipEvaluateCrawler);
        }
    }

    @Override
    public List<IpEvaluateCrawler> findUntreated() {
        return ipEvaluateCrawlerRepository.findTop300ByStatus(IPPoolConstant.IPCRAWLER_STATUS_UNTREATED);
    }

    @Override
    public List<IpEvaluateCrawler> findProduct() {
        return ipEvaluateCrawlerRepository.findTop300ByStatus(IPPoolConstant.IPCRAWLER_STATUS_PRODUCT);
    }

    @Override
    public List<IpEvaluateCrawler> findDiscard() {
        return ipEvaluateCrawlerRepository.findTop300ByStatus(IPPoolConstant.IPCRAWLER_STATUS_DISCARDED);
    }

    @Async("ipPoolExecutor")
    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public void ipEvaluate(IpEvaluateCrawler ipEvaluateCrawler) {

        String code = ipEvaluateCrawler.getCode();
        IpEvaluateCrawler temp = ipEvaluateCrawlerRepository.findByCode(code);

        if(temp == null){
            return;
        }

        if(IPPoolConstant.IPCRAWLER_STATUS_UNTREATED != temp.getStatus()){
            return;
        }

        boolean isValid = isValid(ipEvaluateCrawler);
        int status = isValid?IPPoolConstant.IPCRAWLER_STATUS_PRODUCT:IPPoolConstant.IPCRAWLER_STATUS_DISCARDED;
        ipEvaluateCrawler.setStatus(status);
        ipEvaluateCrawlerRepository.save(ipEvaluateCrawler);
    }

    private boolean isValid(IpEvaluateCrawler ipEvaluateCrawler) {

        //TODO 代理IP检测是否可用

      /*  String port = ipEvaluateCrawler.getPort();
        int num = Integer.valueOf(port);
        return num%2 == 0;*/
        return checkSimpleProxy.proxyCheck(ipEvaluateCrawler.getIp(),ipEvaluateCrawler.getPort());

    }


    @Async("ipPoolExecutor")
    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public void ipCrawler(IpSourceCrawler ipSourceCrawler) {

        String code = ipSourceCrawler.getCode();

        ipSrcCrawlerRepository.delete(ipSourceCrawler);

        IpEvaluateCrawler temp=ipEvaluateCrawlerRepository.findByCode(code);
        if(temp != null){
            return;
        }

        IpEvaluateCrawler ipEvaluateCrawler = new IpEvaluateCrawler();
        ipEvaluateCrawler.setIp(ipSourceCrawler.getIp());
        ipEvaluateCrawler.setPort(ipSourceCrawler.getPort());
        ipEvaluateCrawler.setCode(ipSourceCrawler.getCode());
        ipEvaluateCrawler.setCreationTime(new Date());
        ipEvaluateCrawler.setStatus(IPPoolConstant.IPCRAWLER_STATUS_UNTREATED);
        ipEvaluateCrawlerRepository.save(ipEvaluateCrawler);

    }
}
