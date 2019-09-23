package com.kosoeo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.kosoeo.dto.MemberDTO;

public class MemberDAO {
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int	MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public int join(MemberDTO dto) {
		int ri = 0;
		System.out.println(dto.getEmail());
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into member (no, email, password, name, nickname) values (member_seq.nextval, ?, ?, ?, ?)";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getPassword());	
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getNickName());

			pstmt.executeUpdate();
			ri = MemberDAO.MEMBER_JOIN_SUCCESS;
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
		return ri;
		
	}
	
	public int emailCheck(String email) {
		int ri = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select email from member where email = ?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			set = pstmt.executeQuery();
			if(set.next()) {
				ri = MemberDAO.MEMBER_EXISTENT;
			} else {
				ri = MemberDAO.MEMBER_NONEXISTENT;
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

		return ri;
	}
	
	public int userCheck(String email, String password, String type) {
		int ri = MEMBER_LOGIN_IS_NOT;
		String DBPassword;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select password from member where email = ?";
		
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			set = pstmt.executeQuery();
			
			
			if(set.next()) {
				DBPassword = set.getString("password");
				if(type.equals("normal")) {
					if(DBPassword != null) {
						if(DBPassword.equals(password)) {
							System.out.println("login ok");
							ri = MemberDAO.MEMBER_LOGIN_SUCCESS;	// 로그인 OK
						} else {
							System.out.println("login fail");
							ri = MemberDAO.MEMBER_LOGIN_PW_NO_GOOD;	// 비밀번호 X
						}															
					} else {
						System.out.println("login fail");
						ri = MemberDAO.MEMBER_LOGIN_PW_NO_GOOD;	// 비밀번호 X
					}
				} else {
					ri = MemberDAO.MEMBER_LOGIN_SUCCESS;
				}
			} else {
				System.out.println("login fail");
				ri = MemberDAO.MEMBER_LOGIN_IS_NOT;			// 아이디 X
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
		return ri;
	}
	
	public MemberDTO getMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select no, email, name, nickname, joindate from member where email = ?";
		MemberDTO dto = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dto = new MemberDTO();
				dto.setNo(set.getInt("no"));
				dto.setEmail(set.getString("email"));
				dto.setName(set.getString("name"));
				dto.setNickName(set.getString("nickname"));
				dto.setJoinDate(set.getTimestamp("joindate"));
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
		return dto;
	}
	
	public int updateMember(MemberDTO dto) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update members set email = ?, password = ?, name = ?, nickname = ? where email= ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getEmail());	
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getNickName());
			pstmt.setString(5, dto.getEmail());
			ri = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return ri;
		
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
