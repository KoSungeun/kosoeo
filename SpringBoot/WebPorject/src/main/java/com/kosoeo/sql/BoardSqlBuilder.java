package com.kosoeo.sql;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class BoardSqlBuilder implements ProviderMethodResolver {

	public String write () {
		return new SQL() {{
			INSERT_INTO("BOARD");
			INTO_COLUMNS("NO", "CATEGORY", "MEMBERNO", "TITLE", "CONTENT", "BGROUP", "STEP", "INDENT");
			INTO_VALUES("BOARD_SEQ.NEXTVAL", "#{memberNo}", "#{title}", "#{content}", "#{category}");
		}}.toString();	
	}
}
