package yh.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import yh.dao.AnnotateUserMapper;
import yh.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:config/spring-mvc.xml", "classpath:config/db.xml"})
public class AnnotateUserMapperTest {
	
	@Resource
	private AnnotateUserMapper annotateUserMapper;

	@Test
	public void testFindUserById() throws Exception{
		//调用userMapper的方法
		User user = annotateUserMapper.findUserById(1);
		System.out.println(user);
	}
	
	@Test
	public void testFindUser() throws Exception{
		//调用userMapper的方法
		List<User> userList = annotateUserMapper.findUser(3);
		for(User user : userList){
			System.out.println(user);
		}
	}
	
	@Test
	//第1种入参形式
	public void testFindUserByParam() throws Exception{
		User user = new User();
		user.setId(3);
		List<User> userList = annotateUserMapper.findUserByParam(user);
		for(User user1 : userList){
			System.out.println(user1);
		}
	}
	
	@Test
	//第2种入参形式
	public void testFindUserByParam1() throws Exception{
		User user = new User();
		user.setId(3);
		List<User> userList = annotateUserMapper.findUserByParam1(user);
		for(User user1 : userList){
			System.out.println(user1);
		}
	}
	
	@Test
	//第3种入参形式
	public void testFindUserByParam2() throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 3);
		List<User> userList = annotateUserMapper.findUserByParam2(map);
		for(User user1 : userList){
			System.out.println(user1);
		}
	}
	
	@Test
	public void testInsertUser() throws Exception{
		User user = new User();
		user.setId(3);
		user.setBirthday(new Date());
		user.setUsername("李四");
		//调用userMapper的方法
		annotateUserMapper.insertUser(user);
	}
	
	@Test
	public void testDeleteUser() throws Exception{
		annotateUserMapper.deleteUser(3);
	}
	
	@Test
	public void testUpdateUser() throws Exception{
		User user = annotateUserMapper.findUserById(1);
		user.setUsername("王老汉");
		//调用userMapper的方法
		annotateUserMapper.updateUser(user);
	}

}
