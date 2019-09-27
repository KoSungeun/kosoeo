<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.Instant"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.LocalTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp"></jsp:include>

<table
	class="table table-hover table-striped table-dark  shadow p-3 mb-5 rounded">
	<thead class="bg-danger">
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th class="w-50">제목</th>
			<th>날짜</th>
			<th>히트</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="dto">
			<tr>
				<td>${dto.no}</td>
				<td>${dto.name}</td>
				<td><c:forEach begin="1" end="${dto.indent}">-</c:forEach> 
				<a href="content.do?no=${dto.no}" class="text-white Stretched link">${dto.title}
				</a>
				<c:if test="${dto.postdate.compareTo(yesterday) > 0}">
				<span class="badge badge-secondary">New</span>
				</c:if>
				</td>
				
				<td>${dto.postdate}</td>
				<td>${dto.hit}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5"><a href="writeView.do"
				class="btn btn-danger btn-lg btn-block">글작성</a></td>
		</tr>
	</tbody>
</table>

<ul class="pagination justify-content-center pagination-lg">
	<!-- 처음 -->
	<c:if test="${type != null || word != null}">
		<c:set var="pageQuery" value="&type=${type}&word=${word}"></c:set>
	</c:if>
	<c:choose>
		<c:when test="${(page.curPage - 1) < 1}">
			<li class="page-item disabled"><a class="page-link" href="">&lt;&lt;</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="${action}.do?page=1">&lt;&lt;</a></li>
		</c:otherwise>
	</c:choose>
	<!-- 이전 -->
	<c:choose>
		<c:when test="${(page.curPage -1) < 1}">
			<li class="page-item disabled"><a class="page-link" href="">&lt;</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link"
				href="${action}.do?page=${page.curPage -1}${pageQuery}">&lt;</a></li>
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
					href="${action}.do?page=${fEach}${pageQuery}">${fEach}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<!-- 다음 -->
	<c:choose>
		<c:when test="${(page.curPage + 1) > page.totalPage}">
			<li class="page-item disabled"><a class="page-link" href="">&gt;</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link"
				href="${action}.do?page=${page.curPage + 1}${pageQuery}">&gt;</a></li>
		</c:otherwise>
	</c:choose>
	<!-- 끝 -->
	<c:choose>
		<c:when test="${page.curPage == page.totalPage}">
			<li class="page-item disabled"><a class="page-link" href="">&gt;&gt;</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link"
				href="${action}.do?page=${page.totalPage}${pageQuery}">&gt;&gt;</a></li>
		</c:otherwise>
	</c:choose>
</ul>



<form action="${action}.do" method="get">
	<div class="form-row">
		<div class="col-auto">
			<select name="type" class="form-control">
				<option <c:if test="${type == 'title'}">selected</c:if> selected value="title">제목</option>
				<option <c:if test="${type == 'name'}">selected</c:if> value="name">이름</option>
				<option <c:if test="${type == 'content'}">selected</c:if> value="content">내용</option>
			</select>
		</div>
		<div class="col-auto">
			<input type="text" class="form-control" name="word" value="${word}">
		</div>
		<div class="col-auto">
			<button type="submit" class="btn btn-primary btn-block">검색</button>
		</div>
	</div>
</form>

<jsp:include page="../footer.jsp"></jsp:include>
