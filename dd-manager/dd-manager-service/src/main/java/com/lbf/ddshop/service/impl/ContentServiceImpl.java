package com.lbf.ddshop.service.impl;

import com.ctc.wstx.sw.EncodingXmlWriter;
import com.lbf.ddshop.common.jedis.JedisClient;
import com.lbf.ddshop.common.util.JsonUtils;
import com.lbf.ddshop.dao.TbContentMapper;
import com.lbf.ddshop.pojo.po.TbContent;
import com.lbf.ddshop.pojo.po.TbContentExample;
import com.lbf.ddshop.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/21
 * Time: 17:19
 * Version:V1.0
 */
@Service
public class ContentServiceImpl implements ContentService{

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbContentMapper tbContentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public List<TbContent> listContentsByCid(Long id) {
        List<TbContent> list=null;
        //判断redis集群中是否存在轮播图数据
        try {

            String json = jedisClient.hget("CONTENT_LIST", id + "");
            //判断json是否存在
            if(StringUtils.isNotBlank(json)){
                list= JsonUtils.jsonToList(json,TbContent.class);
                return list;
            }


        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        //创建模板

        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria=example.createCriteria();
        criteria.andCategoryIdEqualTo(id);

        //执行查询

        list=tbContentMapper.selectByExample(example);

        //将结果存入redis集群中

        try {

            jedisClient.hset("CONTENT_LIST",id+"",JsonUtils.objectToJson(list));

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }



        return list;
    }
}
