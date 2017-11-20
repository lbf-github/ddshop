package com.lbf.ddshop.service.impl;

import com.lbf.ddshop.common.util.FtpUtils;
import com.lbf.ddshop.common.util.IDUtils;
import com.lbf.ddshop.common.util.PropKit;
import com.lbf.ddshop.service.FileService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Administrator
 * Date: 2017/11/18
 * Time: 14:35
 * Version:V1.0
 */@Service
public class FileServiceImpl implements FileService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public Map<String, Object> uploadImages(MultipartFile upfile) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {

            //即将放置到上传配置文件中的信息upload.properties
            String name="ftp.properties";
            String host= PropKit.use(name).get("ftp.address");
            int port = PropKit.use(name).getInt("ftp.port");
            String username = PropKit.use(name).get("ftp.username");
            String password = PropKit.use(name).get("ftp.password");
            String basePath = PropKit.use(name).get("ftp.basePath");

            //创建文件路径：基础路径+文件路径+文件名+扩展名
            String filePath = new DateTime().toString("/yyyy/MM/dd");


            //获取原来的文件名，包括扩展名
            String original = upfile.getOriginalFilename();

            //截取出扩展名
            String fileType = original.substring(original.lastIndexOf("."));
            //使用自定义工具类产生新的文件名，只产生了文件名，未产生扩展名
            String newName = IDUtils.genImageName();
            //拼接出新的文件名+扩展名
            newName += fileType;
            InputStream inputStream = null;
            try {
                inputStream = upfile.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //上传成功返回true，否则返回false
            boolean bool = FtpUtils.uploadFile(host, port, username, password, basePath, filePath, newName, inputStream);

            if(bool){
                map.clear();
                map.put("state","SUCCESS");
                map.put("original",original);
                map.put("size",upfile.getSize());
                map.put("title",newName);
                map.put("type",fileType);
                map.put("url",filePath + "/" + newName);
            }




        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return map;
    }
}
