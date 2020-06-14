package com.rt.common.web;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParamsUtils {
    private static final Logger logger = LoggerFactory.getLogger(ParamsUtils.class);
	   /**
	 * 从request中获得参数Map，并返回可读的Map
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getParameterMap(HttpServletRequest request) {
		// 参数Map
		Map<String, String[]> properties = request.getParameterMap();
		// 返回值Map
		Map<String, String> returnMap = new HashMap<String, String>();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if(null == valueObj){
				value = "";
			}else if(valueObj instanceof String[]){
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){
					value = values[i] + ",";
				}
				value = value.substring(0, value.length()-1);
			}else{
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}


	public static Map<String, Object> getParameterDatatables(HttpServletRequest request) {
		String param = request.getParameter("extra_search");
		// 返回值Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(param)){
			String params[] = param.split("&");
			if(params!= null && params.length>0){
				for(String p :params){
					String s[] = p.split("=");
					String key = s[0];
					String value = s.length>1?s[1]:null;
					if(value!=null){
                        try {
                            returnMap.put(key, URLDecoder.decode(value, "utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            logger.error("获取请求参数错误",e);
                        }
                    }
				}
			}
		}
		return returnMap;
	}
}
