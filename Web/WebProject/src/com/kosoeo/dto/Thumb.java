package com.kosoeo.dto;

public class Thumb {

	private int up;
	private int down;
	private int upCount;
	private int downCount;
	
	
	public Thumb() {}
	
	public Thumb(int up, int down, int upCount, int downCount) {
		this.up = up;
		this.down = down;
		this.upCount = upCount;
		this.downCount = downCount;
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
	public int getUpCount() {
		return upCount;
	}
	public void setUpCount(int upCount) {
		this.upCount = upCount;
	}
	public int getDownCount() {
		return downCount;
	}
	public void setDownCount(int downCount) {
		this.downCount = downCount;
	}
	
	
	
	
}
