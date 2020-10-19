package com.resjz.common.service.vo;

import com.resjz.common.dao.zmadmin.entity.FHouseArticleEntity;

import java.io.Serializable;

public class FHouseArticleVo  extends FHouseArticleEntity  implements Serializable {

    private  String  addUserName;

    private  String areaName;

    private  String houseName;

    public String getAddUserName() {
        return addUserName;
    }

    public void setAddUserName(String addUserName) {
        this.addUserName = addUserName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
}
