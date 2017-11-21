package com.lbf.ddshop.portal.web;


import com.lbf.ddshop.common.util.PropKit;
import com.lbf.ddshop.pojo.po.TbContent;
import com.lbf.ddshop.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/21
 * Time: 17:09
 * Version:V1.0
 */
@Controller
public class PortalIndexAction {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/")
    public String portalIndex(Model model){

        //第一步：使用service去查，根据tb_content_category的ID去查

        Long id = PropKit.use("ftp.properties").getLong("ftp.gallery");
        List<TbContent> list = contentService.listContentsByCid(id);

        //存放到model中

        model.addAttribute("ad1List",list);

        //返回主页
        return "index";

    }


}
