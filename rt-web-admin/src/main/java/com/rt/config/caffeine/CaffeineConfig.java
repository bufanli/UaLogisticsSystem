package com.rt.config.caffeine;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="edeis@163.com">edeis</a>
 * @version V1.0.0
 * @date 2017/5/4
 */
//@Configuration
public class CaffeineConfig {
    private static Logger logger = LoggerFactory.getLogger(CaffeineConfig.class);


    /**
     * 必须要指定这个Bean，refreshAfterWrite=5s这个配置属性才生效
     *
     * @return
     */
    @Bean
    public CacheLoader<Object, Object> cacheLoader() {


        CacheLoader<Object, Object> cacheLoader = new CacheLoader<Object, Object>() {
            @Override
            public Object load(Object key) throws Exception {
                return null;
            }
            // 重写这个方法将oldValue值返回回去，进而刷新缓存
            @Override
            public Object reload(Object key, Object oldValue) throws Exception {
                return oldValue;
            }
        };

        return cacheLoader;
    }

    /*
     * ehcache 主要的管理器
     */
    @Bean(name = "caffeineCacheManager")
    public CaffeineCacheManager caffeineCacheManager() {
//        String specAsString = "initialCapacity=100,maximumSize=500,expireAfterAccess=5m,recordStats";
//        CaffeineCacheManager cacheManager = new CaffeineCacheManager("AIRCRAFTS", "SECOND_CACHE");
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setAllowNullValues(false); //can happen if you get a value from a @Cachable that returns null
        //cacheManager.setCacheSpecification(specAsString);
        //cacheManager.setCaffeineSpec(caffeineSpec());
        cacheManager.setCaffeine(caffeineCacheBuilder());
        cacheManager.setCacheLoader(cacheLoader());
        return cacheManager;
    }

    Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(150)
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .weakKeys()
                .removalListener(new CustomRemovalListener())
                .recordStats();
    }

    class CustomRemovalListener implements RemovalListener<Object, Object> {
        @Override
        public void onRemoval(Object key, Object value, RemovalCause cause) {
            System.out.format("removal listerner called with key [%s], cause [%s], evicted [%S]\n",
                    key, cause.toString(), cause.wasEvicted());
        }
    }
}
