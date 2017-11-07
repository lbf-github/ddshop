package com.lbf.ddshop.web;

import com.lbf.ddshop.common.dto.Page;
import com.lbf.ddshop.common.dto.Result;
import com.lbf.ddshop.pojo.po.TbItem;
import com.lbf.ddshop.service.ItemService;
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
    @RequestMapping(value="/item/{itemId}",method = RequestMethod.GET)
    public TbItem selectByPramaryKey(@PathVariable("itemId") Long itemId){

        TbItem tbItem = itemService.getById(itemId);

        return tbItem;
    }



//    @ResponseBody
//    @RequestMapping("/items")
//    public List<TbItem> selectAll(){
//        List<TbItem> list=null;
//       try {
//           list = itemService.findAll();
//       }catch (Exception e){
//            logger.error(e.getMessage(),e);
//           e.printStackTrace();
//       }
//
//        return list;
//    }

    @ResponseBody
    @RequestMapping("/items")
    public Result<TbItem> listItems(Page page){
        Result<TbItem> list=null;
        try {
            list=itemService.listItems(page);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return list;
    }

}
