<%@page import="com.study.jsp.MemberDao"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="dto" class="com.study.jsp.MemberDto"/>
<jsp:setProperty property="*" name="dto"/>
<%
	dto.setrDate(new Timestamp(System.currentTimeMillis()));
	MemberDao dao = MemberDao.getInstance();
	if(dao.confirmId(dto.getId()) == MemberDao.MEMBER_EXISTENT) {
%>
	<script>
		alert("아이디가 이미 존재 합니다.");
		history.back();
	</script>
<%
	} else {
		int ri = dao.insertMember(dto);
		if(ri == MemberDao.MEMBER_JOIN_SUCCESS) {
	session.setAttribute("id", dto.getId());
%>
		<script>
			alert("회원가입을 축하 합니다.");
			document.location.href="login.jsp";
		</script>			
<%
		} else {
%>		
		<script>
			alert("회원가입에 실패했습니다.");
			document.location.href="login.jsp";
		</script>
<%
		}
	}
%>


	


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP/Servlet 21-1</title>
</head>
<body>

</body>
</html>