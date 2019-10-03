package com.kosoeo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.kosoeo.dto.File;


public class FileDAO {
	
	private static FileDAO instance = new FileDAO();
	DataSource dataSource = null;
	
	public FileDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static FileDAO getInstance() {
		return instance;
	}
	
	public void upload(File file) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String query = "insert into files " + 
						   " (no, boardNo, path, submitName, realName, contentType, fileSize) " +
						   " values " + 
						   " (files_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, file.getBoardNo());
			pstmt.setString(2, file.getPath());
			pstmt.setString(3, file.getSubmitName());
			pstmt.setString(4, file.getRealName());
			pstmt.setString(5, file.getContentType());
			pstmt.setLong(6, file.getFileSize());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public List<File> list(int boardNo) {
		
		List<File> files = new ArrayList<File>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String query = "select * from files where boardNo = ?";

		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				File file = new File();
				file.setNo(rs.getInt("no"));
				file.setBoardNo(rs.getInt("boardNo"));
				file.setPath(rs.getString("path"));
				file.setSubmitName(rs.getString("submitName"));
				file.setRealName(rs.getString("realName"));
				file.setContentType(rs.getString("contentType"));
				file.setFileSize(rs.getLong("fileSize"));
				
				files.add(file);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!= null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return files;
		
	}
	
}
