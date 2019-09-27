package com.kosoeo.dto;

import java.sql.Timestamp;

public class MemberDTO {
	int no;
	String email;
	String password;
	String name;
	String nickName;
	Timestamp joinDate;
	
	
	public MemberDTO() {
	}


	public MemberDTO(int no, String email, String password, String name, String nickName, Timestamp joinDate) {
		this.no = no;
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickName = nickName;
		this.joinDate = joinDate;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public Timestamp getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}
	
	
	
	
}
