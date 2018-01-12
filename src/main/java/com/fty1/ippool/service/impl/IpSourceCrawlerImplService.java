package com.fty1.ippool.service.impl;


import com.fty1.ippool.constans.IPPoolConstant;
import com.fty1.ippool.entity.IpEvaluateCrawler;
import com.fty1.ippool.entity.IpLogCrawler;
import com.fty1.ippool.entity.IpSourceCrawler;
import com.fty1.ippool.entity.IpTestCrawler;
import com.fty1.ippool.repository.IpLogCrawlerRepository;
import com.fty1.ippool.repository.IpSourceCrawlerRepository;
import com.fty1.ippool.service.IpSourceCrawlerService;
import com.fty1.ippool.service.IpTestCrawlerService;
import com.fty1.utils.UniqueCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class IpSourceCrawlerImplService implements IpSourceCrawlerService {

    @Autowired
    private IpSourceCrawlerRepository ipSourceCrawlerRepository;


    @Autowired
    private IpLogCrawlerRepository ipLogCrawlerRepository;


    @Autowired
    private IpTestCrawlerService ipTestCrawlerService;


    @Override
    @Async("ipPoolExecutor")
    @Transactional(readOnly = false)
    public void gatherIpCrawler(String ip, String port) {

        String code = UniqueCodeUtils.md5(ip,port);
        IpSourceCrawler temIp = findByCode(code);
        if(temIp != null){
            return;
        }

        Date date = new Date();
        IpSourceCrawler ipSourceCrawler = new IpSourceCrawler();
        ipSourceCrawler.setIp(ip);
        ipSourceCrawler.setPort(port);
        ipSourceCrawler.setCode(code);
        ipSourceCrawler.setStatus(IPPoolConstant.IPCRAWLER_STATUS_UNTREATED);
        ipSourceCrawler.setPerformFlage(null);
        ipSourceCrawler.setCreationTime(date);
        ipSourceCrawlerRepository.save(ipSourceCrawler);


        IpLogCrawler ipLogCrawler = ipLogCrawlerRepository.findByCode(code);

        if(ipLogCrawler != null){
            return;
        }

        ipLogCrawler = new IpLogCrawler();
        ipLogCrawler.setIp(ip);
        ipLogCrawler.setPort(port);
        ipLogCrawler.setCode(code);
        ipLogCrawlerRepository.save(ipLogCrawler);
    }
    @Override
    public IpSourceCrawler findByCode(String code) {
        return ipSourceCrawlerRepository.findByCode(code);
    }

    @Override
    public List<IpSourceCrawler> findUntreated() {
        return ipSourceCrawlerRepository.findTop300ByStatus(IPPoolConstant.IPCRAWLER_STATUS_UNTREATED);
    }

    @Override
    public void ipCrawler() {
        //负责抓取IP到IP测试池
        List<IpTestCrawler> ipTestCrawlers=ipTestCrawlerService.findDiscard();
        if(ipTestCrawlers == null || ipTestCrawlers.isEmpty()){
            return;
        }
        for(IpTestCrawler ipTestCrawler:ipTestCrawlers){
            ipCrawler(ipTestCrawler);
        }
    }


    @Async("ipPoolExecutor")
    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public void ipCrawler(IpTestCrawler ipTestCrawler) {

        String code = ipTestCrawler.getCode();
        IpSourceCrawler temp = findByCode(code);
        if(temp != null){
            return;
        }

        IpSourceCrawler ipSourceCrawler = new IpSourceCrawler();
        ipSourceCrawler.setIp(ipTestCrawler.getIp());
        ipSourceCrawler.setPort(ipTestCrawler.getPort());
        ipSourceCrawler.setCode(ipTestCrawler.getCode());
        ipSourceCrawler.setCreationTime(new Date());
        ipSourceCrawler.setStatus(IPPoolConstant.IPCRAWLER_STATUS_UNTREATED);
        ipSourceCrawlerRepository.save(ipSourceCrawler);
    }
}
