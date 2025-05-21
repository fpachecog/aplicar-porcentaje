package com.tenpo.calculos.infrastructurecross.config;

import io.lettuce.core.ReadFrom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.timeout}")
    private Integer redisTimeout;

    @Value("${cache.porcentaje.duracion}")
    private Integer porcentajeCacheDuracion;

    @Bean
    LettuceConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration configuracion = new RedisStandaloneConfiguration();
        configuracion.setHostName(getRedisHost().split(":")[0]);
        configuracion.setPort(Integer.parseInt(getRedisHost().split(":")[1]));
        configuracion.setPassword(getRedisPassword());

        return new LettuceConnectionFactory(configuracion, getClientConfigurationLocal());
    }

    private LettuceClientConfiguration getClientConfigurationLocal() {
        return LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                .commandTimeout(Duration.ofMillis(getRedisTimeout()))
                .shutdownTimeout(Duration.ZERO)
                .build();
    }

    @Bean
    StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        Duration duracionCache = Duration.ofMinutes(getPorcentajeCacheDuracion());

        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        cacheConfigurations.put("porcentaje", RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(duracionCache));

        return RedisCacheManager.builder(connectionFactory)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }

    public String getRedisHost() {
        return redisHost;
    }

    public String getRedisPassword() {
        return redisPassword;
    }

    public Integer getRedisTimeout() {
        return redisTimeout;
    }

    public Integer getPorcentajeCacheDuracion() {
        return porcentajeCacheDuracion;
    }
}
