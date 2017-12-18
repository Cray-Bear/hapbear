package com.fty1.ippool.repository;

import com.fty1.ippool.entity.IpProductCrawler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpProductCrawlerRepository extends JpaRepository<IpProductCrawler,String> {

    IpProductCrawler findByCode(String code);
}
