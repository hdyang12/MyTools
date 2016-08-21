package yh.model;

import java.util.Date;
import java.util.List;

public class Orders {
	private Integer id;
	private Integer userId;
	private String number;
	private Date createtime;
	private String notel;
	private User user;
	private List<OrderDetail> orderDetails;

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getNotel() {
		return notel;
	}
	public void setNotel(String notel) {
		this.notel = notel;
	}
	@Override
	public String toString() {
		return "Orders [createtime=" + createtime + ", id=" + id + ", notel="
				+ notel + ", number=" + number + ", orderDetails="
				+ orderDetails + ", user=" + user + ", userId=" + userId + "]";
	}

	
}
