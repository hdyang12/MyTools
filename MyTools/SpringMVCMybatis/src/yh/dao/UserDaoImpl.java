package yh.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import yh.model.User;
@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	
	@Autowired 
	private SqlSessionTemplate sqlSession;
	
	public User findUserById(int id) throws Exception {
		User user = sqlSession.selectOne("test.findUserById",id);
		return user;
	}

	public void insertUser(User user) throws Exception {
		sqlSession.insert("test.insertUser", user);
	}
	
	public void deleteUser(int id) throws Exception {
		sqlSession.delete("test.deleteUser",id);
		
	}
}
