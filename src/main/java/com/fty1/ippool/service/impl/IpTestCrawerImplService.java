package com.fty1.ippool.service.impl;

import com.fty1.ippool.repository.IpProductCrawlerRepository;
import com.fty1.ippool.service.IpTestCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IpTestCrawerImplService implements IpTestCrawlerService {


    @Autowired
    private IpProductCrawlerRepository ipProCrawlerRepository;

}
