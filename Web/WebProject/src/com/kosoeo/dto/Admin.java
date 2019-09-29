package com.kosoeo.dto;

public class Admin {
	private int	no;
	private Member member;
	
	
	public Admin() {
		super();
	}


	public Admin(int no, Member member) {
		super();
		this.no = no;
		this.member = member;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}
	
	

	
}
