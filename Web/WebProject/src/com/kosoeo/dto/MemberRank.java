package com.kosoeo.dto;

public class MemberRank {
	private String email;
	private String nickName;
	private int posts;
	public MemberRank(String email, String nickName, int posts) {
		super();
		this.email = email;
		this.nickName = nickName;
		this.posts = posts;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getPosts() {
		return posts;
	}
	public void setPosts(int posts) {
		this.posts = posts;
	}
	
	
	
}
