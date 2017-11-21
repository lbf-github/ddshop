package com.lbf.ddshop.lucene.dao.impl;


import com.lbf.ddshop.lucene.dao.ITbItemLuence;
import com.lbf.ddshop.lucene.po.TbItemLucene;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/21
 * Time: 21:50
 * Version:V1.0
 */
@Repository
public class TbItemLuenceImpl implements ITbItemLuence {

    @Override
    public List<TbItemLucene> listTbItemLucene() {
        //数据库连接
        Connection connection = null;
        //预编译语句对象
        PreparedStatement preparedStatement = null;
        //结果集对象
        ResultSet resultSet = null;
        //商品列表
        List<TbItemLucene> list=new ArrayList<TbItemLucene>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ddshop", "root", "root123");
            preparedStatement = connection.prepareStatement("select id,title,sell_point as sellPoint from tb_item");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                TbItemLucene tbItemLucene=new TbItemLucene();
                tbItemLucene.setId(resultSet.getLong("id"));
                tbItemLucene.setTitle(resultSet.getString("title"));
                tbItemLucene.setSellPoint(resultSet.getString("sellPoint"));
                list.add(tbItemLucene);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
