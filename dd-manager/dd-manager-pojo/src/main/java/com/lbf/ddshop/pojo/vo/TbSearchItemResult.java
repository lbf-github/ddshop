package com.lbf.ddshop.pojo.vo;

import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/23
 * Time: 20:38
 * Version:V1.0
 */

public class TbSearchItemResult {
    private long recordCount;
    private int totalPages;
    private List<TbSearchItemCustom> itemList;

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<TbSearchItemCustom> getItemList() {
        return itemList;
    }

    public void setItemList(List<TbSearchItemCustom> itemList) {
        this.itemList = itemList;
    }
}
