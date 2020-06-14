package com.rt.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.Set;

@Configuration
public class JedisUtil implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(JedisUtil.class);

    private static ApplicationContext applicationContext = null;

    private static JedisPool jedisPool = null;


    public JedisUtil(){}

    public static Jedis getJedis(){
        synchronized (Jedis.class){
            return  getJedisPool().getResource();
        }
    }

    public static JedisPool getJedisPool(){
        if (jedisPool ==null){
            synchronized (JedisPool.class){
                if (jedisPool==null){
                    jedisPool = applicationContext.getBean(JedisPool.class);
                }
            }
        }
        return jedisPool;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(JedisUtil.applicationContext == null){
            JedisUtil.applicationContext  = applicationContext; //初始化 spring applicationContext
        }
    }

    /**
     * 根据key查看是否存在
     * @param key
     * @return
     */
    public static boolean hasKey(String key){
        boolean flag = false;
        Jedis jedis = getJedis();
        try{
            flag= jedis .exists(key);
        }catch (Exception e){
            log.error("redis异常",e);
        }finally {
            jedis.close();
        }
        return flag;
    }

    /**
     * 设置key -value 形式数据
     * @param key
     * @param value
     * @return
     */
    public static String set(String key,String value){
        Jedis jedis = getJedis();
        String result=null;
        try{
            result  =  jedis.set(key,value);
        }catch (Exception e){
            log.error("redis异常",e);
        }finally {
            jedis.close();
        }
        return result;
    }

    /**
     * 设置 一个过期时间
     * @param key
     * @param value
     * @param timeOut 单位秒
     * @return
     */
    public static String set(String key,String value,int timeOut){
        Jedis jedis = getJedis();
        String result=null;
        try{
            result  =  jedis.setex(key,timeOut,value);
        }catch (Exception e){
            log.error("redis异常",e);
        }finally {
            jedis.close();
        }
        return result;
    }

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public static String getByKey(String key){
        Jedis jedis = getJedis();
        String result=null;
        try{
            result  =  jedis.get(key);
        }catch (Exception e){
            log.error("redis异常",e);
        }finally {
            jedis.close();
        }
        return result;
    }

    /**
     * 根据通配符获取所有匹配的key
     * @param pattern
     * @return
     */
    public static Set<String> getKesByPattern(String pattern){
        Jedis jedis = getJedis();
        Set<String> result=null;
        try{
            result  =  jedis.keys(pattern);
        }catch (Exception e){
            log.error("redis异常",e);
        }finally {
            jedis.close();
        }
        return result;
    }

    /**
     * 根据key删除
     * @param key
     */
    public static void delByKey(String key){
        Jedis jedis = getJedis();
        try{
            jedis.del(key);
        }catch (Exception e){
            log.error("redis异常",e);
        }finally {
            jedis.close();
        }
    }

    /**
     * 根据key获取过期时间
     * @param key
     * @return
     */
    public static long getTimeOutByKey(String key){
        Jedis jedis = getJedis();
        Long result=null;
        try{
            result  =  jedis.ttl(key);
        }catch (Exception e){
            log.error("redis异常",e);
        }finally {
            jedis.close();
        }
        return result;
    }

    /**
     * 清空数据 【慎用啊！】
     */
    public static void flushDB(){
        Jedis jedis = getJedis();
        try{
            jedis.flushDB();
        }catch (Exception e){
            log.error("redis异常",e);
        }finally {
            jedis.close();
        }
    }

    /**
     * 刷新过期时间
     * @param key
     * @param timeOut
     * @return
     */
    public static long refreshLiveTime(String key,int timeOut){
        Jedis jedis = getJedis();
        Long result=null;
        try{
            result  =  jedis.expire(key,timeOut);
        }catch (Exception e){
            log.error("redis异常",e);
        }finally {
            jedis.close();
        }
        return result;
    }

    /**
     * key 值自增数字
     * @param key
     * @param num
     */
    public static void increment(String key,Long num){
        Jedis jedis = getJedis();
        try{
            jedis.incrBy(key,num);
        }catch (Exception e){
            log.error("redis异常",e);
        }finally {
            jedis.close();
        }
    }

    /**
     * zet  自增数字
     * @param key
     * @param num
     */
    public static void zincrement(String key,Double num,String member){
        Jedis jedis = getJedis();
        try{
            jedis.zincrby(key,num,member);
        }catch (Exception e){
            log.error("redis异常",e);
        }finally {
            jedis.close();
        }
    }

    /**
     *  按照递减的顺序获取 集合中的值
     * @param key
     * @param start
     * @param end
     */
    public static Set<String> zrevrange(String key,Integer start,Integer end){
        Jedis jedis = getJedis();
        Set<String> result=null;
        try{
            result  =  jedis.zrevrange(key,start,end);
        }catch (Exception e){
            log.error("redis异常",e);
        }finally {
            jedis.close();
        }
        return result;
    }

    /**
     * 返回有序集 key 中，指定区间内的成员。
     * @param key
     * @param start 以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推 可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推
     * @param end 以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推 可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推
     * @return
     */
    public static Set<Tuple> zrange(String key,Integer start,Integer end) {
        Jedis jedis = getJedis();
        Set<Tuple> result=null;
        try{
            result  =  jedis.zrangeWithScores(key, start, end);
        }catch (Exception e){
            log.error("redis异常",e);
        }finally {
            jedis.close();
        }
        return result;
    }

    /**
     * 发布消息
     * @param key
     * @param value
     */
    public static void publish(String key,String value){
        Jedis jedis = getJedis();
        try{
            jedis.publish(key,value);
        }catch (Exception e){
            log.error("redis异常",e);
        }finally {
            jedis.close();
        }
    }

    /**
     *  向队列中 添加数据
     * @param key
     * @param value
     */
    public static void push(String key,String value){
        Jedis jedis = getJedis();
        try{
            jedis.lpush(key,value);
        }catch (Exception e){
            log.error("redis异常",e);
        }finally {
            jedis.close();
        }
    }

    /**
     * 从队列中 获取数据
     * @param key
     */
    public static String pop(String key){
        Jedis jedis = getJedis();
        String result = null;
        try{
            result =  jedis.rpop(key);
        }catch (Exception e){
            log.error("redis异常",e);
        }finally{
            jedis.close();
        }
        return result;
    }
}