package com.rt.common.exception.controller;

import com.rt.common.exception.ParameterException;
import com.rt.common.exception.RestException;
import com.rt.common.exception.ServiceException;
import com.rt.common.exception.SystemException;
import com.rt.common.page.R;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 * @ClassName: GlobalDefaultExceptionHandler
 * @Description: Web层异常处理器,这里可以根据不同的异常,写多个方法去处理,可以处理跳转页面请求,
 * 跳到异常指定的错误页,也可以处理Ajax请求,根据不同异常,在页面输出不同的提示信息;
 * </p>operateExp 处理普通请求 operateExpAjax 处理Ajax请求
 * @author edeis
 * @date 2016年7月12日 下午3:14:17
 *
 */
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

	/*
	 * 如果抛出UnauthorizedException，将被该异常处理器截获来显示没有权限信息
	 */
	@ExceptionHandler({ UnauthorizedException.class })
    @ResponseStatus(HttpStatus.FORBIDDEN)
	public R unauthenticatedException(UnauthorizedException ex) {
      return R.error(403,"没有权限");
    }


    @ExceptionHandler({ UnauthenticatedException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R unauthenticatedException(UnauthenticatedException ex) {
        return R.error(401,"token错误");
    }


    @ExceptionHandler({ MissingServletRequestParameterException.class })
    @ResponseStatus(HttpStatus.OK)
    public R unauthenticatedException(MissingServletRequestParameterException ex) {
        logger.error(ex.getMessage(), ex);
        return R.error(10,"参数错误");
    }


    /**
     * 用户未登录
     *
     * @author fengshuonan
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R unAuth(AuthenticationException e) {
        return R.error(401,"认证失败");
    }




	/**
	 * @Title: operateExp
	 * @Description: 全局异常控制，记录日志
	 *               任何一个方法发生异常，一定会被这个方法拦截到。然后，输出日志。封装Map并返回给页面显示错误信息：
	 *               特别注意：返回给页面错误信息只在开发时才使用，上线后，要把错误页面去掉，只打印log日志即可，防止信息外漏
	 * @param: @param ex
	 * @param: @param request
	 * @return: String
	 * @throws:
	 */
	@ExceptionHandler(SystemException.class)
	public R operateSystemException(SystemException ex, HttpServletRequest request) {
		logger.error(ex.getMessage(), ex);
		return R.error();
	}



    /**
     * 处理RestException.
     */
    @ExceptionHandler(value = { RestException.class })
    public R handleException(RestException ex, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        return R.error();
    }

    /**
     * 处理RestException.
     */
    @ExceptionHandler(value = { ParameterException.class })
    public R handleException(ParameterException ex) {
        logger.error(ex.getMessage(), ex);
        return R.error(ex.getCode(),ex.getMessage());
    }

    /**
     * 处理ServiceException
     */
    @ExceptionHandler(value = { ServiceException.class })
    public R handleException(ServiceException ex) {
        logger.error(ex.getMessage(), ex);
        return R.error(1,ex.getMessage());
    }


    /**
     * 拦截未知的运行时异常
     * @param
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        logger.error("运行时异常:", e);
        return R.error();
    }

    @ExceptionHandler(RuntimeException.class)
    public R handleException(RuntimeException e) {
        logger.error("运行时异常:", e);
        return R.error();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error("数据库中已存在该记录:", e);
        return R.error("该记录已存在");
    }

//    /**
//     * 账号被冻结
//     *
//     * @author fengshuonan
//     */
//    @ExceptionHandler(DisabledAccountException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public String accountLocked(DisabledAccountException e, Model model) {
//        String username = getRequest().getParameter("username");
//        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "账号被冻结", getIp()));
//        model.addAttribute("tips", "账号被冻结");
//        return "/login.html";
//    }
//
//    /**
//     * 账号密码错误
//     *
//     * @author fengshuonan
//     */
//    @ExceptionHandler(CredentialsException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public String credentials(CredentialsException e, Model model) {
//        String username = getRequest().getParameter("username");
//        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "账号密码错误", getIp()));
//        model.addAttribute("tips", "账号密码错误");
//        return "/login.html";
//    }
//
//    /**
//     * 验证码错误
//     *
//     * @author fengshuonan
//     */
//    @ExceptionHandler(InvalidKaptchaException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String credentials(InvalidKaptchaException e, Model model) {
//        String username = getRequest().getParameter("username");
//        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "验证码错误", getIp()));
//        model.addAttribute("tips", "验证码错误");
//        return "/login.html";
//    }
//
//    /**
//     * 无权访问该资源
//     *
//     * @author fengshuonan
//     */
//    @ExceptionHandler(UndeclaredThrowableException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ResponseBody
//    public ErrorTip credentials(UndeclaredThrowableException e) {
//        getRequest().setAttribute("tip", "权限异常");
//        log.error("权限异常!", e);
//        return new ErrorTip(BizExceptionEnum.NO_PERMITION.getCode(),BizExceptionEnum.NO_PERMITION.getMessage());
//    }
//


//
//    /**
//     * session失效的异常拦截
//     *
//     * @author stylefeng
//     * @Date 2017/6/7 21:02
//     */
//    @ExceptionHandler(InvalidSessionException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String sessionTimeout(InvalidSessionException e, Model model, HttpServletRequest request, HttpServletResponse response) {
//        model.addAttribute("tips", "session超时");
//        assertAjax(request, response);
//        return "/login.html";
//    }
//
//    /**
//     * session异常
//     *
//     * @author stylefeng
//     * @Date 2017/6/7 21:02
//     */
//    @ExceptionHandler(UnknownSessionException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String sessionTimeout(UnknownSessionException e, Model model, HttpServletRequest request, HttpServletResponse response) {
//        model.addAttribute("tips", "session超时");
//        assertAjax(request, response);
//        return "/login.html";
//    }
//
//    private void assertAjax(HttpServletRequest request, HttpServletResponse response) {
//        if (request.getHeader("x-requested-with") != null
//                && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
//            //如果是ajax请求响应头会有，x-requested-with
//            response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
//        }
//    }
//



}
