package com.lbf.ddshop.service.impl;

import com.lbf.ddshop.common.dto.Page;
import com.lbf.ddshop.common.dto.Result;
import com.lbf.ddshop.dao.TbItemParamCustomMapper;
import com.lbf.ddshop.dao.TbItemParamMapper;
import com.lbf.ddshop.pojo.po.TbItemParam;
import com.lbf.ddshop.pojo.po.TbItemParamExample;
import com.lbf.ddshop.pojo.vo.TbItemParamCustom;
import com.lbf.ddshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/13
 * Time: 22:15
 * Version:V1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemParamCustomMapper tbItemParamCustomMapper;
    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public Result<TbItemParamCustom> listTbItemParamCustom(Page page) {
        Result<TbItemParamCustom> list=new Result<>();
        try {

            int num=tbItemParamCustomMapper.countTbItemParamCustom();
            List<TbItemParamCustom> list1=tbItemParamCustomMapper.listTbItemParamCustomByPage(page);

            list.setTotal(num);
            list.setRows(list1);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public int saveItemParam(Long cid, String paramDate) {
        int i=0;
        try {
            TbItemParam tbItemParam=new TbItemParam();
            tbItemParam.setItemCatId(cid);
            tbItemParam.setParamData(paramDate);
            tbItemParam.setCreated(new Date());
            tbItemParam.setUpdated(new Date());
            i = tbItemParamMapper.insert(tbItemParam);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }


        return i;
    }

    @Override
    public TbItemParam getItemParamByCid(Long cid) {
        TbItemParam tbItemParam = null;
        try {
            TbItemParamExample example=new TbItemParamExample();
            TbItemParamExample.Criteria criteria=example.createCriteria();
            criteria.andItemCatIdEqualTo(cid);
            List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(example);

            if(tbItemParams!=null&&tbItemParams.size()>0){
                tbItemParam=tbItemParams.get(0);
            }

        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return tbItemParam;
    }
}
