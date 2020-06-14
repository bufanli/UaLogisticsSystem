package com.rt.common.sms;

import com.rt.common.sms.config.SmsConfig;
import com.rt.common.sms.config.SmsProperties;

import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName: CloudStorageService
 * @Description:  云存储(支持七牛、阿里云、腾讯云、又拍云)
 * @author: <a href="edeis@163.com">edeis</a>
 * @version: V1.0.0
 * @date: 2017-8-1
 */
public abstract class SmsService {


    /** 云存储配置信息 */
    public SmsProperties config;

    /**
     * 手机号,短信内容
     * @param mobile
     * @param text
     * @return
     */
    public abstract String singleSend( String mobile,String text);

}
