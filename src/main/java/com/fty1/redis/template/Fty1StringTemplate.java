package com.fty1.redis.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Fty1StringTemplate {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
}
