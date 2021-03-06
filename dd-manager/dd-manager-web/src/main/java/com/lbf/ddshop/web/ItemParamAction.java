package com.lbf.ddshop.web;

import com.lbf.ddshop.common.dto.Page;
import com.lbf.ddshop.common.dto.Result;
import com.lbf.ddshop.pojo.po.TbItemParam;
import com.lbf.ddshop.pojo.vo.TbItemParamCustom;
import com.lbf.ddshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @ResponseBody
    @RequestMapping(value = "item/param/save/{cid}",method = RequestMethod.POST)
    public int saveItemParam(@PathVariable("cid") Long cid,String paramDate){
        int i=0;
        try {
           i = itemParamService.saveItemParam(cid,paramDate);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }


        return i;
    }

    @ResponseBody
    @RequestMapping(value = "itemParam/query/{cid}",method = RequestMethod.GET)
    public TbItemParam getItemParamById(@PathVariable("cid") Long cid){

        TbItemParam tbItemParam = null;
        try {
            tbItemParam = itemParamService.getItemParamByCid(cid);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return tbItemParam;
    }




}
