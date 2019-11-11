<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일목록보기</title>
</head>
<body>
	<h2>파일목록보기</h2>
	<ul>
	<c:forEach items="${fileMap }" var="file">
		<li>
			파일명 : <a href="download?fileName=${file.key }&oriFileName=test.png">${file.key }</a>
			파일크기 : ${file.value }
		
		</li>
	
	</c:forEach>
		
	</ul>

</body>
</html>