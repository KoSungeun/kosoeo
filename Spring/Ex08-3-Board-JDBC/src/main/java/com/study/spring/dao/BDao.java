package com.study.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.study.spring.BPageInfo;
import com.study.spring.dto.BDto;
import com.study.spring.util.Constant;




public class BDao {

	private static BDao instance = new BDao();

	JdbcTemplate template = null;
	
	int listCount = 10;		// 한 페이지당 보여줄 게시물의 갯수
	int pageCount = 10;		// 하단에 보여줄 페이지 리스트의 갯수
	
	public BDao() {
		template = Constant.teplate;

	}
	
	public static BDao getInstance() {
		return instance;
	}
	
	public void write(final String bName, final String bTitle, final String bContent) {
		
		
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String query = "insert into mvc_board " + 
						   " (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) " +
						   " values " + 
						   " (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0 )";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);	
				pstmt.setString(3, bContent);
				return pstmt;
			}
		});
	}
	
	public ArrayList<BDto> list(int curPage) {
	
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;

	
		String query = "select * " +
					   "	from ( " +
					   "		select rownum num, A.* " +
					   "			from ( " +
					   "				select * " +
					   "				from mvc_board " +
					   "			order by bGroup desc, bStep asc ) A " + 
					   "		where rownum <= "+ nEnd + " ) B " +
					   " where B.num >= " + nStart;
	
		return (ArrayList<BDto>) template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));
		
	}
	
	public BDto contentView(String strID) {
		upHit(strID);
		
		String query = "select * from mvc_board where bId = " + strID;

		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
		
	}
	
	public void modify(final String bId, final String bName, final String bTitle, final String bContent) {

		
		String query = "update mvc_board " + 
				   "   set bName = ?, " +
				   "       bTitle = ?, " +
				   "       bContent = ? " +
				   " where bId = ?";
	
		
		template.update(query, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bName);
				ps.setString(2, bTitle);	
				ps.setString(3, bContent);
				ps.setString(4, bId);
			}
		});
		
		
		

	}
	
	public void upHit(final String bId) {

		String query = "update mvc_board " + 
				   "   set bHit =  bHit + 1 " +
				   " where bId = ?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(bId));
		
			}
		});
		
	}
	
	public void delete(final String bId) {
		String query = "delete from mvc_board where bId = ?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bId);

			}
		});

	}
	
	public BDto replyView(String str) {
		
		String query = "select * from mvc_board where bId = " + str;
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
		
	}

	public void reply(final BDto bto) {
		
		replyShape(bto.getbGroup(), bto.getbStep());

		String query = "insert into mvc_board " + 
				   " (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) " +
				   " values " + 
				   " (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
		
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bto.getbName());
				ps.setString(2, bto.getbTitle());	
				ps.setString(3, bto.getbContent());
				ps.setInt(4, bto.getbGroup());
				ps.setInt(5, bto.getbStep() + 1);
				ps.setInt(6, bto.getbIndent() + 1);
			}
		});
		
	}
	
	public void replyShape(final int bGroup, final int bStep) {
		
		String query = "update mvc_board " + 
				   "   set bStep = bStep + 1 " +
				   " where bGroup = ? and bStep > ?";
		
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, bGroup);
				ps.setInt(2, bStep);
			}
		});
	}
	
	public BPageInfo articlePage(int curPage) {

		
		int listCount = 10;		// 한 페이지당 보여줄 게시물의 갯수
		int pageCount = 10;		// 하단에 보여줄 페이지 리스트의 갯수
		
		// 총 게시물의 갯수
		int totalCount = 0;
		
		String query = "select count(*) as total from mvc_board";
		
		totalCount = template.queryForObject(query, Integer.class);
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
		
		BPageInfo pinfo = new BPageInfo();
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
