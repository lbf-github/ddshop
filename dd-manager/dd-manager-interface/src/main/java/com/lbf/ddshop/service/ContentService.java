package com.lbf.ddshop.service;

import com.lbf.ddshop.pojo.po.TbContent;

import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/21
 * Time: 17:19
 * Version:V1.0
 */
public interface ContentService {
    List<TbContent> listContentsByCid(Long id);
}
