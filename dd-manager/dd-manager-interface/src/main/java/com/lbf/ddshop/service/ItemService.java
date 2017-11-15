package com.lbf.ddshop.service;

import com.lbf.ddshop.common.dto.Order;
import com.lbf.ddshop.common.dto.Page;
import com.lbf.ddshop.common.dto.Result;
import com.lbf.ddshop.pojo.po.TbItem;
import com.lbf.ddshop.pojo.vo.TbItemQuery;

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

    Result<TbItem> listItems(Page page,Order order,TbItemQuery tbItemQuery) throws Exception;

    int updateItemsByIds(List<Long> ids);

    int down_updateItemsByIds(List<Long> ids);

    int up_updateItemsByIds(List<Long> ids);

    int saveItem(TbItem tbItem, String content,String paramData);
}
