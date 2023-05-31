package com.nagarro.exittest_spring.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@Document(collection = "users")
public class User {
    @Id
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String userType;
	public User(String username, String email, String firstname, String lastName, String password, String userType) {
		
		this.username = username;
		this.email = email;
		this.firstName = firstname;
		this.lastName = lastName;
		this.password = password;
		this.userType = userType;
	}
	public User() {
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmailId(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
  
    
    
}