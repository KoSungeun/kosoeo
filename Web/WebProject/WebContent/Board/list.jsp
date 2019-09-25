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
				<td><c:forEach begin="1" end="${dto.indent}">-</c:forEach> <a
					href="content.do?no=${dto.no}" class="text-white Stretched link">${dto.title}</a></td>
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
	<c:choose>
		<c:when test="${(page.curPage - 1) < 1}">
			<li class="page-item disabled"><a class="page-link" href="">&lt;&lt;</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="list.do?page=1">&lt;&lt;</a></li>
		</c:otherwise>
	</c:choose>
	<!-- 이전 -->
	<c:choose>
		<c:when test="${(page.curPage -1) < 1}">
			<li class="page-item disabled"><a class="page-link" href="">&lt;</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link"
				href="list.do?page=${page.curPage -1}">&lt;</a></li>
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
					href="list.do?page=${fEach}">${fEach}</a></li>
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
				href="list.do?page=${page.curPage + 1}">&gt;</a></li>
		</c:otherwise>
	</c:choose>
	<!-- 끝 -->
	<c:choose>
		<c:when test="${page.curPage == page.totalPage}">
			<li class="page-item disabled"><a class="page-link" href="">&gt;&gt;</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link"
				href="list.do?page=${page.totalPage}">&gt;&gt;</a></li>
		</c:otherwise>
	</c:choose>
</ul>



<form action="list.do" method="get">
	<div class="form-row">
		<div class="col-auto">
			<select name="type" class="form-control">
				<option selected value="1">제목</option>
				<option value="2">이름</option>
				<option value="3">내용</option>
			</select>
		</div>
		<div class="col-auto">
			<input type="text" class="form-control" name="word">
		</div>
		<div class="col-auto">
			<button type="submit" class="btn btn-primary btn-block">검색</button>
		</div>
	</div>
</form>

<jsp:include page="../footer.jsp"></jsp:include>