package com.fty1.ippool.repository;

import com.fty1.ippool.entity.IpSourceCrawler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IpSourceCrawlerRepository extends JpaRepository<IpSourceCrawler,String> {

    IpSourceCrawler findByCode(String code);

    List<IpSourceCrawler> findByStatus(int ipcrawlerStatusUntreated);
}
