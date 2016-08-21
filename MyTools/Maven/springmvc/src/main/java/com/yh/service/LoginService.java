package com.yh.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class LoginService {

	public ModelAndView doLogin(String uname, String upasswd) {
		if(uname == null || uname.isEmpty()){
			return new ModelAndView("login", "error", "用户名不能为空");
		}
		if(upasswd == null || upasswd.isEmpty()){
			return new ModelAndView("login", "error", "密码不能为空");
		}
		if("admin".equals(uname) && "admin".equals(upasswd)){
			return new ModelAndView("success");
		}
		return new ModelAndView("login", "error", "用户名或密码错误");
	}

}
