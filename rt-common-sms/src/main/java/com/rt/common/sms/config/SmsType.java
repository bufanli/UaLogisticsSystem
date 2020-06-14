package com.rt.common.sms.config;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="edeis@163.com">edeis</a>
 * @version V1.0.0
 * @date 2017-8-1
 */
public enum SmsType {
    /**
     * 七牛云
     */
    YUNPIAN(1),
    /**
     * 阿里云
     */
    ALIYUN(2);

    private int value;

    private SmsType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
