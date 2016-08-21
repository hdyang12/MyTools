package yh.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import yh.dao.UserMapper;
import yh.model.User;
import yh.model.UserCustom;
import yh.model.UserQueryVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:config/spring-mvc.xml", "classpath:config/db.xml"})
public class UserMapperTest {
	
	@Autowired 
	private SqlSessionTemplate sqlSession;
	
//	@Before
//	public void setUp() throws Exception {
//		ApplicationContext ac = new ClassPathXmlApplicationContext("config/db.xml");
//
//		sqlSession =  (SqlSessionTemplate)ac.getBean("sqlSession");
//	}

	@Test
	public void testFindUserById() throws Exception{
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用userMapper的方法
		User user = userMapper.findUserById(1);
		System.out.println(user);
	}
	
	@Test
	public void testFindUserList() throws Exception{
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//创建包装对象,设置查询条件
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		userCustom.setId(1);
		userCustom.setUsername("lisi");
		userQueryVo.setUserCustom(userCustom);
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		ids.add(3);
		userQueryVo.setIds(ids);
		//调用userMapper的方法
		List<UserCustom> list = userMapper.findUserList(userQueryVo);
		System.out.println(list);
	}
	
	@Test
	public void testFindUserCount() throws Exception{
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//创建包装对象,设置查询条件
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		userCustom.setId(1);
		userCustom.setUsername("lisi");
		userQueryVo.setUserCustom(userCustom);
		//调用userMapper的方法
		System.out.println(userMapper.findUserCount(userQueryVo));
	}
	
	@Test
	public void testFindUserByIdResultMap() throws Exception{
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		//调用userMapper的方法
		System.out.println(userMapper.findUserByIdResultMap(1));
	}
	
	@Test
	public void testFindUserByName() throws Exception{
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用userMapper的方法
		System.out.println(userMapper.findUserByName("王五"));
	}
	
	@Test
	public void testInsertUser() throws Exception{
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setId(3);
		user.setBirthday(new Date());
		user.setUsername("李四");
		//调用userMapper的方法
		userMapper.insertUser(user);
	}
	
	@Test
	public void testDeleteUser() throws Exception{
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用userMapper的方法
		userMapper.deleteUser(3);
	}
	
	@Test
	public void testUpdateUser() throws Exception{
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserById(1);
		user.setUsername("李易峰");
		//调用userMapper的方法
		userMapper.updateUser(user);
	}

}
