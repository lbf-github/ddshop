package com.lbf.ddshop.dao;

import com.lbf.ddshop.pojo.po.TbItem;
import com.lbf.ddshop.pojo.vo.TbItemQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbItemCustomMapper {

    int countItems(@Param("tbItemQuery") TbItemQuery tbItemQuery) throws Exception;


    List<TbItem> listItemsByPage(Map<String,Object> map) throws Exception;

}