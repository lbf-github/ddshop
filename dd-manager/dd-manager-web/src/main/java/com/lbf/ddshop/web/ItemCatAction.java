package com.lbf.ddshop.web;

import com.lbf.ddshop.common.dto.TreeNode;
import com.lbf.ddshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/10
 * Time: 19:51
 * Version:V1.0
 */
@Controller
@Scope("prototype")
public class ItemCatAction {

    private Logger longger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping(value = "/itemCats",method = RequestMethod.POST)
    @ResponseBody
    public List<TreeNode> listItems(@RequestParam("parentId") Long parentId){
        List<TreeNode> list=null;
        try {
            list=itemCatService.listItems(parentId);
        }catch (Exception e){
            longger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return list;
    }
}
