package com.kosoeo.dto;

import java.sql.Timestamp;

import lombok.Data;


@Data
public class Member {
	int no;
	String email;
	String password;
	String name;
	String nickName;
	Timestamp joinDate;
	boolean isAdmin;
	boolean isBlock;
}
