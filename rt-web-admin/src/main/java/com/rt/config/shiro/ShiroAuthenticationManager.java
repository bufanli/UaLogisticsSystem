package com.rt.config.shiro;

import com.rt.common.shiro.ShiroUser;
import com.rt.common.shiro.exception.IncorrectCaptchaException;
import com.rt.common.utils.SpringUtils;
import com.rt.modules.sys.entity.User;
import com.rt.modules.sys.entity.UserSessionEntity;
import com.rt.modules.sys.service.UserSessionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @ClassName: ShiroManager
 * @Description: shiro 认证信息操作工具类
 * @author edeis
 * @date 2016年7月12日 下午4:31:10
 *
 */
public class ShiroAuthenticationManager {
    public static final int INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static final String ALGORITHM = "SHA-1";
	/*
	 * 用户权限管理
	 */
	public static final MyRealm myRealm = SpringUtils.getBean("myRealm", MyRealm.class);
	public static final UserSessionService userSessionService = SpringUtils.getBean("userSessionService", UserSessionService.class);
	/*
	 * 用户session管理
	 */
//	public static final UserSessionService userSessionService = SpringContextUtil.getBean("userSessionService", UserSessionService.class);

	/**
	 * 获取shiro的session
	 * 
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * 获取shiro Subject
	 * 
	 * @return
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取用户
	 * 
	 * @return
	 */
	public static User getUserEntity() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		User user = new User();
		BeanUtils.copyProperties(shiroUser,user);
		return user;
	}

	/**
	 * 获取用户id
	 * 
	 * @return
	 */
	public static Long getUserId() {
		return getUserEntity() == null ? null : getUserEntity().getId();
	}
	
	/**
	 * 获取用户id
	 * 
	 * @return
	 */
	public static String getUserAccountName() {
		return getUserEntity() == null ? null : getUserEntity().getUsername();
	}

	/**
	 * 把值放入到当前登录用户的Session里
	 * 
	 * @param key
	 * @param value
	 */
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * 从当前登录用户的Session里取值
	 * 
	 * @param key
	 * @return
	 */
	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	/**
	 * 判断是否登录
	 * 
	 * @return
	 */
	public static boolean isLogin() {
		return null != SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 退出登录
	 */
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}




	/**
	 * 获取验证码，获取后删除
	 * 
	 * @return
	 */
	public static String getKaptcha(String key) {
		Object kaptcha = getSessionAttribute(key);
        if(kaptcha == null){
            throw new IncorrectCaptchaException("验证码已失效");
        }
		getSession().removeAttribute(key.toString());
		return kaptcha.toString();
	}
	
	
	/**
	 * 
	 * @Description	清空当前用户权限信息
	 * </br>目的：为了在判断权限的时候，系统会再次调用 <code>doGetAuthorizationInfo(...)  </code>方法加载权限信息。
	 *
	 * @author wjggwm
	 * @data 2017年1月5日 下午6:09:55
	 */
	public static void clearUserAuth(){
		myRealm.clearCachedAuthorizationInfo();
	}
	
	
	/**
	 * 
	 * @Description 根据UserIds清空权限信息
	 * @param userIds	用户ids
	 *
	 * @author wjggwm
	 * @data 2017年1月5日 下午6:07:45
	 */
	public static void clearUserAuthByUserId(Long...userIds){
		if(null == userIds || userIds.length == 0)	return ;
		List<SimplePrincipalCollection> result = userSessionService.getSimplePrincipalCollectionByUserId(userIds);

		for (SimplePrincipalCollection simplePrincipalCollection : result) {
			myRealm.clearCachedAuthorizationInfo(simplePrincipalCollection);
		}
	}


	/**
	 * 
	 * @Description 根据UserIds清空权限信息
	 * @param userIds	用户ids
	 *
	 * @author wjggwm
	 * @data 2017年1月5日 下午6:08:01
	 */
	public static void clearUserAuthByUserId(List<Long> userIds) {
		if(null == userIds || userIds.size() == 0){
			return ;
		}
		clearUserAuthByUserId(userIds.toArray(new Long[0]));
	}
	
	/**
	 * 
	 * @Description 清空所有用户权限信息
	 *
	 * @author wjggwm
	 * @data 2017年1月5日 下午6:08:01
	 */
	public static void clearAllUserAuth() {
		List<UserSessionEntity> list = userSessionService.getAllUser();
		List<Long> userIds = new ArrayList<Long>();
		list.forEach(user -> {
			userIds.add(user.getUser().getId());
		});
		clearUserAuthByUserId(userIds.toArray(new Long[0]));
	}



//    public static class HashPassword {
//        public String salt;
//        public String password;
//    }
//
//
//    public static HashPassword encrypt(String plainText) {
//        HashPassword result = new HashPassword();
//        byte[] salt = Digests.generateSalt(SALT_SIZE);
//        result.salt = Encodes.encodeHex(salt);
//
//        byte[] hashPassword = Digests.sha1(plainText.getBytes(), salt, INTERATIONS);
//        result.password = Encodes.encodeHex(hashPassword);
//        return result;
//
//    }
//
//    public static String encryptPasswort(String pwd,String salt){
//        byte[] _salt = Encodes.decodeHex(salt);
//        byte[] hashPassword = Digests.sha1(pwd.getBytes(), _salt, INTERATIONS);
//        return Encodes.encodeHex(hashPassword);
//    }



}
