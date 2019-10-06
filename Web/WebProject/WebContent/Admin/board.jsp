<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp"></jsp:include>
<jsp:include page="header.jsp"></jsp:include>

<nav class="nav nav-pills flex-column flex-sm-row mt-3">
  <a class="flex-sm-fill text-sm-center nav-link <c:if test="${param.category != 0}">text-dark</c:if> <c:if test="${param.category == 0}">active</c:if>" href="board.do?category=0">공지사항</a>
  <a class="flex-sm-fill text-sm-center nav-link <c:if test="${param.category != 1}">text-dark</c:if> <c:if test="${param.category == 1}">active</c:if>" href="board.do?category=1">자유게시판</a>
  <a class="flex-sm-fill text-sm-center nav-link <c:if test="${param.category != 2}">text-dark</c:if> <c:if test="${param.category == 2}">active</c:if>" href="board.do?category=2">자료실</a>
</nav>

<table
	class="table table-hover table-striped shadow p-3 mb-4 mt-2">
	<thead class="thead-dark">
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>제목</th>
			<th>날짜</th>
			<th>히트</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="dto">
		<c:set var="formatter" value="${ DateTimeFormatter.ofPattern('yyyy-MM-dd HH:mm:ss')}"></c:set>
		<c:set var="postdate" value="${ dto.postdate.toLocalDateTime()}"></c:set>
			<tr>
				<td>${dto.no}</td>
				<td>${dto.member.nickName}</td>
				<td><c:forEach begin="1" end="${dto.indent}"><i class="fas fa-chevron-circle-right"></i></c:forEach> 
				<a href="../Board/content.do?no=${dto.no}" class="text-dark Stretched link">${dto.title}</a>	
				<c:if test="${dto.postdate.compareTo(yesterday) > 0}">
				<span class="badge badge-danger">New</span>
				</c:if>
				</td>
				<td>${postdate.format(formatter)}</td>
				<td>${dto.hit}</td>
				<td><a href="boardDelete.do?boardNo=${dto.no}&type=${param.type}&word=${param.word}" class="text-dark"><i class="fas fa-trash-alt"></a></i></td>
			</tr>
		</c:forEach>
		
		<c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/WebProject/Board/notice.do' && member.isAdmin() || 
		requestScope['javax.servlet.forward.request_uri'] != '/WebProject/Board/notice.do'}">
		<tr>
			<td colspan="6"><button type="button" class="btn btn-danger btn-lg btn-block" id="writeBtn">글작성</button></td>
		</tr>
		</c:if>
		
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
			<li class="page-item"><a class="page-link" href="board.do?category=${param.category}&page=1"><i class="fas fa-angle-double-left"></i></a></li>
		</c:otherwise>
	</c:choose>
	<!-- 이전 -->
	<c:choose>
		<c:when test="${(page.curPage -1) < 1}">
			<li class="page-item disabled"><a class="page-link" href=""><i class="fas fa-angle-left"></i></a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link"
				href="board.do?category=${param.category}&page=${page.curPage -1}${pageQuery}"><i class="fas fa-angle-left"></i></a></li>
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
					href="board.do?category=${param.category}&page=${fEach}${pageQuery}">${fEach}</a></li>
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
				href="board.do?category=${param.category}&page=${page.curPage + 1}${pageQuery}"><i class="fas fa-angle-right"></i></a></li>
		</c:otherwise>
	</c:choose>
	<!-- 끝 -->
	<c:choose>
		
		<c:when test="${page.curPage == page.totalPage}">
			<li class="page-item disabled"><a class="page-link" href=""><i class="fas fa-angle-double-right"></i></a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link"
				href="board.do?category=${param.category}&page=${page.totalPage}${pageQuery}"><i class="fas fa-angle-double-right"></i></a></li>
		</c:otherwise>
	</c:choose>
</ul>


<div class="d-flex justify-content-end">
<form action="board.do" method="get">
	<input type="hidden" name="category" value="${param.category }"></input>
	<div class="form-row">
		<div class="col-auto">
			<select name="type" class="form-control">
				<option <c:if test="${param.type == 'title'}">selected</c:if> selected value="title">제목</option>
				<option <c:if test="${param.type == 'nickName'}">selected</c:if> value="nickName">이름</option>
				<option <c:if test="${param.type == 'content'}">selected</c:if> value="content">내용</option>
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

<script>
	$("#writeBtn").click(function() {
		if(${member == null}) {
			$(".modal-body").html("로그인후 이용해주세요.");
			$("#loginFooter").removeClass("d-none");
			$("#alertModal").modal();
		} else {
			location.href="../Board/writeView.do";
		}
	});
</script>


<jsp:include page="../footer.jsp"></jsp:include>
