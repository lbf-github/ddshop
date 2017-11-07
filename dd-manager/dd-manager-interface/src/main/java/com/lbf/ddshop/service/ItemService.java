package com.lbf.ddshop.service;

import com.lbf.ddshop.common.dto.Page;
import com.lbf.ddshop.common.dto.Result;
import com.lbf.ddshop.pojo.po.TbItem;

import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/6
 * Time: 12:49
 * Version:V1.0
 */
public interface ItemService {


    TbItem getById(Long itemId);

    List<TbItem> findAll();

    Result<TbItem> listItems(Page page) throws Exception;
}
