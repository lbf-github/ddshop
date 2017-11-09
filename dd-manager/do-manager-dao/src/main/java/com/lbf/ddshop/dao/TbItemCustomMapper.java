package com.lbf.ddshop.dao;

import com.lbf.ddshop.pojo.po.TbItem;

import java.util.List;
import java.util.Map;

public interface TbItemCustomMapper {

    int countItems() throws Exception;


    List<TbItem> listItemsByPage(Map<String,Object> map) throws Exception;

}