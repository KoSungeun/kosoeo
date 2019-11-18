package com.kosoeo.dao;


import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.ArrayList;

import com.kosoeo.dto.Board;
import com.kosoeo.sql.BoardSqlBuilder;


@Mapper
public interface BoardDAO {
	@InsertProvider(BoardSqlBuilder.class)
	public int write(@Param("memberNo") int memberNo, @Param("title") String title, 
						@Param("content") String content, @Param("category") int category);
	

	@SelectProvider(BoardSqlBuilder.class)
	public ArrayList<Board> list(@Param("curPage") int curPage, @Param("category") int category,
									@Param("type") String type, @Param("word") String word);
}
