package com.fty1.ippool.service.impl;

import com.fty1.ippool.constans.IPPoolConstant;
import com.fty1.ippool.entity.IpEvaluateCrawler;
import com.fty1.ippool.entity.IpProductCrawler;
import com.fty1.ippool.repository.IpEvaluateCrawlerRepository;
import com.fty1.ippool.repository.IpProductCrawlerRepository;
import com.fty1.ippool.service.IpEvaluateCrawlerService;
import com.fty1.ippool.service.IpProductCrawerService;
import com.fty1.ippool.vo.IpProductCrawlerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class IpProductCrawerImplService implements IpProductCrawerService {


    @Autowired
    private IpProductCrawlerRepository ipProductCrawlerRepository;


    @Autowired
    private IpEvaluateCrawlerService ipEvaluateCrawlerService;


    @Autowired
    private IpEvaluateCrawlerRepository ipEvaluateCrawlerRepository;

    @Override
    public List<IpProductCrawlerVO> findAll(PageRequest pageRequest) {
        Page<IpProductCrawler> page = ipProductCrawlerRepository.findAll(pageRequest);

        List<IpProductCrawler> ipProCrawlers = page.getContent();
        if(ipProCrawlers == null || ipProCrawlers.isEmpty()){
            return null;
        }

        List<IpProductCrawlerVO> ipProCrawlerVOs = new ArrayList<>();

        for(IpProductCrawler ipProCrawler:ipProCrawlers){

            IpProductCrawlerVO ipProCrawlerVO = new IpProductCrawlerVO();
            ipProCrawlerVO.setIp(ipProCrawler.getIp());
            ipProCrawlerVO.setPort(ipProCrawler.getPort());
            ipProCrawlerVOs.add(ipProCrawlerVO);
        }

        return ipProCrawlerVOs;
    }

    @Override
    public void ipCrawler() {
        List<IpEvaluateCrawler> ipEvaluateCrawlers = ipEvaluateCrawlerService.findProduct();

        if(ipEvaluateCrawlers == null || ipEvaluateCrawlers.isEmpty()){
            return;
        }

        for(IpEvaluateCrawler ipEvaluateCrawler:ipEvaluateCrawlers){
            ipCrawler(ipEvaluateCrawler);
        }
    }

    @Async
    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public void ipCrawler(IpEvaluateCrawler ipEvaluateCrawler) {


        if(IPPoolConstant.IPCRAWLER_STATUS_PRODUCT == ipEvaluateCrawler.getStatus()){
            ipEvaluateCrawlerRepository.delete(ipEvaluateCrawler);
        }


        IpProductCrawler ipProductCrawler = findByCode(ipEvaluateCrawler.getCode());
        if(ipProductCrawler != null){
            return;
        }

        ipProductCrawler = new IpProductCrawler();
        ipProductCrawler.setIp(ipEvaluateCrawler.getIp());
        ipProductCrawler.setPort(ipEvaluateCrawler.getPort());
        ipProductCrawler.setCode(ipEvaluateCrawler.getCode());
        ipProductCrawlerRepository.save(ipProductCrawler);

    }

    private IpProductCrawler findByCode(String code) {
        return ipProductCrawlerRepository.findByCode(code);
    }
}
