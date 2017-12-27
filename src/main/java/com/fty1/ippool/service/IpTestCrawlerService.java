package com.fty1.ippool.service;

import com.fty1.ippool.entity.IpTestCrawler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IpTestCrawlerService {

    void crawlerIpDiscard();

    void crawlerIpProduct();

    List<IpTestCrawler> findDiscard();
}
