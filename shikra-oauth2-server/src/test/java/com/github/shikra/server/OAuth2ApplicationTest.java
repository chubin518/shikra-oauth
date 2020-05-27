package com.github.shikra.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shikra.server.model.OauthRole;
import com.github.shikra.server.service.OauthRoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest(classes = OAuth2Application.class)
@RunWith(SpringRunner.class)
@Slf4j
public class OAuth2ApplicationTest {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    OauthRoleService oauthRoleService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void redisTest() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("redisson", "hello word");
    }

    @Test
    public void transactionTest() {
        OauthRole role = new OauthRole();
        role.setCode("abc");
        role.setCreateTime(new Date());
        role.setCreateUserId(1l);
        role.setName("11111");
        role.setStatus((byte) 1);
        role.setUpdateTime(new Date());
        role.setUpdateUserId(2l);
        oauthRoleService.save(role);
    }

    @Test
    public void testJackson() throws JsonProcessingException {
        OauthRole role = new OauthRole();
        role.setCode("abc");
        role.setCreateTime(new Date());
        role.setCreateUserId(1l);
        role.setName("11111");
        role.setStatus((byte) 1);
        role.setUpdateTime(new Date());
        role.setUpdateUserId(2l);
        String s = objectMapper.writeValueAsString(role);
        System.out.println(s);
    }
}
