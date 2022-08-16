package com.example.project02_iot.customer;

import java.io.Serializable;

public class CustomerVO implements Serializable {
	
	private int id;
	private String name, gender, email, phone;
	public CustomerVO(){}
	public CustomerVO(int id, String email, String phone, String gender) {
		this.id = id;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
