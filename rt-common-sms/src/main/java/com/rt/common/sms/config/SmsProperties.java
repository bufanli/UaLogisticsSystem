package com.rt.common.sms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName:  YunpianConfig
 * @Description:  云片短信配置
 * @author:  <a href="liuyafengwy@163.com">luffy</a>
 * @date:  2019/5/6 11:32
 */
//@Component
//@ConfigurationProperties(prefix = "sms")
public class SmsProperties {
    //1:云片   2:阿里
    private Integer type;

    private String apikey;


    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
