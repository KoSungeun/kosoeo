package com.kosoeo.dto;

import java.sql.Timestamp;

import lombok.Data;


@Data
public class File {
	private int no;
	private int boardNo;
	private String path;
	private String submitName;
	private String realName;
	private String contentType;
	private long fileSize;
	private Timestamp uploadDate;
}
