<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP/Servlet 21-1</title>
</head>
<body>
	<form action="loginOk.do" method="post">
		아이디 : <input type="text" name="id"
						value="<% if(session.getAttribute("id") != null)
									 out.println(session.getAttribute("id"));										
							%>"> <br>
		비밀번호 : <input type="password" name="pw"><br><p>
		<input type="submit" value="로그인" onclick=""> &nbsp;&nbsp;      
		<input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'"> &nbsp;&nbsp;&nbsp;
	</form>

</body>
</html>