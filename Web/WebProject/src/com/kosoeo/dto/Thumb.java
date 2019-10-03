package com.kosoeo.dto;

public class Thumb {
	private int no;
	private int memberNo;
	private int boardNo;
	private int up;
	private int down;
	
	public Thumb() {}
	public Thumb(int no, int memberNo, int boardNo, int up, int down) {
		super();
		this.no = no;
		this.memberNo = memberNo;
		this.boardNo = boardNo;
		this.up = up;
		this.down = down;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getUp() {
		return up;
	}
	public void setUp(int up) {
		this.up = up;
	}
	public int getDown() {
		return down;
	}
	public void setDown(int down) {
		this.down = down;
	}
	
	
}
