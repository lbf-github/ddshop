package com.lbf.ddshop.service.impl;

import com.lbf.ddshop.common.dto.TreeNode;
import com.lbf.ddshop.dao.TbItemCatMapper;
import com.lbf.ddshop.pojo.po.TbItemCat;
import com.lbf.ddshop.pojo.po.TbItemCatExample;
import com.lbf.ddshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/10
 * Time: 19:53
 * Version:V1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    private Logger longger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<TreeNode> listItems(Long parentId) {
        List<TreeNode> list=null;
        List<TreeNode> resultList=null;
        try {

            //创建模板
            TbItemCatExample example=new TbItemCatExample();
            TbItemCatExample.Criteria criteria=example.createCriteria();
            criteria.andParentIdEqualTo(parentId);

            //执行查询
            List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(example);
            resultList=new ArrayList<>();

            for(int i=0;i<tbItemCats.size();i++){
                TreeNode treeNode=new TreeNode();
                TbItemCat tbItemCat = tbItemCats.get(i);
                treeNode.setId(tbItemCat.getId());
                treeNode.setText(tbItemCat.getName());
                treeNode.setState(tbItemCat.getIsParent() ? "closed":"open");
                resultList.add(treeNode);
            }


        }catch (Exception e){
            longger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return resultList;
    }
}
