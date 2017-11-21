package com.lbf.ddshop.lucene.manager;

import com.lbf.ddshop.lucene.dao.ITbItemLuence;
import com.lbf.ddshop.lucene.dao.impl.TbItemLuenceImpl;
import com.lbf.ddshop.lucene.po.TbItemLucene;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/21
 * Time: 22:09
 * Version:V1.0
 */
public class IndexManager {
    //创建索引
    @Test
    public void createIndex() throws IOException {
        //采集数据
        ITbItemLuence iTbItemLuence=new TbItemLuenceImpl();

        List<TbItemLucene> tbItemLuceneList = iTbItemLuence.listTbItemLucene();
        //将采集数据保存到文档域中
        List<Document> documentList = new ArrayList<Document>();

        for (TbItemLucene tbItemLucene : tbItemLuceneList){
            Document document = new Document();

            Field id = new StringField("id",tbItemLucene.getId().toString(), Field.Store.YES);

            Field title = new TextField("title",tbItemLucene.getTitle(), Field.Store.YES);

            Field sellPoint = new TextField("sellPoint",tbItemLucene.getSellPoint(),Field.Store.YES);


            //将field添加到document中
            document.add(id);
            document.add(title);
            document.add(sellPoint);

            //添加到文档域集合中
            documentList.add(document);
        }

        //创建分词器，使用Lucene默认分词器
        Analyzer analyzer = new IKAnalyzer();
        IndexWriterConfig cfg = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        //指定索引库的地址

        File indexFile = new File("E:\\java_study\\week18\\bookindex");
        Directory directory = FSDirectory.open(indexFile);
        IndexWriter writer = new IndexWriter(directory,cfg);
        //创建索引，将每个文档域对象存放到索引库中
        for (Document doc : documentList) {
            writer.addDocument(doc);
        }
        //关闭连接
        writer.close();
    }

    //查询索引
    @Test
    public void indexSearch() throws ParseException, IOException {
        QueryParser parser = new QueryParser("description",new IKAnalyzer());
        Query query = parser.parse("id:860275");
        //指定索引库的地址
        File indexFile = new File("E:\\java_study\\week18\\bookindex");
        Directory directory = FSDirectory.open(indexFile);
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        //搜索索引库的内容
        TopDocs topDocs = searcher.search(query, 10);
        System.out.println("匹配的记录总数为：" + topDocs.totalHits);

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs){
            System.err.println("docId:" + scoreDoc.doc);
            Document document = searcher.doc(scoreDoc.doc);
            System.out.println(document.get("id"));
            System.out.println(document.get("title"));
            System.out.println(document.get("sellPoint"));

        }
    }


    //删除索引
    @Test
    public void indexDelete() throws IOException {
        //创建分词器，使用Lucene默认分词器
        Analyzer analyzer = new IKAnalyzer();
        IndexWriterConfig cfg = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        //指定索引库的地址
        File indexFile = new File("E:\\java_study\\week18\\bookindex");
        Directory directory = FSDirectory.open(indexFile);
        IndexWriter writer = new IndexWriter(directory,cfg);
        //根据条件删除
        Term term = new Term("id","536563");
        writer.deleteDocuments(term);
        //writer.deleteAll();
        writer.close();
    }

    //更新索引
    @Test
    public void indexUpdate() throws IOException {
        //创建分词器，使用Lucene默认分词器
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig cfg = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        //指定索引库的地址
        File indexFile = new File("E:\\java_study\\week18\\bookindex");
        Directory directory = FSDirectory.open(indexFile);
        IndexWriter writer = new IndexWriter(directory,cfg);
        //更新
        Term term = new Term("id","860275");
        //更新模板
        Document doc = new Document();
        doc.add(new TextField("id","860275", Field.Store.YES));
        doc.add(new TextField("title","ceshi", Field.Store.YES));
        //如果存在，则更新
        //如果不存在，则新增
        writer.updateDocument(term,doc);
        writer.close();
    }

}
