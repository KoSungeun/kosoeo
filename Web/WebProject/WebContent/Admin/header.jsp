<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<style>
.page-item.active .page-link {
  z-index: 1;
  background-color: #dc3545;
  border-color: #dc3545;
}
.page-link {
  color: #dc3545;
}
.page-link:hover {
	color: #dc3545;
}
.page-link:focus {
	box-shadow: 0px 0px 0px 0.2rem rgba(255,0,0,0.25);

}

.nav-pills .nav-link.active, .nav-pills .show>.nav-link {

	background-color: #dc3545;
}

</style>




<c:if test="${requestScope['javax.servlet.forward.servlet_path'].indexOf('member') > -1}">
<c:set var="nav" value="0"></c:set>
</c:if>
<c:if test="${requestScope['javax.servlet.forward.servlet_path'].indexOf('board') > -1}">
<c:set var="nav" value="1"></c:set>
</c:if>
<c:if test="${requestScope['javax.servlet.forward.servlet_path'].indexOf('rank') > -1}">
<c:set var="nav" value="2"></c:set>
</c:if>


<div class="container-fluid">

<c:if test="${msg != null }">
	<div class="alert alert-danger alert-dismissible fade show mt-3" role="alert">
	  <strong>${msg}</strong>
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    <span aria-hidden="true">&times;</span>
	  </button>
	</div>
	<c:remove var="msg" scope="session"/>
</c:if>



<ul class="nav nav-tabs mt-3">
  <li class="nav-item">
    <a class="nav-link <c:if test='${nav == 0}'>active</c:if>" href="member.do">회원</a>
  </li>
  <li class="nav-item">
    <a class="nav-link <c:if test='${nav == 1}'>active</c:if>" href="board.do?category=0">게시판</a>
  </li>
  <li class="nav-item">
    <a class="nav-link <c:if test='${nav == 2}'>active</c:if>" href="rank.do">순위</a>
  </li>
</ul>	

