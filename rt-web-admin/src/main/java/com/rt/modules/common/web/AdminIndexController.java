package com.rt.modules.common.web;

import com.rt.common.base.controller.BaseController;
import com.rt.config.shiro.ShiroAuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminIndexController extends BaseController {


	@RequestMapping(value ={"/admin/","/admin/index"})
	public String index(){
		request.setAttribute("user", ShiroAuthenticationManager.getUserEntity());
		return "admin/index";
	}


	@RequestMapping(value = {"/admin/welcome"})
	public String welcome(){
		return "/admin/welcome";
	}
}
