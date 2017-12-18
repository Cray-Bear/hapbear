package com.fty1.redis.service.impl;

import com.fty1.redis.service.Fty1RedisStringService;
import com.fty1.redis.template.Fty1StringTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Fty1RedisStringImplService implements Fty1RedisStringService {

    @Autowired
    private Fty1StringTemplate fty1StringTemplate;
}
