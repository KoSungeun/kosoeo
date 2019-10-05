<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp"></jsp:include>
<jsp:include page="header.jsp"></jsp:include>


<table
	class="table table-hover table-striped shadow p-3 mb-4 mt-2">
	<thead class="thead-dark">
		<tr>
			<th>이메일</th>
			<th>이름</th>
			<th>별명</th>
			<th>가입일</th>
			<th>정지</th>
			<th>관리자</th>
			<th>관리</th>
		</tr>
	</thead>
	<tbody>

		<c:forEach items="${list }" var="member">
		<c:set var="formatter" value="${ DateTimeFormatter.ofPattern('yyyy-MM-dd HH:mm:ss')}"></c:set>
		<c:set var="joinDate" value="${ member.joinDate.toLocalDateTime()}"></c:set>
			<tr>
				<td>${member.email }</td>
				<td>${member.name }</td>
				<td>${member.nickName }</td>
				<td>${joinDate.format(formatter)}</td>
				<td><c:if test="${member.isBlock()}"><span class="badge badge-danger">정지</span></c:if></td>
				<td><c:if test="${member.isAdmin()}"><span class="badge badge-danger">관리자</span></c:if></td>		
				<td>
					<div class="btn-group ">
					  <button type="button" class="btn btn-danger btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					    	관리
					  </button>
					  <div class="dropdown-menu">
					    <a class="dropdown-item" href="withdraw.do?memberNo=${member.no}&type=${param.type}&word=${param.word}">탈퇴</a>
					    <a class="dropdown-item" href="block.do?memberNo=${member.no}&type=${param.type}&word=${param.word}">정지</a>
					  </div>
					</div>

				</td>
			</tr>
		</c:forEach>
		

	</tbody>
</table>


<ul class="pagination justify-content-center pagination-lg">
	<!-- 처음 -->
	<c:if test="${param.type != null || param.word != null}">
		<c:set var="pageQuery" value="&type=${param.type}&word=${param.word}"></c:set>
	</c:if>
	<c:choose>
		<c:when test="${(page.curPage - 1) < 1}">
			<li class="page-item disabled"><a class="page-link" href=""><i class="fas fa-angle-double-left"></i></a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="member.do?page=1"><i class="fas fa-angle-double-left"></i></a></li>
		</c:otherwise>
	</c:choose>
	<!-- 이전 -->
	<c:choose>
		<c:when test="${(page.curPage -1) < 1}">
			<li class="page-item disabled"><a class="page-link" href=""><i class="fas fa-angle-left"></i></a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link"
				href="member.do?page=${page.curPage -1}${pageQuery}"><i class="fas fa-angle-left"></i></a></li>
		</c:otherwise>
	</c:choose>
	<!--  개별 페이지 -->
	<c:forEach var="fEach" begin="${page.startPage}" end="${page.endPage}"
		step="1">
		<c:choose>
			<c:when test="${page.curPage == fEach}">
				<li class="page-item active" aria-current="page"><span
					class="page-link"> ${fEach} <span class="sr-only">(current)</span>
				</span></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link"
					href="member.do?page=${fEach}${pageQuery}">${fEach}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<!-- 다음 -->
	<c:choose>
		<c:when test="${(page.curPage + 1) > page.totalPage}">
			<li class="page-item disabled"><a class="page-link" href=""><i class="fas fa-angle-right"></i></a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link"
				href="member.do?page=${page.curPage + 1}${pageQuery}"><i class="fas fa-angle-right"></i></a></li>
		</c:otherwise>
	</c:choose>
	<!-- 끝 -->
	<c:choose>
		
		<c:when test="${page.curPage == page.totalPage}">
			<li class="page-item disabled"><a class="page-link" href=""><i class="fas fa-angle-double-right"></i></a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link"
				href="member.do?page=${page.totalPage}${pageQuery}"><i class="fas fa-angle-double-right"></i></a></li>
		</c:otherwise>
	</c:choose>
</ul>


<div class="d-flex justify-content-end">
<form action="member.do" method="get">
	<div class="form-row">
		<div class="col-auto">
			<select name="type" class="form-control">
				<option <c:if test="${param.type == 'email'}">selected</c:if> selected value="email">이메일</option>
				<option <c:if test="${param.type == 'name'}">selected</c:if> value="name">이름</option>
				<option <c:if test="${param.type == 'nickName'}">selected</c:if> value="nickName">별명</option>
			</select>
		</div>
		<div class="col-auto">
			<input type="text" class="form-control" name="word" value="${param.word}">
		</div>
		<div class="col-auto">
			<button type="submit" class="btn btn-danger btn-block">검색</button>
		</div>
	</div>
</form>
</div>




</div>
<jsp:include page="../footer.jsp"></jsp:include>