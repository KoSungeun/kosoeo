<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP/Servlet 26-2</title>
</head>
<body>

	<%
		request.setAttribute("id", "abcde");
		request.setAttribute("pw", "12345");
		
		//response.sendRedirect("RequestObj"); // <-- 이 건 안됨.
		request.getRequestDispatcher("RequestObj").forward(request, response);
	%>
</body>
</html>