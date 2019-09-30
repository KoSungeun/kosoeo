package com.kosoeo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.kosoeo.dto.Comment;
import com.kosoeo.dto.Member;


public class CommentDAO {
	
	private static CommentDAO instance = new CommentDAO();
	DataSource dataSource = null;
	
	public CommentDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static CommentDAO getInstance() {
		return instance;
	}
	
	public void write(int memberNo, int boardNo, String content) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String query = "insert into comments " + 
						   " (no, memberNo, boardNo, content) " +
						   " values " + 
						   " (comments_seq.nextval, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			pstmt.setString(3, content);	
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
	
	public ArrayList<Comment> list(int boardNo) {
			
		ArrayList<Comment> comments = new ArrayList<Comment>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String query = "select * from comments join member on comments.memberNo = member.no where boardNo = ?";

		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Comment comment = new Comment();
				Member member = new Member();
				member.setNo(rs.getInt("memberNo"));
				member.setEmail(rs.getString("email"));
				member.setName(rs.getString("name"));
				member.setNickName(rs.getString("nickName"));
				comment.setNo(rs.getInt("no"));
				comment.setMember(member);
				comment.setBoardNo(rs.getInt("boardNo"));
				comment.setContent(rs.getString("content"));
				comment.setCommentDate(rs.getTimestamp("commentDate"));
				comments.add(comment);
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
		return comments;
	}
	
	public void delete(int commentNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			
			String query = "delete from comments where no = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, commentNo);
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
	
}
