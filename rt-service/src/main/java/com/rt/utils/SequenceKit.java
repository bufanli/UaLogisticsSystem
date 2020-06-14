package com.rt.utils;

import com.rt.common.utils.SpringContextUtil;
import org.joda.time.DateTime;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Auther: edeis
 * @Date: 2018/10/31 14:56
 * @Description: 序列自增工具（每日自增）
 */
public class SequenceKit {
    public static final String TYPE_D = "D";

    public static final RedisTemplate redisTemplate = SpringContextUtil.getBean("redisTemplate", RedisTemplate.class);


    /**
     * 订单号
     *
     * @return
     */
    public static String getSequenceDoc(Long userId) {
        String key = new DateTime().toString("yyyyMMdd");
        Long no = redisTemplate.opsForValue().increment(TYPE_D + key, 1);
        return TYPE_D + userId + key + String.format("%04d", no);
    }


    public static void main(String[] args) {
        System.out.println(String.format("%04d", 12345));
    }
}
