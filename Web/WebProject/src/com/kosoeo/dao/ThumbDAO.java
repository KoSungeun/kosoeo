package com.kosoeo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class ThumbDAO {
	
	private static ThumbDAO instance = new ThumbDAO();
	DataSource dataSource = null;
	
	public ThumbDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ThumbDAO getInstance() {
		return instance;
	}
	
	
	public void thumbUp(int memberNo, int boardNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int thumb = -1;
	
		String query = "select thumbUp from thumb where memberNo = ? and boardNo = ?";

		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			rs = pstmt.executeQuery();
			pstmt.close();
			if (rs.next()) {
				thumb = rs.getInt(1);
			} else {
				query = "insert into thumb (no, memberNo, boardNo, up) values (thumb_seq.nextval, ?, ?, 1)";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, memberNo);
				pstmt.setInt(2, boardNo);
			}		
			
			if(thumb == 0) {
				query = "update thumb set up = 1 where memberNo = ? and boardNo = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, memberNo);
				pstmt.setInt(2, boardNo);
				
			} else if (thumb == 1) {
				query = "update thumb set up = 0 where memberNo = ? and boardNo = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, memberNo);
				pstmt.setInt(2, boardNo);
			}
			pstmt.executeUpdate();
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
		
	}
	
	public void thumbDown(int memberNo, int boardNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int thumb = -1;
	
		String query = "select thumbUp from thumb where memberNo = ? and boardNo = ?";

		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			rs = pstmt.executeQuery();
			pstmt.close();
			if (rs.next()) {
				thumb = rs.getInt(1);
			} else {
				query = "insert into thumb (no, memberNo, boardNo, down) values (thumb_seq.nextval, ?, ?, 1)";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, memberNo);
				pstmt.setInt(2, boardNo);
			}		
			
			if(thumb == 0) {
				query = "update thumb set down = 0 where memberNo = ? and boardNo = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, memberNo);
				pstmt.setInt(2, boardNo);
				
			} else if (thumb == 1) {
				query = "update thumb set down = 1 where memberNo = ? and boardNo = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, memberNo);
				pstmt.setInt(2, boardNo);
			}
			pstmt.executeUpdate();
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
		
	}
	
	public void boardThumb(int boardNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from thumb where boardNo = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
		
	}
	
}
