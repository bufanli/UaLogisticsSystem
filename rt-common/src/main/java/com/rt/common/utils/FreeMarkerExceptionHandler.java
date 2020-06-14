package com.rt.common.utils;

import com.rt.common.exception.IllegalLicenseException;
import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.Writer;

public class FreeMarkerExceptionHandler implements TemplateExceptionHandler {

	private static final TemplateExceptionHandler DEFAULT_TEMPLATE_EXCEPTION_HANDLER = TemplateExceptionHandler.DEBUG_HANDLER;

	public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
		if (ExceptionUtils.indexOfThrowable(te, IllegalLicenseException.class) >= 0) {
			throw new IllegalLicenseException();
		}
		DEFAULT_TEMPLATE_EXCEPTION_HANDLER.handleTemplateException(te, env, out);
	}

}