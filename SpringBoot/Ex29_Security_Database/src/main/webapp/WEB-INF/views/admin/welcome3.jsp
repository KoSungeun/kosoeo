<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
welcome : Admin
<hr>
<sec:authorize access="isAuthenticated()">
<p>Log-In</p>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
<p>Log-Out</p>
</sec:authorize>

USER ID : <sec:authentication property="name"/><br/>
<c:url value="/logout" var="logoutRul" />
<a href="${logoutUrl }">Log Out</a> <br/>
</body>
</html>