package yh.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import yh.dao.UserDao;
import yh.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:config/spring-mvc.xml", "classpath:config/db.xml"})
public class UserDaoImplTest {
	
	@Resource(name="userDaoImpl")
	private UserDao userDao;

	@Test
	public void testFindUserById() throws Exception{
		//调用UserDao的方法
		User user = userDao.findUserById(1);
		System.out.println(user);
	}
	
	@Test
	public void testInsertUser() throws Exception{
		User user = new User();
		user.setId(4);
		user.setBirthday(new Date());
		user.setUsername("吴老狗");
		userDao.insertUser(user);
	}
	
	@Test
	public void testDeleteUser() throws Exception{
		userDao.deleteUser(4);
	}

}
