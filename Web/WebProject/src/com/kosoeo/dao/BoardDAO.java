package com.kosoeo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.kosoeo.dto.BoardDTO;
import com.kosoeo.dto.BoardPage;




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
	
	public void write(String name, String title, String content, int category) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into board " + 
						   " (no, category, name, title, content, bGroup, step, indent) " +
						   " values " + 
						   " (board_seq.nextval, ?, ?, ?, ?, board_seq.currval, 0, 0 )";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, category);
			pstmt.setString(2, name);
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
	}
	
	public ArrayList<BoardDTO> list(int curPage, int category) {
		
		ArrayList<BoardDTO> dtos = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
				
		
		try {
			con = dataSource.getConnection();
			String query = "select * " +
						   "	from ( " +
						   "		select rownum num, A.* " +
						   "			from ( " +
						   "				select * " +
						   "				from board where category = ? " +
						   "			order by bGroup desc, Step asc ) A " + 
						   "		where rownum <= ? ) B " +
						   " where B.num >= ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, category);
			pstmt.setInt(2, nEnd);
			pstmt.setInt(3, nStart);
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(resultSet.getInt("no"));
				dto.setCategory(resultSet.getInt("category"));
				dto.setName(resultSet.getString("name"));
				dto.setTitle(resultSet.getString("title"));
				dto.setContent(resultSet.getString("title"));
				dto.setPostdate(resultSet.getTimestamp("postdate"));
				dto.setHit(resultSet.getInt("hit"));
				dto.setBgroup(resultSet.getInt("bgroup"));
				dto.setStep(resultSet.getInt("step"));
				dto.setIndent(resultSet.getInt("indent"));

				dtos.add(dto);
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
	
	public BoardDTO contentView(String strID) {
		upHit(strID);
		
		BoardDTO dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			String query = "select * from board where no = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				dto = new BoardDTO();
				dto.setNo(resultSet.getInt("no"));
				dto.setCategory(resultSet.getInt("category"));
				dto.setName(resultSet.getString("name"));
				dto.setTitle(resultSet.getString("title"));
				dto.setContent(resultSet.getString("title"));
				dto.setPostdate(resultSet.getTimestamp("postdate"));
				dto.setHit(resultSet.getInt("hit"));
				dto.setBgroup(resultSet.getInt("bgroup"));
				dto.setStep(resultSet.getInt("step"));
				dto.setIndent(resultSet.getInt("indent"));
				
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
	
	public void modify(String no, String name, String title, String content) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update board " + 
						   "   set name = ?, " +
						   "       bTitle = ?, " +
						   "       bContent = ? " +
						   " where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, title);	
			pstmt.setString(3, content);
			pstmt.setString(4, no);
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
	
	public void upHit(String no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update board " + 
						   "   set hit =  hit + 1 " +
						   " where no = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, no);
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
	
	public void delete(String no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "delete from board where no = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, no);
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
	
	public BoardDTO replyView(String str) {
		BoardDTO dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			String query = "select * from board where no = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				dto = new BoardDTO();
				dto.setNo(resultSet.getInt("no"));
				dto.setCategory(resultSet.getInt("category"));
				dto.setName(resultSet.getString("name"));
				dto.setTitle(resultSet.getString("title"));
				dto.setContent(resultSet.getString("title"));
				dto.setPostdate(resultSet.getTimestamp("postdate"));
				dto.setHit(resultSet.getInt("hit"));
				dto.setBgroup(resultSet.getInt("bgroup"));
				dto.setStep(resultSet.getInt("step"));
				dto.setIndent(resultSet.getInt("indent"));

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

	public void reply(BoardDTO bto) {
		
		replyShape(bto.getBgroup(), bto.getStep());
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into board " + 
						   " (no, category, name, title, content, bgroup, step, indent) " +
						   " values " + 
						   " (board_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bto.getCategory());
			pstmt.setString(2, bto.getName());
			pstmt.setString(3, bto.getTitle());	
			pstmt.setString(4, bto.getContent());
			pstmt.setInt(5, bto.getBgroup());
			pstmt.setInt(6, bto.getStep() + 1);
			pstmt.setInt(7, bto.getIndent() + 1);
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
	
	public BoardPage articlePage(int curPage, int category) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int listCount = 10;		// 한 페이지당 보여줄 게시물의 갯수
		int pageCount = 10;		// 하단에 보여줄 페이지 리스트의 갯수
		
		// 총 게시물의 갯수
		int totalCount = 0;
		
		try {
			con = dataSource.getConnection();
			String query = "select count(*) as total from board where category = ?";
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
