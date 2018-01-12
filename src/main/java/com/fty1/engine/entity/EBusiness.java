package com.fty1.engine.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "engine_tm_business")
public class EBusiness extends ERoot {

    private String businessCode;

    private String describe;

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
