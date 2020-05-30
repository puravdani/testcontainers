package com.demo;

import org.junit.Before;
import org.junit.ClassRule;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.shaded.okhttp3.Cache;
import redis.clients.jedis.Jedis;

public class RedisContainerTest {

    @ClassRule
    public static GenericContainer redis = new GenericContainer("redis:3.0.6").withExposedPorts(6379);

    private Cache cache;

    static {
        redis.start();
    }

    @Before
    public void setup(){
        Jedis jedis = new Jedis(redis.getContainerIpAddress(),redis.getMappedPort(6379));
        //cache = new RedisBackedCache
    }
}
