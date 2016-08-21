package com.yh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yh.service.LoginService;

@Controller
public class LoginController {

	@Resource
	LoginService service;
	
	//http://localhost:8080/springmvc/index.html
	@RequestMapping("index")
	public String toLoginPage(){
		return "login";
	}
	
	@RequestMapping("login")
	public ModelAndView doLogin(String uname, String upasswd){
		return service.doLogin(uname,upasswd);
	}
}
