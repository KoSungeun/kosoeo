package com.kosoeo.dto;

import java.sql.Timestamp;

public class Board {
	private int no;
	private int category;
	private Member member;
	private String title;
	private String content;
	private int blike;
	private Timestamp postdate;
	private int hit;
	private int bgroup;
	private int step;
	private int indent;
	
	
	public Board() {

	}
	
	



	public Board(int no, int category, Member member, String title, String content, int blike, Timestamp postdate,
			int hit, int bgroup, int step, int indent) {
		super();
		this.no = no;
		this.category = category;
		this.member = member;
		this.title = title;
		this.content = content;
		this.blike = blike;
		this.postdate = postdate;
		this.hit = hit;
		this.bgroup = bgroup;
		this.step = step;
		this.indent = indent;
	}








	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBlike() {
		return blike;
	}
	public void setBlike(int blike) {
		this.blike = blike;
	}
	public Timestamp getPostdate() {
		return postdate;
	}
	public void setPostdate(Timestamp postdate) {
		this.postdate = postdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getBgroup() {
		return bgroup;
	}
	public void setBgroup(int bgroup) {
		this.bgroup = bgroup;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}
	
	
}
