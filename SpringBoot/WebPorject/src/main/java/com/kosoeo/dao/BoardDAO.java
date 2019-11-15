package com.kosoeo.dao;


import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kosoeo.sql.BoardSqlBuilder;


@Mapper
public interface BoardDAO {
	@InsertProvider(BoardSqlBuilder.class)
	public int write(@Param("memberNo") int memberNo, @Param("title") String title, @Param("content") String content, @Param("category") int category);
}
