package com.demo.configuration;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.net.UnknownHostException;


@Configuration
public class RedisDataConf {


    @Autowired(required = true)
    Envconfig env;
    public static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RedisDataConf.class);

    @Bean
    @ConditionalOnMissingBean
    public RedisConnectionFactory redisConnectionFactory() throws UnknownHostException {

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(env.getRedisMaxConn());
        poolConfig.setMinIdle(env.getRedisMinIdleConn());
        poolConfig.setMaxIdle(env.getRedisMaxIdleConn());
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);

        JedisConnectionFactory factory = new JedisConnectionFactory(poolConfig);
        factory.setHostName(env.getRedisIp());
        factory.setUsePool(true);
        factory.setPort(env.getRedisPort());
        LOGGER.info("---------REDIS----------" + factory.getHostName() + " -- " + factory.getPort());
        return factory;
    }

    @Bean(name = "redisUserTemplate")
    public RedisTemplate<String, String> redisTemplateUser(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
