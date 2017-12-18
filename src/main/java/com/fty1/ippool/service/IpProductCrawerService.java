package com.fty1.ippool.service;

import com.fty1.ippool.vo.IpProductCrawlerVO;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IpProductCrawerService {

    List<IpProductCrawlerVO> findAll(PageRequest pageRequest);

    void ipCrawler();
}
