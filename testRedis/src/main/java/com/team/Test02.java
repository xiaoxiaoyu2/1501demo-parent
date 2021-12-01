package com.team;

import redis.clients.jedis.Jedis;

public class Test02 {
    public static void main(String[] args) {
        //使用jedis操作单机版的redis
        //1.创建Jedis对象
        Jedis jedis=new Jedis("localhost",6379);
        //2.使用jedis的方法操作Redis
        //注意:jedis的方法对应Redis的命令
        //jedis.set("键",值);
        jedis.set("name","老王");
        //设置有效时间
        jedis.expire("name",600);  //以秒为单位
        //获取键值
        String name=jedis.get("name");
        System.out.println(name);
        //查看剩余时间
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(jedis.ttl("name"));
        //3.关闭jedis
        jedis.close();
    }
}
