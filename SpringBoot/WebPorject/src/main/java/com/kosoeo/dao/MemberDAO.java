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

import com.kosoeo.dto.Member;
import com.kosoeo.dto.MemberRank;
import com.kosoeo.dto.Page;

public class MemberDAO {
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int	MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	public static final int MEMBER_LOGIN_BLOCK = -2;
	
	int listCount = 10;		// 한 페이지당 보여줄 게시물의 갯수
	int pageCount = 10;		// 하단에 보여줄 페이지 리스트의 갯수
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public int join(Member dto) {
		int ri = 0;
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
	
	public Member getMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select no, email, name, nickname, joindate from member where email = ?";
		Member dto = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dto = new Member();
				AdminDAO adao = new AdminDAO();
				int no = set.getInt("no");
				dto.setNo(no);
				dto.setEmail(set.getString("email"));
				dto.setName(set.getString("name"));
				dto.setNickName(set.getString("nickname"));
				dto.setJoinDate(set.getTimestamp("joindate"));
				dto.setAdmin(adao.adminCheck(no));
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
	
	public List<Member> list(int curPage, String type, String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<>();
		
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		String query = null;
		if(type != null) {
			
			query = "select * " +
					   "	from ( " +
					   "		select rownum num, A.* " +
					   "			from ( " +
					   "				select member.no memberno, email, password, name, nickname, joindate, block.no block, admin.no admin " +
					   "				from member left join block on member.no = block.memberno left join admin on member.no = admin.memberno " +
					   "				where " + type + " like " + word + 
					   "				order by joindate desc " + 
					   ") A " + 
					   "		where rownum <= ? ) B " +
					   " where B.num >= ? ";
			
		} else {
			
			query = "select * " +
					   "	from ( " +
					   "		select rownum num, A.* " +
					   "			from ( " +
					   "				select member.no memberno, email, password, name, nickname, joindate, block.no block, admin.no admin " +
					   "				from member left join block on member.no = block.memberno left join admin on member.no = admin.memberno " +
					   "				order by joindate desc " + 
					   ") A " + 
					   "		where rownum <= ? ) B " +
					   " where B.num >= ? ";
			
			
		}
		
		Member dto = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new Member();
				int no = rs.getInt("memberno");
				dto.setNo(no);
				dto.setEmail(rs.getString("email"));
				dto.setName(rs.getString("name"));
				dto.setNickName(rs.getString("nickname"));
				dto.setJoinDate(rs.getTimestamp("joindate"));
				if(rs.getInt("block") > 0) {
					dto.setBlock(true);
				} else {
					dto.setBlock(false);
				}
				if(rs.getInt("admin") > 0) {
					dto.setAdmin(true);
				} else {
					dto.setAdmin(false);
				}
				
				list.add(dto);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	public int modify (Member dto) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update member set email = ?, password = ?, name = ?, nickname = ? where no = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getEmail());	
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getNickName());
			pstmt.setInt(5, dto.getNo());
			
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
	
	public String block (int memberNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String msg = "";
		
		
		try {
			
			String query = "select * from block where memberNo = ?";
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				query = "delete block where memberNo = ?";
				msg = "정지를 해제하였습니다.";
			} else {
				query = "insert into block (no, memberNo) values (block_seq.nextval, ?)";
				msg = "정지시켰습니다.";
			}
			pstmt.close();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memberNo);
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
		return msg;
	}
	
	public boolean blockCheck(int memberNo) {
		boolean isBlock = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from block where memberNo = ?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			set = pstmt.executeQuery();
			if(set.next()) {
				isBlock = true;
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
		return isBlock;
	}
	
	public int withdraw (int no) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete member where no = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
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
	
	
	public Page articlePage(int curPage, String type, String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		

		// 총 게시물의 갯수
		int totalCount = 0;
		
		String query = null;
		if(type != null) {
			query = "select count(*) as total from member where " + type + " like " + word;
		} else {
			query = "select count(*) as total from member";
		}
		
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				totalCount = resultSet.getInt("total");
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		// 총 페이지 수
		int totalPage = totalCount / listCount;
		if (totalCount % listCount > 0)
			totalPage++;
		
		// 현재 페이지
		int myCurPage = curPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage < 1)
			myCurPage = 1;
		
		// 시작 페이지
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		
		// 끝 페이지
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage)
			endPage = totalPage;
		
		if(totalPage == 0) {
			totalPage = 1;
		}
		
		Page pinfo = new Page();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
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
	

	public List<MemberRank> boardRank() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberRank> list = new ArrayList<>();
		MemberRank rank = null;
		

		String query = 	" select a.* " +
						" from (select  m.email, m.nickname,  count(*) posts " + 
						" from board b join member m " +
						" on b.memberno = m.no " + 
						" where b.postdate > (sysdate-7) " + 
						" group by  m.email, m.nickname " + 
						" order by posts desc) a " + 
						" where rownum <= 5 ";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String email = rs.getString("email");
				String nickName = rs.getString("nickName");
				int posts = rs.getInt("posts");
				rank = new MemberRank();
				rank.setEmail(email);
				rank.setNickName(nickName);
				rank.setPosts(posts);
				list.add(rank);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	public List<MemberRank> commantRank() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberRank> list = new ArrayList<>();
		MemberRank rank = null;
		

		String query = 	" select a.* " +
						" from (select m.email, m.nickname,  count(*) posts " + 
						" from comments c join member m " + 
						" on c.memberno = m.no " + 
						" where c.commentdate > (sysdate-7) " + 
						" group by m.email, m.nickname " +
						" order by posts desc) a " +
						" where rownum <= 5";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String email = rs.getString("email");
				String nickName = rs.getString("nickName");
				int posts = rs.getInt("posts");
				rank = new MemberRank();
				rank.setEmail(email);
				rank.setNickName(nickName);
				rank.setPosts(posts);
				list.add(rank);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
}
