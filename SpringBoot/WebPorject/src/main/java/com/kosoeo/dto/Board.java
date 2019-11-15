package com.kosoeo.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
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
	private int thumbUpCount;
	
}
