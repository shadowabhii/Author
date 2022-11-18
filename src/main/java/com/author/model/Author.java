package com.author.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer aId;
	private String firstName;
	private String lastName;
	private int age;
	private String userName;
	private String password;
	private String emailId;
	public Integer getaId() {
		return aId;
	}
	public void setaId(Integer aId) {
		this.aId = aId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Author(Integer aId, String firstName, String lastName, int age, String userName, String password,
			String emailId) {
		super();
		this.aId = aId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.userName = userName;
		this.password = password;
		this.emailId = emailId;
	}
	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Author [aId=" + aId + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", userName=" + userName + ", password=" + password + ", emailId=" + emailId + "]";
	}
	
	

}
