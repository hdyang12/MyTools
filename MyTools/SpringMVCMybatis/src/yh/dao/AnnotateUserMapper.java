package yh.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import yh.model.User;

/**
 * @DeleteProvider(type = TestSqlProvider.class, method = "deleteSql") ：用法和含义@SelectProvider一样，只不过是用来删除数据而用的。
 * @InsertProvider(type = TestSqlProvider.class, method = "insertSql") ：用法和含义@SelectProvider一样，只不过是用来插入数据库而用的。
 * @UpdateProvider(type = TestSqlProvider.class, method = "updateSql") ：用法和含义@SelectProvider一样，只不过是用来更新数据库而用的。
 * @author YH
 *
 */
public interface AnnotateUserMapper {
	
	//使用注解查询，结果映射不同的名字可以使用results，相同的名字会自动映射，驼峰式的开启注解也会映射
	@Select({"select * from user where id = #{user_id}"})
	@Results({
	@Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
	@Result(column = "sex", property = "sex", jdbcType = JdbcType.VARCHAR)
	})
	public User findUserById(int id)throws Exception;
	
	//这里的 @Param 会自动映射到map中(例如: @Param(“Id”) int Id:map的key为Id,value为参数Id的值)
	@SelectProvider(type = UserProvider.class, method = "findUser")
	//public User findUser(Map<String, Object> map)throws Exception;
	public List<User> findUser(@Param("id") int id)throws Exception;
	
	//这个user可以直接被使用,id 就是 user.id
	@SelectProvider(type = UserProvider.class, method = "findUserByUser")
	public List<User> findUserByParam(@Param("user") User user)throws Exception;
	
	//这个user可以直接被使用,id 就是 user.id
	@SelectProvider(type = UserProvider.class, method = "findUserByUser1")
	public List<User> findUserByParam1(User user)throws Exception;
	
	//入参是map,map中有id属性,这个id可以直接被使用
	@SelectProvider(type = UserProvider.class, method = "findUserByUser2")
	public List<User> findUserByParam2(Map<String, Object> map)throws Exception;
	
	@Insert({
		"insert into user (id,username,birthday,sex,address)value(#{id},#{username},#{birthday},#{sex},#{address})"
	})
	public void insertUser(User user)throws Exception;
	
	@Delete({
		"delete from user where id=#{id}"
	})
	public void deleteUser(@Param("id") int id) throws Exception;
	
	
	@Update({ "update user",
		"set username=#{username},",
		"birthday=#{birthday,jdbcType=TIMESTAMP},",
		"address=#{address,jdbcType=VARCHAR},",
		"sex=#{sex}",
		"where id = #{id}" })
	public void updateUser(User user)throws Exception;
}
