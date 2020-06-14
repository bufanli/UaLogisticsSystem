package com.rt.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断是否为ajax请求
 * @author edeis
 *
 */
public class AjaxUtils {

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	public static boolean isAjaxUploadRequest(HttpServletRequest request) {
		return request.getParameter("ajaxUpload") != null;
	}
	
	private AjaxUtils() {}

}
