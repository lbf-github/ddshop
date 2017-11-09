package com.lbf.ddshop.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/9
 * Time: 11:08
 * Version:V1.0
 */
public class Order {
    private String sort;
    private String order;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
    /**
     * 1.0版本
     *  这个版本有点蠢
     */
   /* public List<Order> doList() {
        List<Order> list = new ArrayList<>();
        Order newOrder = new Order();
        String[] s = sort.split(",");
        String[] o = order.split(",");
        for (int i = 0; i < s.length; i++) {
            newOrder.setSort(s[i]);
            newOrder.setOrder(o[i]);
            list.add(newOrder);
        }
        return list;
    }*/

    /**
     * 2.0版本
     */
    public List<String> getOrderParams() {
        List<String> list = new ArrayList<String>();
        String[] sorts = this.sort.split(",");
        String[] orders = this.order.split(",");
        for (int i = 0; i < sorts.length; i++) {

            String temp = sorts[i] + " " + orders[i];
            list.add(temp);
        }
        return list;

    }
}
