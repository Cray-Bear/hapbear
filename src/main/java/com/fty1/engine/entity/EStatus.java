package com.fty1.engine.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "engine_tm_status")
public class EStatus extends ERoot {


    private String businessCode;    //业务场景编码

    private String statusCode;       //节点的别名

    private Integer numberValue;    //节点的数字值

    private String edescribe;        //描述

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(Integer numberValue) {
        this.numberValue = numberValue;
    }

    public String getEdescribe() {
        return edescribe;
    }

    public void setEdescribe(String edescribe) {
        this.edescribe = edescribe;
    }
}
