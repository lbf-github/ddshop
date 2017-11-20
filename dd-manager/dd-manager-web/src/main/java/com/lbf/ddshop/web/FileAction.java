package com.lbf.ddshop.web;

import com.lbf.ddshop.service.FileService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

/**
 * User: Administrator
 * Date: 2017/11/18
 * Time: 11:23
 * Version:V1.0
 */
@Controller
@Scope("prototype")
public class FileAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/file/upload",method = RequestMethod.GET)
    @ResponseBody
    public void config(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String action = request.getParameter("action");
        if("config".equals(action)){
            PrintWriter out = response.getWriter();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.json");
            IOUtils.copy(inputStream,out,"UTF-8");
        }
    }


    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> upload(MultipartFile upfile){
        Map<String,Object> map=null;
        try {
            map=fileService.uploadImages(upfile);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return map;
    }


}
