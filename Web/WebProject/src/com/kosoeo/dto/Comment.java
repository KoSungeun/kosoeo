package com.kosoeo.dto;

import java.sql.Timestamp;

public class Comment {
	private int no;
	private Member member;
	private int boardNo;
	private String content;
	private Timestamp commentDate;
	
	public Comment() {	}

	public Comment(int no, Member member, int boardNo, String content, Timestamp commentDate) {
		this.no = no;
		this.member = member;
		this.boardNo = boardNo;
		this.content = content;
		this.commentDate = commentDate;
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

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}

	
	
}
