package com.rt.common.utils.sign;

import com.rt.common.web.ParamsUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static java.net.URLEncoder.encode;

public class Md5SecurityUtil {

	private static Logger logger = LoggerFactory.getLogger(Md5SecurityUtil.class);
	
	/** 签名参数名 */
	public final static String OAUTH_SIGNATURE = "sign";
	/** 时间戳参数名 */
	public final static String OAUTH_TIMESTAMP = "timestamp";
	
	/**
	 * 生成md5 签名
	 * @param nameValuePairs
	 * @return
	 * @throws Exception
	 */
	public static String getSignatureValue(List<NameValuePair> nameValuePairs) throws Exception {
		
		Map<String, String> parameterMap = new HashMap<String, String>();
		for (NameValuePair pair : nameValuePairs) {
			parameterMap.put(pair.name, pair.value);
		}
		
		ArrayList<NameValuePair> currentPairs = new ArrayList<NameValuePair>(nameValuePairs);
		
		String timestamp = System.currentTimeMillis() + "";
		
		if (!parameterMap.containsKey(OAUTH_TIMESTAMP)){
			currentPairs.add(new NameValuePair(OAUTH_TIMESTAMP, timestamp));
		}
		String signatureBaseString = getSignatureBaseString(nameValuePairs);
	    String signatureValue = getSignatureValue(signatureBaseString);
	    
		return signatureValue;
	}

	/**
	 * 组装请求的url(多用于测试)
	 * @param nameValuePairs 除oauth_signature 外的参数值对集合
	 * @return
	 * @throws Exception
	 */
	public static String buildUrl(List<NameValuePair> nameValuePairs) throws Exception {

		//将除oauth_signature 外的参数集合按照字母升序排列
		Collections.sort(nameValuePairs, new Comparator<NameValuePair>() {
			@Override
			public int compare(NameValuePair o1, NameValuePair o2) {
				return o1.name.compareTo(o2.name);
			}
		});

		logger.trace("#pairs_sort {}", nameValuePairs);

		StringBuilder sb = new StringBuilder(40);
		for (int i=0;i< nameValuePairs.size();i++) {
			NameValuePair pair = nameValuePairs.get(i);
			if(i >0)
				sb.append("&");
			sb.append(pair.name).append("=").append(pair.value);
		}
		String signatureBaseString = sb.toString();

		logger.debug("#buildurl: {}", signatureBaseString );
		return signatureBaseString;
	}
	/**
	 * 获取签名
	 * 获取签名基础串 -->> urlencode(a=x&b=y&...)
	 * @param nameValuePairs 除oauth_signature 外的参数值对集合
	 * @return
	 * @throws Exception
	 */
	private static String getSignatureBaseString(List<NameValuePair> nameValuePairs) throws Exception {
		
		//将除oauth_signature 外的参数集合按照字母升序排列
		Collections.sort(nameValuePairs, new Comparator<NameValuePair>() {
			@Override
			public int compare(NameValuePair o1, NameValuePair o2) {
				return o1.name.compareTo(o2.name);
			}
		});
		
		logger.trace("#pairs_sort {}", nameValuePairs);
		
		StringBuilder sb = new StringBuilder(40);
		for (int i=0;i< nameValuePairs.size();i++) {
			NameValuePair pair = nameValuePairs.get(i);
			if(i >0)
				sb.append("%26");
			sb.append(encode(pair.name, "utf-8")).append("%3D").append(encode(pair.value, "utf-8"));
		}
		String signatureBaseString = sb.toString();

		logger.debug("#signatureBaseString: {}", signatureBaseString );
		return signatureBaseString;
	}
	
	/**
	 * 
	 * @param signatureBaseString 签名基础串
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	private static String getSignatureValue(String signatureBaseString)
			throws NoSuchAlgorithmException, InvalidKeyException {
		
		String signatureValue = MD5.GetMD5Code(signatureBaseString, true);
		

		logger.debug("signatureBaseString:{}, signatureValue:{}", signatureBaseString, signatureValue);
		return signatureValue;
	}


	public static boolean verifyOauthSignatureV2(HttpServletRequest request){

		Map<String ,String > params = ParamsUtils.getParameterMap(request);
		HashMap<String ,String > newPara = new HashMap<String ,String>();
		String params_sign = "";
		for(String key : params.keySet()){
			if(OAUTH_SIGNATURE.equalsIgnoreCase(key)){
				params_sign = params.get(key);
			}else{
				newPara.put(key,params.get(key));
			}
		}
		String sign = ParamSignUtils.genSign(newPara,"");
		if(sign.equalsIgnoreCase(params_sign)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 签名验证
	 * @param request
	 * @return
	 */
	public static boolean verifyOauthSignature(HttpServletRequest request) {
		boolean ret = true;
		
		List<NameValuePair> requestParameters = new ArrayList<NameValuePair>();
		
	    
	    Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			requestParameters.add(new NameValuePair(name, request.getParameter(name)));
		}
		
		List<NameValuePair> executeParameter = new ArrayList<NameValuePair>(requestParameters.size());
		String signatureVlaue = "";
		for (NameValuePair pair : requestParameters) {
			if (OAUTH_SIGNATURE.equals(pair.name)) {
				//提取sign
				signatureVlaue = pair.value;
			}
			else {
				executeParameter.add(pair);
			}
		}
		
		if (StringUtils.isBlank(signatureVlaue)) {
			logger.info("签名验证为空");
			return false;
		}
		
		try{
			String signatureBaseString = getSignatureBaseString( executeParameter);
			String computationalSignatureValue = getSignatureValue(signatureBaseString);
			if (!signatureVlaue.equals(computationalSignatureValue)) {
				logger.debug("签名错误, 请求url:{}, 客户端签名{}, 验证签名{}", request.getRequestURL(), signatureVlaue, computationalSignatureValue);
				return false;
			}
		}	catch(Exception e){
			logger.error("签名验证异常", e);
			ret = false;
		}
		return ret;
	}
	
	
	public static void main(String[] args) throws Exception {
		List<NameValuePair> l = new ArrayList<NameValuePair>();
		l.add(new NameValuePair("timestamp", "1461840803"));
		l.add(new NameValuePair("token", "D015B1D1A4080ED1F7CD93CEB7294B2E"));
		l.add(new NameValuePair("uid", "2"));
//		l.add(new NameValuePair("course_num", "1"));
//		l.add(new NameValuePair("token", "D015B1D1A4080ED1F7CD93CEB7294B2E"));
//		l.add(new NameValuePair("timestamp", "1461838258"));

		System.out.println(buildUrl(l));
		String s = getSignatureBaseString(l);
		System.out.println(s);
		System.out.println(getSignatureValue(l));

	}
	
	
}