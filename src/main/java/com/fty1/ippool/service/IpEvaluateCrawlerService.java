package com.fty1.ippool.service;

import com.fty1.ippool.entity.IpEvaluateCrawler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IpEvaluateCrawlerService {

    void ipCrawler();

    void ipEvaluate();

    List<IpEvaluateCrawler> findUntreated();

    List<IpEvaluateCrawler> findProduct();
}
