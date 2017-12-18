package com.fty1.ippool.service;

import com.fty1.ippool.entity.IpSourceCrawler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IpSourceCrawlerService {

    void gatherIpCrawler(String ip, String port);

    IpSourceCrawler findByCode(String code);

    List<IpSourceCrawler> findUntreated();
}
