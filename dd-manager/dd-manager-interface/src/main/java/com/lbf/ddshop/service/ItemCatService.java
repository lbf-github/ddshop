package com.lbf.ddshop.service;

import com.lbf.ddshop.common.dto.TreeNode;

import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/10
 * Time: 19:52
 * Version:V1.0
 */
public interface ItemCatService {
    List<TreeNode> listItems(Long parentId);
}
