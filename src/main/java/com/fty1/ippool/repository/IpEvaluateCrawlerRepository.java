package com.fty1.ippool.repository;

import com.fty1.ippool.entity.IpEvaluateCrawler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IpEvaluateCrawlerRepository extends JpaRepository<IpEvaluateCrawler,String> {

    IpEvaluateCrawler findByCode(String code);

    List<IpEvaluateCrawler> findByStatus(int ipcrawlerStatusUntreated);
}
