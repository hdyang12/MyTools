package yh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/*
 * http://localhost:8080/SpringMVC/hello
 */
public class MyController  {
	

	@RequestMapping("/hello")
	public String hello()throws Exception{
		return "hello";
	}

}