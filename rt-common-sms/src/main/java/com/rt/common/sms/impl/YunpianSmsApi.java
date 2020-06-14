package com.rt.common.sms.impl;

import com.rt.common.sms.SmsService;
import com.rt.common.sms.config.SmsProperties;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
/**
 * @ClassName:  SmsApi
 * @Description:  短信服务
 * @author:  <a href="liuyafengwy@163.com">luffy</a>
 * @date:  2019/5/6 12:00
 */
public class YunpianSmsApi extends SmsService {


    // 查账户信息的http地址
    private static String URI_GET_USER_INFO = "http://yunpian.com/v1/user/get.json";

    //单条发送接口的http地址
    private static String URI_SINGLE_SEND_SMS = "https://sms.yunpian.com/v2/sms/single_send.json";


    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    public YunpianSmsApi(SmsProperties properties){
        this.config = properties;
    }



    /**
     * @param mobile 接收的手机号,仅支持单号码发送
     * @param text 需要使用已审核通过的模板或者默认模板
     * @return
     */
    public String singleSend(String mobile,String text) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", config.getApikey());
        params.put("text", text);
        params.put("mobile", mobile);
        return post(URI_SINGLE_SEND_SMS, params);
    }


    /**
     * 批量发送短信,相同内容多个号码,智能匹配短信模板
     *
     * @param apikey 成功注册后登录云片官网,进入后台可查看
     * @param text   需要使用已审核通过的模板或者默认模板
     * @param mobile 接收的手机号,多个手机号用英文逗号隔开
     * @return json格式字符串
     */
    public static String batchSend(String apikey, String text, String mobile) {
        Map<String, String> params = new HashMap<String, String>();//请求参数集合
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post("https://sms.yunpian.com/v2/sms/batch_send.json",  params);//请自行使用post方式请求,可使用Apache HttpClient
    }


    /**
     * 取账户信息
     * @return json格式字符串
     * @throws java.io.IOException
     */
    public static String getUserInfo(String apikey) throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        return post(URI_GET_USER_INFO, params);
    }


    /**
     * 基于HttpClient 3.1的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */
    public static String post(String url, Map<String, String> paramsMap) {
        HttpClient client = new HttpClient();
        try {
            PostMethod method = new PostMethod(url);
            if (paramsMap != null) {
                NameValuePair[] namePairs = new NameValuePair[paramsMap.size()];
                int i = 0;
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new NameValuePair(param.getKey(), param.getValue());
                    namePairs[i++] = pair;
                }
                method.setRequestBody(namePairs);
                HttpMethodParams param = method.getParams();
                param.setContentCharset(ENCODING);
            }
            client.executeMethod(method);
            return method.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
