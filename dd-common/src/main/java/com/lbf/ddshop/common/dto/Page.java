package com.lbf.ddshop.common.dto;

/**
 * User: Administrator
 * Date: 2017/11/7
 * Time: 16:21
 * Version:V1.0
 */
public class Page {

    private int page;

    private int rows;

    /**
     * 偏移量
     * @return
     */
    //private int offset;

    public int getOffset() {
        return (page-1)*rows;
    }



    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
