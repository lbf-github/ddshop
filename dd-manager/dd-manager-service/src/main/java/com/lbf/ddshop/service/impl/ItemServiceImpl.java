package com.lbf.ddshop.service.impl;

import com.lbf.ddshop.common.dto.Page;
import com.lbf.ddshop.common.dto.Result;
import com.lbf.ddshop.dao.TbItemCustomMapper;
import com.lbf.ddshop.dao.TbItemMapper;
import com.lbf.ddshop.pojo.po.TbItem;
import com.lbf.ddshop.pojo.po.TbItemExample;
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

    @Autowired
    private TbItemCustomMapper tbItemCustomMapper;

    @Override
    public TbItem getById(Long itemId) {
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
        return tbItem;
    }

    @Override
    public List<TbItem> findAll() {
        return tbItemMapper.selectByExample(null);
    }

    @Override
    public Result<TbItem> listItems(Page page) throws Exception {

        int total = tbItemCustomMapper.countItems();
        List<TbItem> list = tbItemCustomMapper.listItemsByPage(page);
        Result<TbItem> result=new Result<>();
        result.setTotal(total);
        result.setRows(list);
        return result;
    }

    @Override
    public int updateItemsByIds(List<Long> ids) {

        TbItem record = new TbItem();
        record.setStatus((byte)3);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return tbItemMapper.updateByExampleSelective(record,example);
    }
}
