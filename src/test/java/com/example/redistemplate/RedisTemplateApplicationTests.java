package com.example.redistemplate;

import com.example.redistemplate.entity.User;
import com.example.redistemplate.utils.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

@SpringBootTest
class RedisTemplateApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    void contextLoads() {
        // redisTemplate.opsForValue() 操作字符串
        // redisTemplate.opsForList() 操作list
        // redisTemplate.opsForHash() 操作hash
        // redisTemplate.opsForSet()
        // redisTemplate.opsForZSet()

        // redisTemplate.opsForGeo()
        // redisTemplate.opsForHyperLogLog()

        // 获取Redis的连接对象
        // RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        // connection.flushDb();
        // connection.flushAll();

        redisTemplate.opsForValue().set("name", "xiaotian");
        System.out.println(redisTemplate.opsForValue().get("name"));

    }

    @Test
    void test1() throws JsonProcessingException {
        // 一般使用json传递对象
        User tianjiaxin = new User("TIANJIAXIN", 22);
        String str = new ObjectMapper().writeValueAsString(tianjiaxin);
        // redisTemplate.opsForValue().set("user",str);
        redisTemplate.opsForValue().set("user", tianjiaxin);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

    @Test
    void test2(){
        redisUtil.set("test", "田佳鑫");
        System.out.println(redisUtil.get("test"));
    }



    @Test
    public void sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("这是一封测试邮件");
        message.setFrom("443016895@qq.com");
        message.setTo("tianjx0722@gmail.com");
//        message.setCc("37xxxxx37@qq.com");
//        message.setBcc("14xxxxx098@qq.com");
        message.setSentDate(new Date());
        message.setText("这是测试邮件的正文");
        javaMailSender.send(message);
    }
}
