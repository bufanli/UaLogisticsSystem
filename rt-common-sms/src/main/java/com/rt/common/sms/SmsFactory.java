package com.rt.common.sms;

import com.rt.common.sms.config.SmsConfig;
import com.rt.common.sms.config.SmsProperties;
import com.rt.common.sms.config.SmsType;
import com.rt.common.sms.impl.YunpianSmsApi;

/**
 * @ClassName: OSSFactory
 * @Description: 文件上传Factory
 * @author: <a href="edeis@163.com">edeis</a>
 * @version: V1.0.0
 * @date: 2017-8-1
 */
public  class SmsFactory {

    public static SmsService build(SmsProperties config){
        if(config.getType() == SmsType.YUNPIAN.getValue()){
            return new YunpianSmsApi(config);
        }else if(config.getType() == SmsType.ALIYUN.getValue()){
        }

        return null;
    }

}
