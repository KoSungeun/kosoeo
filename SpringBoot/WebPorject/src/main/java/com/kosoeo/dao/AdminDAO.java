package com.kosoeo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminDAO {
	
	
	public boolean adminCheck(int memberNo) {
		boolean isAdmin = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from admin where memberNo = ?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			set = pstmt.executeQuery();
			if(set.next()) {
				isAdmin = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return isAdmin;
	}
	
	private Connection getConnection() {
		Context context = null;
		DataSource dataSource = null;
		Connection con = null;
		
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			con = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
