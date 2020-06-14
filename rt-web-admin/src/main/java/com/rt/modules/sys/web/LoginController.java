package com.rt.modules.sys.web;

import com.google.code.kaptcha.Constants;
import com.rt.common.base.controller.BaseController;
import com.rt.common.log.LogManager;
import com.rt.common.log.factory.LogTaskFactory;
import com.rt.config.shiro.ShiroAuthenticationManager;
import com.rt.modules.sys.entity.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = {"/admin/login"}, method = RequestMethod.GET)
    public String login() {
        return "admin/login";
    }


    @RequestMapping(value = {"/admin/login"}, method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("captcha") String captcha, ModelMap model) {
        try {
            String kaptcha = ShiroAuthenticationManager.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
            if(StringUtils.isEmpty(captcha)){
                model.put("errors", "请输入验证码");
                return "admin/login";
            }

            if (!captcha.equalsIgnoreCase(kaptcha)) {
                model.put("errors", "验证码不正确");
                return "admin/login";
            }

//            Subject subject = SecurityUtils.getSubject();
//            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//            subject.login(token);
//            LogManager.me().executeLog(LogTaskFactory.loginLog(ShiroAuthenticationManager.getUserEntity().getId(), request.getRemoteHost()));
            User user = ShiroAuthenticationManager.getUserEntity();
//            model.addAttribute("user",user);
            model.addAttribute("user",null);
            return redirect("/admin/index");
        } catch (UnknownAccountException e) {
            logger.error(e.getMessage());
            model.put("errors", "未知账户");
        } catch (IncorrectCredentialsException e) {
            logger.error(e.getMessage());
            model.put("errors", "密码错误");
        } catch (LockedAccountException e) {
            logger.error(e.getMessage());
            model.put("errors", "账号被锁定");
        } catch (AuthenticationException e) {
            logger.error(e.getMessage());
            model.put("errors", "验证账户失败");
        }
        return "admin/login";
    }

    @RequestMapping(value = {"/admin/logout"}, method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
//        subject.logout();todo
        return "admin/login";
    }

}
