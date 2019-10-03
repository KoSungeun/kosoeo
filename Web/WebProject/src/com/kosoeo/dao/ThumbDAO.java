package com.kosoeo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.kosoeo.dto.Thumb;



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
	
	
	public void thumbUpDown(int memberNo, int boardNo, String upDown) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int thumb = -1;
		String query = "select "+ upDown + " from thumb where memberNo = ? and boardNo = ?";

		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				thumb = rs.getInt(1);
			} else {
				pstmt.close();
				query = "insert into thumb (no, memberNo, boardNo, " + upDown + ") values (thumb_seq.nextval, ?, ?, 1)";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, memberNo);
				pstmt.setInt(2, boardNo);
			}		
			
			if(thumb == 0) {
				pstmt.close();
				query = "update thumb set " + upDown + " = 1 where memberNo = ? and boardNo = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, memberNo);
				pstmt.setInt(2, boardNo);
				
			} else if (thumb == 1) {
				pstmt.close();
				query = "update thumb set " + upDown + " = 0 where memberNo = ? and boardNo = ?";
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
	
	
	public Thumb upDownCount(int boardNo, int memberNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Thumb thumb = null;
		
		String query = "select up, down, (select count(*) from thumb where boardno = ? and up = 1) upCount, "
				+ " (select count(*) from thumb where boardNo = ? and down = 1) downCount from thumb where memberNo = ?";

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, boardNo);
			pstmt.setInt(3, memberNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				thumb = new Thumb();
				thumb.setUp(rs.getInt("up"));
				thumb.setDown(rs.getInt("down"));
				thumb.setUpCount(rs.getInt("upCount"));
				thumb.setDownCount(rs.getInt("downCount"));
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
		
		return thumb;
	}
	
	
	
}
