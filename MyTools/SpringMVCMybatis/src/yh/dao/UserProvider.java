package yh.dao;

import java.util.Map;

import yh.model.User;

public class UserProvider {
	
	public String findUser(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		sb.append("select * from user where id < #{id}");
		return sb.toString();
	}
	
	public String findUserByUser(){
		StringBuffer sb = new StringBuffer();
		sb.append("select * from user where id < #{user.id}");
		return sb.toString();
	}
	
	public String findUserByUser1(User user){
		StringBuffer sb = new StringBuffer();
		sb.append("select * from user where id < #{id}");
		return sb.toString();
	}
	
	public String findUserByUser2(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		sb.append("select * from user where id < #{id}");
		return sb.toString();
	}

}
