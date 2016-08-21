package yh.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import yh.dao.AnnotateUserMapper;
import yh.dao.UserDao;
import yh.dao.UserMapper;
import yh.model.User;

@Controller
public class MyController  {
	
	@Resource
	private AnnotateUserMapper annotateUserMapper;
	
	@Autowired 
	private SqlSessionTemplate sqlSession;
	
	@Resource(name="userDaoImpl")
	private UserDao userDao;
	
	//http://localhost:8080/SpringMVC/hello
	@RequestMapping(value="/hello")
	public Map<String,User> hello()throws Exception{
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserById(1);
		Map<String,User> map = new HashMap<String, User>();
		map.put("user", user);
		//当没有url返回的时候，springmvc默认访问的URL是前缀+ @RequestMapping +后缀
		return map;
	}
	
	//http://localhost:8080/SpringMVC/hello1
	@RequestMapping(value="/hello1")
	public User hello1()throws Exception{
		//当没有url返回的时候，springmvc默认访问的URL是前缀+ @RequestMapping +后缀
		return userDao.findUserById(1);
	}

	//http://localhost:8080/SpringMVC/hello2
	@RequestMapping(value="/hello2",method=RequestMethod.GET)
	public ModelAndView hello2()throws Exception{
		User user = annotateUserMapper.findUserById(1);
		//对于ModelAndView构造函数可以指定返回页面的名称，也可以通过setViewName方法来设置所需要跳转的页面；
		ModelAndView modelAndView = new ModelAndView("/hello"); 
		//modelAndView.setViewName("/hello");
		modelAndView.addObject("user",user);
		return modelAndView;
	}
	
	//http://localhost:8080/SpringMVC/hello
//	@RequestMapping(value="/hello")
//	public String hello()throws Exception{
//		return "hello";
//	}
	
	
}