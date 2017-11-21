package com.lbf.ddshop.common.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * User: Administrator
 * Date: 2017/11/20
 * Time: 21:17
 * Version:V1.0
 */
public class JedisTest {
    @Test
    public void testJedis1(){
        Jedis jedis=new Jedis("120.78.182.128",7002);
        jedis.set("name","lbf");
        System.out.println(jedis.get("name"));
        jedis.close();
    }

    @Test
    public void testJedis2(){
        //获取jedis池
        JedisPool jedisPool = new JedisPool("120.78.182.128",7002);
        //获取Jedis对象
        Jedis jedis = jedisPool.getResource();
        jedis.set("key1","value1");
        System.out.println(jedis.get("key1"));
        //关闭连接
        jedis.close();
        jedisPool.close();
    }
    @Test
    public void testJedis3(){
        //创建集群节点集合
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        //将6个节点加入到集合中
        nodes.add(new HostAndPort("120.78.182.128",7001));
        nodes.add(new HostAndPort("120.78.182.128",7002));
        nodes.add(new HostAndPort("120.78.182.128",7003));
        nodes.add(new HostAndPort("120.78.182.128",7004));
        nodes.add(new HostAndPort("120.78.182.128",7005));
        nodes.add(new HostAndPort("120.78.182.128",7006));
        //创建集群对象
        JedisCluster jedisCluster = new JedisCluster(nodes);
        //jedisCluster.hdel("CONTENT_LIST","89");
        //存入数据
        jedisCluster.set("key2","value2");
        //System.out.println(jedisCluster.hget("CONTENT_LIST","89"));
        //关闭连接
        jedisCluster.close();
    }

}
