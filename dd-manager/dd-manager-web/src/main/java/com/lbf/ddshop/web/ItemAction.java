package com.lbf.ddshop.web;

import com.lbf.ddshop.pojo.po.TbItem;
import com.lbf.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value="/item/{itemId}",method = RequestMethod.GET)
    public TbItem selectByPramaryKey(@PathVariable("itemId") Long itemId){

        TbItem tbItem = itemService.getById(itemId);

        return tbItem;
    }

    @RequestMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return page;
    }

    @ResponseBody
    @RequestMapping("/items")
    public List<TbItem> selectAll(){
        List<TbItem> list = itemService.findAll();
        return list;
    }
}
