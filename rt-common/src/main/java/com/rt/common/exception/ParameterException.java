package com.rt.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @ClassName:  ParameterException
 * @Description:  
 * @author:  <a href="liuyafengwy@163.com">luffy</a>
 * @date:  2019/5/2 22:23
 */
public class ParameterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

	private Integer code;

	public ParameterException() {
	}

	public ParameterException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public ParameterException(HttpStatus status) {
		this.status = status;
	}

	public ParameterException(String message) {
		super(message);
	}

	public ParameterException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
}
