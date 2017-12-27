package com.fty1.ippool.service.impl;

import com.fty1.ippool.constans.IPPoolConstant;
import com.fty1.ippool.entity.IpEvaluateCrawler;
import com.fty1.ippool.entity.IpSpoorCrawler;
import com.fty1.ippool.entity.view.ViewIpCrawler;
import com.fty1.ippool.repository.*;
import com.fty1.ippool.repository.view.ViewIpCrawlerRepository;
import com.fty1.ippool.service.IpSpoorCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Component
public class IpSpoorCrawlerImplService implements IpSpoorCrawlerService{


    @Autowired
    private IpLogCrawlerRepository ipLogCrawlerRepository;

    @Autowired
    private IpSourceCrawlerRepository ipSourceCrawlerRepository;

    @Autowired
    private IpEvaluateCrawlerRepository ipEvaluateCrawlerRepository;

    @Autowired
    private IpDiscardCrawlerRepository ipDiscardCrawlerRepository;

    @Autowired
    private IpProductCrawlerRepository ipProductCrawlerRepository;

    @Autowired
    private IpTestCrawlerRepository ipTestCrawlerRepository;

    @Autowired
    private IpSpoorCrawlerRepository ipSpoorCrawlerRepository;




    @Autowired
    private ViewIpCrawlerRepository viewIpCrawlerRepository;
    

    @Async("ipPoolExecutor")
    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public void ipSpoorByIpViews() {

        List<ViewIpCrawler> views = viewIpCrawlerRepository.findAll();

        if(views == null || views.isEmpty()){
            return;
        }

        IpSpoorCrawler ipSpoorCrawler = buildByViewIpCrawlers(views);

        if(ipSpoorCrawler == null){
            return;
        }
        ipSpoorCrawlerRepository.save(ipSpoorCrawler);
    }

    @Override
    @Async("ipPoolExecutor")
    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public void ipSpoor() {

        IpSpoorCrawler ipSpoorCrawler = new IpSpoorCrawler();
        ipSpoorCrawler.setIpLogCrawlerNum(ipLogCrawlerRepository.count());
        ipSpoorCrawler.setIpSourceCrawlerNum(ipSourceCrawlerRepository.count());
        ipSpoorCrawler.setIpEvalueCrawlerNum(ipEvaluateCrawlerRepository.count());
        ipSpoorCrawler.setIpProductCrawlerNum(ipProductCrawlerRepository.count());
        ipSpoorCrawler.setIpDiscardCrawlerNum(ipDiscardCrawlerRepository.count());
        ipSpoorCrawler.setIpTestCrawlerNum(ipTestCrawlerRepository.count());
        ipSpoorCrawler.setIpSpoorCrawlerNum(ipSpoorCrawlerRepository.count());
        Calendar calendar = Calendar.getInstance();
        ipSpoorCrawler.setYear(calendar.get(Calendar.YEAR));
        ipSpoorCrawler.setMonth(calendar.get(Calendar.MONTH));
        ipSpoorCrawler.setDay(calendar.get(Calendar.DAY_OF_YEAR));
        ipSpoorCrawlerRepository.save(ipSpoorCrawler);
    }


    private IpSpoorCrawler buildByViewIpCrawlers(List<ViewIpCrawler> views) {

        if(views == null || views.isEmpty()){
            return null;
        }

        IpSpoorCrawler ipSpoorCrawler = new IpSpoorCrawler();
        for(ViewIpCrawler viewIpCrawler:views){
            Integer index = viewIpCrawler.getIndex();
            switch (index){
                case 0:{
                    ipSpoorCrawler.setIpLogCrawlerNum(viewIpCrawler.getNum());
                    break;
                }
                case 1:{
                    ipSpoorCrawler.setIpSourceCrawlerNum(viewIpCrawler.getNum());
                    break;
                }
                case 2:{
                    ipSpoorCrawler.setIpEvalueCrawlerNum(viewIpCrawler.getNum());
                    break;
                }
                case 3:{
                    ipSpoorCrawler.setIpProductCrawlerNum(viewIpCrawler.getNum());
                    break;
                }
                case 4:{
                    ipSpoorCrawler.setIpDiscardCrawlerNum(viewIpCrawler.getNum());
                    break;
                }
                case 5:{
                    ipSpoorCrawler.setIpTestCrawlerNum(viewIpCrawler.getNum());
                    break;
                }
                case 6:{
                    ipSpoorCrawler.setIpSpoorCrawlerNum(viewIpCrawler.getNum());
                    break;
                }
            }
        }

        Calendar calendar = Calendar.getInstance();
        ipSpoorCrawler.setYear(calendar.get(Calendar.YEAR));
        ipSpoorCrawler.setMonth(calendar.get(Calendar.MONTH));
        ipSpoorCrawler.setDay(calendar.get(Calendar.DAY_OF_YEAR));
        ipSpoorCrawlerRepository.save(ipSpoorCrawler);

        return ipSpoorCrawler;
    }
}
