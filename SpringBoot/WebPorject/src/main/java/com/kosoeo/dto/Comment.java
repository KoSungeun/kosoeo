package com.kosoeo.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Comment {
	private int no;
	private Member member;
	private int boardNo;
	private String content;
	private Timestamp commentDate;
}
