package com.lbf.ddshop.lucene.dao;

import com.lbf.ddshop.lucene.po.TbItemLucene;

import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/21
 * Time: 21:46
 * Version:V1.0
 */
public interface ITbItemLuence {
    /**
     * 采集数据，查询所有商品
     * @return
     */
    List<TbItemLucene> listTbItemLucene();
}
