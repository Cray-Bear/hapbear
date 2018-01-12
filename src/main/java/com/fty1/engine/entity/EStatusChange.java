package com.fty1.engine.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "engine_tm_statuschange")
public class EStatusChange extends ERoot {

    private String businessCode;        //业务场景编码


    private String fromBusinessCode;        //业务场景编码

    private String fromStatusCode;       //起点


    private String toBusinessCode;        //业务场景编码

    private String toStatusCode;         //终点

    private String echange;                //如何到达

    private String changeType;            //起点开始触发类型

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getFromBusinessCode() {
        return fromBusinessCode;
    }

    public void setFromBusinessCode(String fromBusinessCode) {
        this.fromBusinessCode = fromBusinessCode;
    }

    public String getFromStatusCode() {
        return fromStatusCode;
    }

    public void setFromStatusCode(String fromStatusCode) {
        this.fromStatusCode = fromStatusCode;
    }

    public String getToBusinessCode() {
        return toBusinessCode;
    }

    public void setToBusinessCode(String toBusinessCode) {
        this.toBusinessCode = toBusinessCode;
    }

    public String getToStatusCode() {
        return toStatusCode;
    }

    public void setToStatusCode(String toStatusCode) {
        this.toStatusCode = toStatusCode;
    }

    public String getEchange() {
        return echange;
    }

    public void setEchange(String echange) {
        this.echange = echange;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }
}
