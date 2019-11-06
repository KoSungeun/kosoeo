<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="student" method="get">
		student id : <input type="text" name="id"> <br />
		<input type="submit" value="Get 전송">
	</form>
	
	<hr>
	
	<form action="student" method="post">
		student id : <input type="text" name="id"> <br />
		<input type="submit" value="Post전송">
	</form>

</body>
</html>