package com.lbf.ddshop.pojo.vo;

import com.lbf.ddshop.pojo.po.TbItem;

/**
 * User: Administrator
 * Date: 2017/11/7
 * Time: 18:32
 * Version:V1.0
 */
public class TbItemCustom extends TbItem {

    private String catName;
    private String statusName;


    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
