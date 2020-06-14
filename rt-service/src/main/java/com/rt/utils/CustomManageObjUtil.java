package com.rt.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 定制化管理对象工具
 *
 * 用于手动获取被Spring管理的bean
 * 此场景通常存在于自己new的对象的依赖注入
 * 这里相当于采采用手动依赖注入
 *
 * @author wangmeng
 * @since 2020-04-16
 */
@Component
public class CustomManageObjUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //获取applicationContext上下文
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过class获取Bean.
    public static  <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
}
