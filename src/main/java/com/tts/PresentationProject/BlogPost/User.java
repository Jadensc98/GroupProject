package com.tts.PresentationProject.BlogPost;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String	email;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateDttm; 
	private	String password;
public User() {
	
}
public User(String firstName, String lastName,String email,String password,String userName, Date updateDttm) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.userName = userName;
	this.password = password;
	this.updateDttm = updateDttm;
	
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
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Date getUpdateDttm() {
	return updateDttm;
}
public void setUpdateDttm(Date updateDttm) {
	this.updateDttm = updateDttm;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
			+ ", email=" + email + ", updateDttm=" + updateDttm + ", password=" + password + "]";
}

}
