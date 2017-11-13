package com.lbf.ddshop.web;

import com.lbf.ddshop.common.dto.Order;
import com.lbf.ddshop.common.dto.Page;
import com.lbf.ddshop.common.dto.Result;
import com.lbf.ddshop.pojo.po.TbItem;
import com.lbf.ddshop.pojo.vo.TbItemQuery;
import com.lbf.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/6
 * Time: 12:35
 * Version:V1.0
 */
@Controller
@Scope("prototype")
public class ItemAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public TbItem selectByPramaryKey(@PathVariable("itemId") Long itemId) {

        TbItem tbItem = itemService.getById(itemId);

        return tbItem;
    }

    /**
     * 分页查找
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping("/items")
    public Result<TbItem> listItems(Page page, Order order, TbItemQuery tbItemQuery) {
        Result<TbItem> list = null;
        try {
            list = itemService.listItems(page,order,tbItemQuery);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/items/batch", method = RequestMethod.POST)
    public int updateItemsByIds(@RequestParam("ids[]") List<Long> ids) {
        return itemService.updateItemsByIds(ids);
    }



    @ResponseBody
    @RequestMapping(value = "/items/batch/down",method = RequestMethod.POST)
    public int down_updateItemsByIds(@RequestParam("ids[]") List<Long> ids){
        return itemService.down_updateItemsByIds(ids);
    }

    @ResponseBody
    @RequestMapping(value = "/items/batch/up",method = RequestMethod.POST)
    public int up_updateItemsByIds(@RequestParam("ids[]") List<Long> ids){
        return itemService.up_updateItemsByIds(ids);
    }

    @ResponseBody
    @RequestMapping(value="/item",method = RequestMethod.POST)
    public int saveItem(TbItem tbItem,String content){
        int i=0;
        try{

           i = itemService.saveItem(tbItem,content);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return i;
    }

}
