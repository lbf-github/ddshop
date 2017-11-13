package com.lbf.ddshop.web;

import com.lbf.ddshop.common.dto.Page;
import com.lbf.ddshop.common.dto.Result;
import com.lbf.ddshop.pojo.vo.TbItemParamCustom;
import com.lbf.ddshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: Administrator
 * Date: 2017/11/13
 * Time: 22:05
 * Version:V1.0
 */
@Controller
@Scope("prototype")
public class ItemParamAction {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemParamService itemParamService;

    @ResponseBody
    @RequestMapping("/itemParams")
    private Result<TbItemParamCustom> listTbItemParamCustom(Page page){

        Result<TbItemParamCustom> list=null;
        try {
            list=itemParamService.listTbItemParamCustom(page);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return list;

    }




}
