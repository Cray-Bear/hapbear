package com.fty1.ippool.entity;

import com.fty1.ippool.constans.IPPoolConstant;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ip_discard_crawler")
public class IpDiscardCrawler {


    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String ip;

    private String port;

    /**
     * 处理唯一码
     */
    private String code;

    private Integer status = IPPoolConstant.IPCRAWLER_STATUS_UNTREATED;

    /**
     * 处理批次
     */
    private String performFlage;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPerformFlage() {
        return performFlage;
    }

    public void setPerformFlage(String performFlage) {
        this.performFlage = performFlage;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
