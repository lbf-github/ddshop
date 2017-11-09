package com.lbf.ddshop.service.impl;

import com.lbf.ddshop.common.dto.Order;
import com.lbf.ddshop.common.dto.Page;
import com.lbf.ddshop.common.dto.Result;
import com.lbf.ddshop.dao.TbItemCustomMapper;
import com.lbf.ddshop.dao.TbItemMapper;
import com.lbf.ddshop.pojo.po.TbItem;
import com.lbf.ddshop.pojo.po.TbItemExample;
import com.lbf.ddshop.pojo.vo.TbItemQuery;
import com.lbf.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 分页
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public Result<TbItem> listItems(Page page,Order order,TbItemQuery tbItemQuery) throws Exception {

        int total = tbItemCustomMapper.countItems(tbItemQuery);
        Map<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("order",order);
        map.put("tbItemQuery",tbItemQuery);
        List<TbItem> list = tbItemCustomMapper.listItemsByPage(map);
        Result<TbItem> result=new Result<>();
        result.setTotal(total);
        result.setRows(list);
        return result;
    }

    /**
     * 批量修改
     * @param ids
     * @return
     */
    @Override
    public int updateItemsByIds(List<Long> ids) {

        TbItem record = new TbItem();
        record.setStatus((byte)3);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return tbItemMapper.updateByExampleSelective(record,example);
    }


    @Override
    public int down_updateItemsByIds(List<Long> ids) {
        TbItem record=new TbItem();
        record.setStatus((byte)2);


        TbItemExample example=new TbItemExample();
        TbItemExample.Criteria criteria=example.createCriteria();
        criteria.andIdIn(ids);

        return tbItemMapper.updateByExampleSelective(record,example);
    }


    @Override
    public int up_updateItemsByIds(List<Long> ids) {
        TbItem record=new TbItem();
        record.setStatus((byte)1);

        TbItemExample example=new TbItemExample();
        TbItemExample.Criteria criteria=example.createCriteria();
        criteria.andIdIn(ids);

        return tbItemMapper.updateByExampleSelective(record,example);
    }
}
