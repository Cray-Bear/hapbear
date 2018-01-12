package com.fty1.engine.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "engine_tm_point")
public class EStatusGroup extends ERoot {


    private String businessCode;    //业务场景编码

    private String groupCode;       //节点的别名

    private Integer numberValue;    //节点的数字值

    private String describe;        //描述

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Integer getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(Integer numberValue) {
        this.numberValue = numberValue;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
