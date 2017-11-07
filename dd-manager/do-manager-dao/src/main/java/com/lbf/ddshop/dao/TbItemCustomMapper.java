package com.lbf.ddshop.dao;

import com.lbf.ddshop.common.dto.Page;
import com.lbf.ddshop.pojo.po.TbItem;

import java.util.List;

public interface TbItemCustomMapper {

    int countItems() throws Exception;

    List<TbItem> listItemsByPage(Page page) throws Exception;

}