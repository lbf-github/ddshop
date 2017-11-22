package com.lbf.ddshop.web;

import com.lbf.ddshop.common.dto.MessageResult;
import com.lbf.ddshop.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: Administrator
 * Date: 2017/11/22
 * Time: 20:27
 * Version:V1.0
 */
@Controller
public class SearchItemAction {


    @Autowired
    private SearchItemService searchItemService;

    @ResponseBody
    @RequestMapping(value = "item/search/import",method = RequestMethod.POST)
    public MessageResult searchItemIndex(){

        //通过调用Service层方法来导入索引库
        boolean bool=searchItemService.importAllItems();

        MessageResult mr=new MessageResult();
        if(bool){

        mr.setSuccess(true);
        mr.setMessage("索引导入成功");
        }else{
            mr.setSuccess(false);
            mr.setMessage("索引导入失败");
        }
        return mr;

    }

}
