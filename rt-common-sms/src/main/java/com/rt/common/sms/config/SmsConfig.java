package com.rt.common.sms.config;


import com.rt.common.sms.SmsFactory;
import com.rt.common.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName:  IMConfig
 * @Description:
 * @author:  <a href="liuyafengwy@163.com">luffy</a>
 * @date:  2019/4/28 15:05
 */
//@Configuration
public class SmsConfig {

    @Autowired
    private SmsProperties smsProperties;

    @Bean
    public SmsService smsService(){
        return SmsFactory.build(smsProperties);
    }
}
