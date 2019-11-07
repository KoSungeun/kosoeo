<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
	<table
		class="table table-hover table-striped table-dark  shadow p-3 mb-5 rounded">
		<thead class="bg-danger">
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th class="w-75">제목</th>
				<th>날짜</th>
				<th>히트</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="dto">
				<tr>
					<td>${dto.bId}</td>
					<td>${dto.bName}</td>
					<td><c:forEach begin="1" end="${dto.bIndent}">-</c:forEach> <a
						href="content_view?bId=${dto.bId}"
						class="text-white Stretched link">${dto.bTitle}</a></td>
					<td>${dto.bDate}</td>
					<td>${dto.bHit}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5"><a href="write_view"
					class="btn btn-danger btn-lg btn-block">글작성</a></td>
			</tr>
			<tr>
				<td colspan="5">
					<ul class="pagination justify-content-center pagination-lg">
						<!-- 처음 -->
						<c:choose>
							<c:when test="${(page.curPage - 1) < 1}">
								<li class="page-item disabled"><a class="page-link" href="">&lt;&lt;</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									href="list?page=1">&lt;&lt;</a></li>
							</c:otherwise>
						</c:choose>
						<!-- 이전 -->
						<c:choose>
							<c:when test="${(page.curPage -1) < 1}">
								<li class="page-item disabled"><a class="page-link" href="">&lt;</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									href="list?page=${page.curPage -1}">&lt;</a></li>
							</c:otherwise>
						</c:choose>
						<!--  개별 페이지 -->
						<c:forEach var="fEach" begin="${page.startPage}"
							end="${page.endPage}" step="1">
							<c:choose>
								<c:when test="${page.curPage == fEach}">
									<li class="page-item active" aria-current="page"><span
										class="page-link"> ${fEach} <span class="sr-only">(current)</span>
									</span></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										href="list?page=${fEach}">${fEach}</a></li>
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
									href="list?page=${page.curPage + 1}">&gt;</a></li>
							</c:otherwise>
						</c:choose>
						<!-- 끝 -->
						<c:choose>
							<c:when test="${page.curPage == page.totalPage}">
								<li class="page-item disabled"><a class="page-link" href="">&gt;&gt;</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									href="list?page=${page.totalPage}">&gt;&gt;</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</td>
			</tr>
		</tbody>
	</table>



	totalCount : ${page.totalCount }
	<br> listCount : ${page.listCount }
	<br> totalPage : ${page.totalPage }
	<br> curPage : ${page.curPage }
	<br> pageCount : ${page.pageCount }
	<br> startPage : ${page.startPage }
	<br> endPage : ${page.endPage }
	<br>


</body>
</html>