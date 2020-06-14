package com.rt.common.log.factory;


import com.rt.common.log.LogSucceed;
import com.rt.common.log.LogType;
import com.rt.modules.sys.entity.OperationLog;

import java.util.Date;

/**
 * 日志对象创建工厂
 *
 * @author fengshuonan
 * @date 2016年12月6日 下午9:18:27
 */
public class LogFactory {

    /**
     * 创建操作日志
     *
     * @author fengshuonan
     * @Date 2017/3/30 18:45
     */
    public static OperationLog createOperationLog(LogType logType, Long userId, String bussinessName, String clazzName, String methodName, String msg, LogSucceed succeed) {
        OperationLog operationLog = new OperationLog();
        operationLog.setLogtype(logType.getMessage());
        operationLog.setLogname(bussinessName);
        operationLog.setUserid(userId);
        operationLog.setClassname(clazzName);
        operationLog.setMethod(methodName);
        operationLog.setCreatetime(new Date());
        operationLog.setSucceed(succeed.getMessage());
        operationLog.setMessage(msg);
        return operationLog;
    }

    /**
     * 创建登录日志
     *
     * @author fengshuonan
     * @Date 2017/3/30 18:46
     */
    public static OperationLog createLoginLog(LogType logType, Long userId, String msg,String ip) {
        OperationLog operationLog = new OperationLog();
        operationLog.setLogtype(logType.getMessage());
        operationLog.setLogname(logType.getMessage());
        operationLog.setUserid(userId);
        operationLog.setCreatetime(new Date());
        operationLog.setSucceed(LogSucceed.SUCCESS.getMessage());
//        operationLog.setIp(ip);
        operationLog.setMessage(msg);
        return operationLog;
    }
}
