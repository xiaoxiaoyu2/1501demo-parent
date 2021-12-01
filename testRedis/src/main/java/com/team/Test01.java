package com.team;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Test01 {
    public static void main(String[] args) {
        //使用jedis基于连接池操作Redis的步骤
        //1.创建连接池对象
        //1.1创建连接配置对象
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMaxTotal(20);  //设置最大连接数
        //1.2创建连接池对象
        JedisPool jedisPool=new JedisPool(poolConfig,"localhost",6379);
        //2.基于连接池获取Jedis对象
        Jedis jedis=jedisPool.getResource();
        //3.操作redis
        jedis.set("name","余仕峰");
        String str=jedis.get("name");
        System.out.println("字符串是:"+str);
        //4.关闭对象
        jedis.close();
    }
}
