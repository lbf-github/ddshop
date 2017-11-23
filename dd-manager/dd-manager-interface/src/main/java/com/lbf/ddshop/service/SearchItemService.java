package com.lbf.ddshop.service;

import com.lbf.ddshop.pojo.vo.TbSearchItemResult;

/**
 * User: Administrator
 * Date: 2017/11/22
 * Time: 20:40
 * Version:V1.0
 */

public interface SearchItemService {
    boolean importAllItems();

    TbSearchItemResult search(String keyword, Integer page, int rows);
}
