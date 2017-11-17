package blue.cache.redis.java;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

// 注意Jedis对象并不是线程安全的，在多线程下使用同一个Jedis对象会出现并发问题。
// 为了避免每次使用Jedis对象时都需要重新构建，Jedis提供了JedisPool。
// JedisPool是基于Commons Pool 2实现的一个线程安全的连接池。
public class JedisPool02 {

    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);

//        public JedisPool(/** Commons Pool的参数 **/ final GenericObjectPoolConfig poolConfig,
//        /** Redis Server地址 **/ final URI uri,
//        /** 连接Redis Server超时时间**/ final int connectionTimeout,
//        /** 等待Response超时时间 **/ final int soTimeout) {
//            super(poolConfig, new JedisFactory(uri, connectionTimeout, soTimeout, null));
//        }
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379);

        Jedis jedis = null;
        try {
            // 初始化Jedis对象并不会与Redis Server建立连接，连接发生在第一次执行命令时。
            jedis = jedisPool.getResource();
            jedis.set("201711011017", "test");
            System.out.println(jedis.get("201711011017"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        jedisPool.close();
    }
}
