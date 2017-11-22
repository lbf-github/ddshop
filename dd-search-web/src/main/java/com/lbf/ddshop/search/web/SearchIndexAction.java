package com.lbf.ddshop.search.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: DHC
 * Date: 2017/11/22
 * Time: 15:40
 * Version:V1.0
 */
@Controller
public class SearchIndexAction {

    @RequestMapping("/")
    public String portalIndex(Model model){
        return "search";
    }
}
