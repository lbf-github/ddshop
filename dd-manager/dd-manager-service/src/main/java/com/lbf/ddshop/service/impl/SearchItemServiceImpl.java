package com.lbf.ddshop.service.impl;

import com.lbf.ddshop.dao.SearchItemDao;
import com.lbf.ddshop.dao.TbItemMapperCustom;
import com.lbf.ddshop.pojo.vo.TbSearchItemCustom;
import com.lbf.ddshop.pojo.vo.TbSearchItemResult;
import com.lbf.ddshop.service.SearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/22
 * Time: 20:42
 * Version:V1.0
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {


    @Autowired
    private TbItemMapperCustom tbItemMapperCustom;

    @Autowired
    private SolrServer solrServer;

    @Autowired
    private SearchItemDao searchItemDao;

    /**
     * 导入索引库
     * @return
     */
    @Override
    public boolean importAllItems() {
        //采集数据
        List<TbSearchItemCustom> list = tbItemMapperCustom.getSearchItemList();

        //采集数据遍历
        for (TbSearchItemCustom tbSearchItemCustom : list) {
            //拿到solr的文档域
            SolrInputDocument document = new SolrInputDocument();
            //向文档对象中添加域：对应schema.xml配置文件中的域名
            document.addField("id", tbSearchItemCustom.getId());
            document.addField("item_title", tbSearchItemCustom.getTitle());
            document.addField("item_sell_point", tbSearchItemCustom.getSellPoint());
            document.addField("item_price", tbSearchItemCustom.getPrice());
            document.addField("item_image", tbSearchItemCustom.getImage());
            document.addField("item_category_name", tbSearchItemCustom.getCatName());
            //写入索引库
            try {
                solrServer.add(document);
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public TbSearchItemResult search(String keyword, Integer page, int rows) {
        //创建一个SolrQuery对象
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery(keyword);
        //设置分页条件
        if (page <=0 ) page = 1;
        query.setStart((page - 1) * rows);
        query.setRows(rows);
        //设置默认搜索域
        query.set("df", "item_keywords");
        //开启高亮显示
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        //调用dao执行查询
        TbSearchItemResult searchResult = searchItemDao.search(query);
        //计算总页数
        long recordCount = searchResult.getRecordCount();
        int totalPage = (int) ((recordCount + rows - 1) / rows);
        //添加到返回结果
        searchResult.setTotalPages(totalPage);
        //返回结果
        return searchResult;

    }
}
