/**
 * @Author: 李幸阜
 * @Date: 2024/4/20 02:58
 * @Description:
 **/

package com.dm.content.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        JdkSerializationRedisSerializer redisSerializer2 = new JdkSerializationRedisSerializer();

        template.setConnectionFactory(factory);
        //key序列化方式
        template.setKeySerializer(redisSerializer);
        //value序列化
        template.setValueSerializer(redisSerializer2);
        //value hashmap序列化
        template.setHashValueSerializer(redisSerializer2);
        //key haspmap序列化
        template.setHashKeySerializer(redisSerializer);

        return template;
    }
}
