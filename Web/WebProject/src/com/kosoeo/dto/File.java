package com.kosoeo.dto;

import java.sql.Timestamp;

public class File {
	private int no;
	private int boardNo;
	private String path;
	private String submitName;
	private String realName;
	private String contentType;
	private long fileSize;
	private Timestamp uploadDate;
	
	public File() {}
	public File(int no, int boardNo, String path, String submitName, String realName, String contentType, long fileSize,
			Timestamp uploadDate) {
		super();
		this.no = no;
		this.boardNo = boardNo;
		this.path = path;
		this.submitName = submitName;
		this.realName = realName;
		this.contentType = contentType;
		this.fileSize = fileSize;
		this.uploadDate = uploadDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSubmitName() {
		return submitName;
	}

	public void setSubmitName(String submitName) {
		this.submitName = submitName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public Timestamp getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	
	
}
