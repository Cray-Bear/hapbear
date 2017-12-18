package com.fty1.ippool.repository;

import com.fty1.ippool.entity.IpLogCrawler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpLogCrawlerRepository extends JpaRepository<IpLogCrawler,String> {

    IpLogCrawler findByCode(String code);
}
