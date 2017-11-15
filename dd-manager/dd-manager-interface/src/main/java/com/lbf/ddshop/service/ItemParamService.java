package com.lbf.ddshop.service;

import com.lbf.ddshop.common.dto.Page;
import com.lbf.ddshop.common.dto.Result;
import com.lbf.ddshop.pojo.po.TbItemParam;
import com.lbf.ddshop.pojo.vo.TbItemParamCustom;

/**
 * User: Administrator
 * Date: 2017/11/13
 * Time: 22:14
 * Version:V1.0
 */
public interface ItemParamService {
    Result<TbItemParamCustom> listTbItemParamCustom(Page page);

    int saveItemParam(Long cid, String paramDate);

    TbItemParam getItemParamByCid(Long cid);
}
