package com.lbf.ddshop.service.impl;

import com.lbf.ddshop.dao.TbItemMapper;
import com.lbf.ddshop.pojo.po.TbItem;
import com.lbf.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/6
 * Time: 13:01
 * Version:V1.0
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem getById(Long itemId) {
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
        return tbItem;
    }

    @Override
    public List<TbItem> findAll() {
        return tbItemMapper.selectByExample(null);
    }
}
