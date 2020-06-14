package com.rt.common.base.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatisplus 公共字段处理
 */
public class CommonMetaObjectHandler implements MetaObjectHandler {

    protected final static Logger logger = LoggerFactory.getLogger(CommonMetaObjectHandler.class);
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("*************************");
        System.out.println("insert fill");
        System.out.println("*************************");

        Object testType = getFieldValByName("createDate", metaObject);//mybatis-plus版本2.0.9+
        System.out.println("createDate=" + testType);
        if (testType == null) {
            setFieldValByName("createDate", new Date(), metaObject);//mybatis-plus版本2.0.9+
        }

//        String create_date = "createDate";
//         metaObject.setValue(create_date, new Date());
        updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        String modify_date = "modifyDate";
//        metaObject.setValue(modify_date, new Date());
        //更新填充
        System.out.println("*************************");
        System.out.println("update fill");
        System.out.println("*************************");
        //mybatis-plus版本2.0.9+
        setFieldValByName("modifyDate", new Date(), metaObject);

    }
}
