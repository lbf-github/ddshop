package com.lbf.ddshop.dao;

import com.lbf.ddshop.common.dto.Page;
import com.lbf.ddshop.pojo.vo.TbItemParamCustom;

import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/13
 * Time: 22:18
 * Version:V1.0
 */
public interface TbItemParamCustomMapper {
    int countTbItemParamCustom();

    List<TbItemParamCustom> listTbItemParamCustomByPage(Page page);
}
