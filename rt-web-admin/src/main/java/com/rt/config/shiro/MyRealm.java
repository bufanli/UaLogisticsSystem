package com.rt.config.shiro;

import com.rt.common.shiro.ShiroUser;
import com.rt.modules.sys.entity.Resource;
import com.rt.modules.sys.entity.Role;
import com.rt.modules.sys.entity.User;
import com.rt.common.utils.Encodes;
import com.rt.modules.sys.service.ResourceService;
import com.rt.modules.sys.service.RoleService;
import com.rt.modules.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: MyRealm
 * @Description: 自定义Realm
 * @author: <a href="edeis@163.com">edeis</a>
 * @version: V1.0.0
 * @date: 2017/4/26
 */
@Component
public class MyRealm extends AuthorizingRealm {




    @Autowired
    //shiro 使用redis缓存导致spring cache失效，需要在自定义的realm中的Autowired添加@Lazy注解
    // http://stackoverflow.com/questions/21512791/spring-service-with-cacheable-methods-gets-initialized-without-cache-when-autowi
    @Lazy
    private UserService userService;
    @Autowired
    @Lazy
    private RoleService roleService;
    @Autowired
    @Lazy
    private ResourceService resourceService;

    // 是否使用验证码
    protected boolean useCaptcha = false;
    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);

    public MyRealm() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ShiroAuthenticationManager.ALGORITHM);
        matcher.setHashIterations(ShiroAuthenticationManager.INTERATIONS);
        setCredentialsMatcher(matcher);

    }


    /**
     * 认证信息,认证回调函数,登录时调用
     * </br>首先根据传入的用户名获取User信息；然后如果user为空，那么抛出没找到帐号异常UnknownAccountException；
     * </br>如果user找到但锁定了抛出锁定异常LockedAccountException；最后生成AuthenticationInfo信息，
     * </br>交给间接父类AuthenticatingRealm使用CredentialsMatcher进行判断密码是否匹配，
     * </br>如果不匹配将抛出密码错误异常IncorrectCredentialsException；
     * </br>另外如果密码重试次数太多将抛出超出重试次数异常ExcessiveAttemptsException；
     * </br>在组装SimpleAuthenticationInfo信息时， 需要传入：身份信息（用户名）、凭据（密文密码）、加密盐（username+salt），
     * </br>CredentialsMatcher使用盐加密传入的明文密码和此处的密文密码进行匹配。
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        logger.info("######## 执行Shiro身份认证 ########");
//        if (useCaptcha) {
//            CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
//            logger.info("doGetAuthenticationInfo: 验证当前Subject时获取到token为：" + token);
//            String parm = token.getCaptcha();
//        }

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        logger.debug("doGetAuthenticationInfo: 当前用户username <== " + username);
        User user = userService.getByUserName(username);
        logger.debug("doGetAuthenticationInfo: 从数据库查出的用户user <== " + user);
        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        // 账号锁定
        if (user.getLocked() == 1) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        ShiroUser shiroUser = new ShiroUser(user.getId(), user.getUsername(), user.getNickname());
        byte[] salt = Encodes.decodeHex(user.getSalt());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(shiroUser, user.getPassword(), ByteSource.Util.bytes(salt), getName());
        return info;
    }


    /**
     * 授权信息
     * 只有需要验证权限时才会调用, 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.在配有缓存的情况下，只加载一次.
     * 用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.<br/>
     * 授权(权限控制):<br/>
     *  1、如果用户正常退出，缓存自动清空；<br/>
     *  2、如果用户非正常退出，缓存自动清空；<br/>
     *  3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     *  （需要手动编程进行实现；放在service进行调用）在权限修改后调用realm中的方法，realm已经由spring管理，
     *  所以从spring中获取realm实例，调用clearCached方法；<br/>
     *  Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*
         * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
         * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
         * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
         * 缓存过期之后会再次执行。
         */
        logger.info("######## 执行Shiro权限认证 ########");
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        logger.info("doGetAuthorizationInfo: username <== " + shiroUser.getUsername());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User dbUser = userService.getById(shiroUser.getId());
        Set<String> shiroPermissions = new HashSet<>();
        Set<String> roleSet = new HashSet<String>();
        List<Role> roles = roleService.listByUserId(dbUser.getId());
//        List<Role> roles = dbUser.getRoles();
        for (Role role : roles) {
            List<Resource> resources = resourceService.listByRoleId(role.getId());
            for (Resource resource : resources) {
                if(StringUtils.isNotBlank(resource.getSourceKey())){
                    shiroPermissions.add(resource.getSourceKey());
                }
            }
            roleSet.add(role.getRoleKey());
        }
        authorizationInfo.setRoles(roleSet);
        logger.info("#####用户角色####"+roleSet);
        logger.info("#####用户权限####"+shiroPermissions);
        authorizationInfo.setStringPermissions(shiroPermissions);
        return authorizationInfo;
    }






    /**
     * 清除当前用户权限信息
     */
    public void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        super.clearCachedAuthorizationInfo(principalCollection);
    }

    /**
     * 清除当前用户认证信息
     */
    public void clearCachedAuthenticationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        super.clearCachedAuthenticationInfo(principalCollection);
    }

    /**
     * 清除指定 principalCollection 的权限信息
     */
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        super.clearCachedAuthorizationInfo(principalCollection);
    }

    /**
     * 清除用户认证信息
     */
    public void clearCachedAuthenticationInfo(PrincipalCollection principalCollection) {
        super.clearCachedAuthenticationInfo(principalCollection);
    }


    /**
     * 清除当前用户的认证和授权缓存信息
     */
    public void clearAllCache() {
        clearCachedAuthorizationInfo();
        clearCachedAuthenticationInfo();
    }
}
