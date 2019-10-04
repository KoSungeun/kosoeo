package com.kosoeo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.kosoeo.dto.Board;
import com.kosoeo.dto.BoardPage;
import com.kosoeo.dto.Member;




public class BoardDAO {

	private static BoardDAO instance = new BoardDAO();
	DataSource dataSource = null;
	
	int listCount = 10;		// 한 페이지당 보여줄 게시물의 갯수
	int pageCount = 10;		// 하단에 보여줄 페이지 리스트의 갯수
	
	public BoardDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	public int write(int memberNo, String title, String content, int category) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int seq = 0;
		try {
			con = dataSource.getConnection();
			
			String query = "select last_number from user_sequences where sequence_name = 'BOARD_SEQ'";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				seq = rs.getInt("last_number");
			}
			pstmt.close();
			rs.close();
			query = "insert into board " + 
						   " (no, category, memberNo, title, content, bGroup, step, indent) " +
						   " values " + 
						   " (board_seq.nextval, ?, ?, ?, ?, board_seq.currval, 0, 0 )";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, category);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, title);	
			pstmt.setString(4, content);
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
		return seq;
	}
	
	public ArrayList<Board> list(int curPage, int category, String type, String word) {
		
		ArrayList<Board> dtos = new ArrayList<Board>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
	

		String query = null;
		if(type != null) {

			query = "select * " +
					   "	from ( " +
					   "		select rownum num, A.* " +
					   "			from ( " +
					   "				select * " +
					   "				from board join member " + 
					   "  					  on board.memberno = member.no " +
					   "						where category = ? and " + type + " like " + word +
					   "			order by bGroup desc, Step asc ) A " + 
					   "		where rownum <= ? ) B " +
					   " where B.num >= ? ";
		} else {
			query = "select * " +
					   "	from ( " +
					   "		select rownum num, A.* " +
					   "			from ( " +
					   "				select * " +
					   "				from board join member " + 
					   "  					  on board.memberno = member.no " +
					   "                         where category = ? " +
					   "			order by bGroup desc, Step asc ) A " + 
					   "		where rownum <= ? ) B " +
					   " where B.num >= ? ";
		}
		
		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, category);
			pstmt.setInt(2, nEnd);
			pstmt.setInt(3, nStart);
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				Board bdto = new Board();
				Member mdto = new Member();
				mdto.setEmail(resultSet.getString("email"));
				mdto.setName(resultSet.getString("name"));
				mdto.setNickName(resultSet.getString("nickName"));
				bdto.setNo(resultSet.getInt("no"));
				bdto.setCategory(resultSet.getInt("category"));
				bdto.setMember(mdto);
				bdto.setTitle(resultSet.getString("title"));
				bdto.setContent(resultSet.getString("content"));
				bdto.setPostdate(resultSet.getTimestamp("postdate"));
				bdto.setHit(resultSet.getInt("hit"));
				bdto.setBgroup(resultSet.getInt("bgroup"));
				bdto.setStep(resultSet.getInt("step"));
				bdto.setIndent(resultSet.getInt("indent"));
				dtos.add(bdto);
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
		return dtos;
		
	}
	
	public Board contentView(int no) {
		
		
		Board bdto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			String query = "select * from board join member on board.memberno = member.no where board.no = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				bdto = new Board();
				Member mdto = new Member();
				mdto.setNo(resultSet.getInt("memberNo"));
				mdto.setEmail(resultSet.getString("email"));
				mdto.setName(resultSet.getString("name"));
				mdto.setNickName(resultSet.getString("nickName"));
				bdto.setNo(resultSet.getInt("no"));
				bdto.setCategory(resultSet.getInt("category"));
				bdto.setMember(mdto);
				bdto.setTitle(resultSet.getString("title"));
				bdto.setContent(resultSet.getString("content"));
				bdto.setPostdate(resultSet.getTimestamp("postdate"));
				bdto.setHit(resultSet.getInt("hit"));
				bdto.setBgroup(resultSet.getInt("bgroup"));
				bdto.setStep(resultSet.getInt("step"));
				bdto.setIndent(resultSet.getInt("indent"));
				
				upHit(no);
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
		return bdto;
		
	}
	
	public void update(Board board) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dataSource.getConnection();
			String query = "update board " + 
						   "   set title = ?, " +
						   "       content = ? " +
						   " where no = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, board.getTitle());	
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getNo());
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
	
	public void upHit(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update board " + 
						   "   set hit =  hit + 1 " +
						   " where no = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
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
	
	public void delete(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "delete from files where boardNo = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			pstmt.close();
			
			query = "delete from comments where boardNo = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			pstmt.close();
			
			query = "delete from thumb where boardNo = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			pstmt.close();
			
			query = "delete from board where no = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
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
	
	public Board replyView(String str) {
		Board dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			String query = "select * from board join member on board.memberno = member.no where no = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				Board bdto = new Board();
				Member mdto = new Member();
				mdto.setEmail(resultSet.getString("email"));
				mdto.setName(resultSet.getString("name"));
				mdto.setNickName(resultSet.getString("nickName"));
				bdto.setNo(resultSet.getInt("no"));
				bdto.setCategory(resultSet.getInt("category"));
				bdto.setMember(mdto);
				bdto.setTitle(resultSet.getString("title"));
				bdto.setContent(resultSet.getString("content"));
				bdto.setPostdate(resultSet.getTimestamp("postdate"));
				bdto.setHit(resultSet.getInt("hit"));
				bdto.setBgroup(resultSet.getInt("bgroup"));
				bdto.setStep(resultSet.getInt("step"));
				bdto.setIndent(resultSet.getInt("indent"));

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
		return dto;
		
	}

	public void reply(Board dto) {
		
		replyShape(dto.getBgroup(), dto.getStep());
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into board " + 
						   " (no, category, memberNo, title, content, bgroup, step, indent) " +
						   " values " + 
						   " (board_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, dto.getCategory());
			pstmt.setInt(2, dto.getMember().getNo());
			pstmt.setString(3, dto.getTitle());	
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getBgroup());
			pstmt.setInt(6, dto.getStep() + 1);
			pstmt.setInt(7, dto.getIndent() + 1);
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
	
	public void replyShape(int bgroup, int step) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update board " + 
						   "   set step = step + 1 " +
						   " where bgroup = ? and step > ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bgroup);
			pstmt.setInt(2, step);
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
	
	public BoardPage articlePage(int curPage, int category, String type, String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int listCount = 10;		// 한 페이지당 보여줄 게시물의 갯수
		int pageCount = 10;		// 하단에 보여줄 페이지 리스트의 갯수
		
		// 총 게시물의 갯수
		int totalCount = 0;
		
		String query = null;
		if(type != null) {
			query = "select count(*) as total from board join member on board.memberno = member.no where category = ?  and " + type + " like " + word;
		} else {
			query = "select count(*) as total from board join member on board.memberno = member.no where category = ?";
		}
		
		
		try {
			con = dataSource.getConnection();
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, category);
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
		
		BoardPage pinfo = new BoardPage();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}


}
