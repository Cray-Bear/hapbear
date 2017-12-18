package com.fty1.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;

public class Fty1RedisTemplateFactory {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;



}
