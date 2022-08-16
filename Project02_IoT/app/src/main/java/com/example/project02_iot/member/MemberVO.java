package com.example.project02_iot.member;

public class MemberVO {
	
	private String userid, name, admin, userpw, gender, email, birth, phone, address, post, social;

	public MemberVO(String userid, String name, String admin, String userpw, String gender, String email, String birth,
			String phone, String address, String post, String social) {
		super();
		this.userid = userid;
		this.name = name;
		this.admin = admin;
		this.userpw = userpw;
		this.gender = gender;
		this.email = email;
		this.birth = birth;
		this.phone = phone;
		this.address = address;
		this.post = post;
		this.social = social;
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
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

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getSocial() {
		return social;
	}

	public void setSocial(String social) {
		this.social = social;
	}
	
	
}
