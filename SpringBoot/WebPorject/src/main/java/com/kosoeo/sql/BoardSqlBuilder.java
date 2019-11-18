package com.kosoeo.sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class BoardSqlBuilder implements ProviderMethodResolver {

	

	public static String write () {
		return new SQL() {{
			INSERT_INTO("BOARD");
			INTO_COLUMNS("NO", "CATEGORY", "MEMBERNO", "TITLE", "CONTENT", "BGROUP", "STEP", "INDENT");
			INTO_VALUES("BOARD_SEQ.NEXTVAL", "#{memberNo}", "#{title}", "#{content}", "#{category}");
		}}.toString();	
	}
	public static String list (@Param("curPage") final int curPage, @Param("category") final int category, @Param("type") final String type, @Param("word") final String word) {

		int listCount = 10;		// 한 페이지당 보여줄 게시물의 갯수
		int pageCount = 10;		// 하단에 보여줄 페이지 리스트의 갯수

		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;

		return new SQL() {{
			SELECT("*");
			FROM("(" +
				new SQL() {{
					SELECT("ROWNUM num, A.*");
					FROM("(" +
						new SQL() {{
							SELECT("*");
							FROM("BOARD");
							INNER_JOIN("MEMBER on BOARD.MEMBERNO = MEMBER.NO");
							WHERE("CATEGORY = #{category}");
							if(type != null) {
								AND();
								WHERE("#{type} like #{word}");
							}
							ORDER_BY("BGROUP DESC", "STEP ASC");
						}}
					+") A");
					WHERE("ROWNUM <=" + nEnd);
				}}
			+") B");
			WHERE("B.num >=" + nStart);
		}}.toString();
	}
}
