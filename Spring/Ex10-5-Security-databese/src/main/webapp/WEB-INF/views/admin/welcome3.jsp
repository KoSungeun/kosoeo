<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
welcome : Admin

<hr>

<%--
<c:if test="${not empty pageContext.request.userPrincipal }">
<p> is Log-In </p>
</c:if>
<c:if test="${empty pageContext.request.userPrincipal }">
<p> is Log-Out </p>
</c:if>  --%>

<s:authorize ifAnyGranted="ROLE_USER">
<p>Log-In</p>
</s:authorize>

<s:authorize ifNotGranted="ROLE_USER">
<p>Log-Out</p>
</s:authorize>


<%-- USER ID : ${pageContext.request.userPrincipal.name }<br/> --%>
USER ID : <s:authentication property="name"/> <br/>

<c:url value="/j_spring_security_logout" var="logoutUrl" />
<a href="${logoutUrl }">Log Out</a> <br />
</body>
</html>