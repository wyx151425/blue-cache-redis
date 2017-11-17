package blue.cache.redis.java;

import redis.clients.jedis.Jedis;

/**
 * Jedis直接连接Redis
 */
public class Jedis01 {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("singleJedis", "hello jedis!");
        System.out.println(jedis.get("singleJedis"));
        jedis.close();
    }
}
