package yh.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String sex;
	private Date birthday;
	private String address;
	//用户创建的订单列表
	private List<Orders> ordersList;
	
	public List<Orders> getOrdersList() {
		return ordersList;
	}
	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String toString() {
		return "User [address=" + address + ", birthday=" + birthday + ", id="
				+ id + ", sex=" + sex + ", username=" + username + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
