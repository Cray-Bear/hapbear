package com.fty1.ippool.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ip_spoor_crawler")
public class IpSpoorCrawler {


    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private Long ipSourceCrawlerNum;
    private Long ipEvalueCrawlerNum;
    private Long ipDiscardCrawlerNum;
    private Long ipProductCrawlerNum;
    private Long ipLogCrawlerNum;
    private Long ipTestCrawlerNum;
    private Long ipSpoorCrawlerNum;
    private Integer year;
    private Integer month;
    private Integer day;
    /**
     * 创建时间
     */
    private Date creationTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIpSourceCrawlerNum() {
        return ipSourceCrawlerNum;
    }

    public void setIpSourceCrawlerNum(Long ipSourceCrawlerNum) {
        this.ipSourceCrawlerNum = ipSourceCrawlerNum;
    }

    public Long getIpEvalueCrawlerNum() {
        return ipEvalueCrawlerNum;
    }

    public void setIpEvalueCrawlerNum(Long ipEvalueCrawlerNum) {
        this.ipEvalueCrawlerNum = ipEvalueCrawlerNum;
    }

    public Long getIpDiscardCrawlerNum() {
        return ipDiscardCrawlerNum;
    }

    public void setIpDiscardCrawlerNum(Long ipDiscardCrawlerNum) {
        this.ipDiscardCrawlerNum = ipDiscardCrawlerNum;
    }

    public Long getIpProductCrawlerNum() {
        return ipProductCrawlerNum;
    }

    public void setIpProductCrawlerNum(Long ipProductCrawlerNum) {
        this.ipProductCrawlerNum = ipProductCrawlerNum;
    }

    public Long getIpLogCrawlerNum() {
        return ipLogCrawlerNum;
    }

    public void setIpLogCrawlerNum(Long ipLogCrawlerNum) {
        this.ipLogCrawlerNum = ipLogCrawlerNum;
    }

    public Long getIpTestCrawlerNum() {
        return ipTestCrawlerNum;
    }

    public void setIpTestCrawlerNum(Long ipTestCrawlerNum) {
        this.ipTestCrawlerNum = ipTestCrawlerNum;
    }

    public Long getIpSpoorCrawlerNum() {
        return ipSpoorCrawlerNum;
    }

    public void setIpSpoorCrawlerNum(Long ipSpoorCrawlerNum) {
        this.ipSpoorCrawlerNum = ipSpoorCrawlerNum;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
