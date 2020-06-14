package com.rt.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author monkey_lwy@163.com
 * @date 2019-02-27 17:56
 * @desc
 */
public final class StrToMapUtil {

    public static Map<String, String> strToMap(String str){
        if (str == null || str.equals("")) {
            return null;
        }
        String[] strs = str.split("&");
        Map<String, String> m = new HashMap<String, String>();
        for(String s:strs){
            String[] ms = s.split("=");
            if(ms.length>1){
                m.put(ms[0], ms[1]);
            }
        }
        return  m;
    }


}
