package com.lbf.ddshop.service.impl;

import com.lbf.ddshop.common.dto.Order;
import com.lbf.ddshop.common.dto.Page;
import com.lbf.ddshop.common.dto.Result;
import com.lbf.ddshop.common.util.IDUtils;
import com.lbf.ddshop.dao.TbItemCustomMapper;
import com.lbf.ddshop.dao.TbItemDescMapper;
import com.lbf.ddshop.dao.TbItemMapper;
import com.lbf.ddshop.dao.TbItemParamItemMapper;
import com.lbf.ddshop.pojo.po.TbItem;
import com.lbf.ddshop.pojo.po.TbItemDesc;
import com.lbf.ddshop.pojo.po.TbItemExample;
import com.lbf.ddshop.pojo.po.TbItemParamItem;
import com.lbf.ddshop.pojo.vo.TbItemQuery;
import com.lbf.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemCustomMapper tbItemCustomMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

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


    @Override
    @Transactional
    public Long saveItem(TbItem tbItem, String content,String paramData) {
        Long itemId=null;
        int i=0;
        try{

            //添加tb_item表
            itemId= IDUtils.getItemId();
            tbItem.setId(itemId);
            tbItem.setStatus((byte)2);
            tbItem.setCreated(new Date());
            tbItem.setUpdated(new Date());
           i = tbItemMapper.insert(tbItem);

            //添加tb_item_desc表
            TbItemDesc tbItemDesc=new TbItemDesc();
            tbItemDesc.setItemId(itemId);
            tbItemDesc.setItemDesc(content);
            tbItemDesc.setCreated(new Date());
            tbItemDesc.setUpdated(new Date());
            i +=tbItemDescMapper.insert(tbItemDesc);

            //添加tb_item_param_item表
            TbItemParamItem tbItemParamItem=new TbItemParamItem();
            tbItemParamItem.setItemId(itemId);
            tbItemParamItem.setParamData(paramData);
            tbItemParamItem.setCreated(new Date());
            tbItemParamItem.setUpdated(new Date());
            i+=tbItemParamItemMapper.insert(tbItemParamItem);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return itemId;
    }
}
