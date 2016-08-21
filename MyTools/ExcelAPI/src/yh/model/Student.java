package yh.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Student {

	private String name;
	private String sex;
	private int age;
	private Date date;
	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	



	public Student(String name, String sex, int age, Date date) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.date = date;
	}







	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
