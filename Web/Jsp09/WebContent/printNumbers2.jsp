<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PrintNumber 2</title>
</head>
<body>

<%
	String max = request.getParameter("max");
	int maxValue = Integer.parseInt(max);	

		
	for (int i=0; i< maxValue; i++) {
		out.println(i + "<br>");
	}

%>

</body>
</html>