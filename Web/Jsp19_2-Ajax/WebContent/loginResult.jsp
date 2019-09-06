<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%!
    	String name, id, pw;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		name = (String)session.getAttribute("name");
		id = (String)session.getAttribute("id");
		pw = (String)session.getAttribute("pw");
	
	%>
	
	<%= name %>님 안녕하세요 <br><p>
	
	<a href="logout.jsp">로그아웃</a> &nbsp;&nbsp;
	 <a href="modify.jsp">정보수정</a> &nbsp;&nbsp; 
	 <a href="withdraw.jsp">회원탈퇴</a>
	
</body>
</html>