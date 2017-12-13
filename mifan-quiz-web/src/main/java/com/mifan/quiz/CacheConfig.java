package com.mifan.quiz;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author quzile
 * @version 1.0
 * @since 2017/7/15
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Primary
    @Bean
    public RedisCacheManager cacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache/ehcache.xml"));
        factoryBean.setCacheManagerName("applicationCacheManager");
        return factoryBean;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        EhCacheCacheManager manager = new EhCacheCacheManager();
        manager.setCacheManager(ehCacheManagerFactoryBean().getObject());
        return manager;
    }

}
