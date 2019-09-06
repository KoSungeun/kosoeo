<%@page import="com.study.jsp.MemberDTO"%>
<%@page import="com.study.jsp.MemberDAO"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		dto.setId("abc");
		dto.setPw("123");
		dto.setName("홍길동");
		dto.setPhone("010-1234-5678");
		dto.setGender("man");
		int nResult = memberDAO.memberInsert(dto);
		
		dto.setId("def");
		dto.setPw("456");
		dto.setName("전우치");
		dto.setPhone("010-9012-3456");
		dto.setGender("man");
		nResult = memberDAO.memberInsert(dto);
		
		dto.setId("ghi");
		dto.setPw("789");
		dto.setName("손오공");
		dto.setPhone("010-7890-1234");
		dto.setGender("man");
		nResult = memberDAO.memberInsert(dto);
		
		out.println("insert success");
	%>
	<br />
	<a href="memberSelect.jsp">회원정보 보기</a>

</body>
</html>