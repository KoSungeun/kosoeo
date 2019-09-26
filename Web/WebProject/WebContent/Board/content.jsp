<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"></jsp:include>
	<table class="table table-hover table-striped table-dark  shadow p-3 mb-5 rounded">
		<tr>
			<td> 번호 </td>
			<td class="w-75 "> ${content_view.no} </td>
		</tr>
		<tr>
			<td> 히트 </td>
			<td> ${content_view.hit} </td>
		</tr>
		<tr>
			<td> 이름 </td>
			<td> ${content_view.name} </td>
		</tr>
		<tr>
			<td> 제목 </td>
			<td> ${content_view.title} </td>
		</tr>
		<tr>
			<td> 내용 </td>
			<td> ${content_view.content} </td>
		</tr>
		<tr>
			<td colspan="2">
				<a class="btn btn-danger" href="modify_view.do?bId=${content_view.no}">수정</a> &nbsp;&nbsp;
				<a class="btn btn-danger" href="list.do?page=<%= session.getAttribute("cpage") %>">목록보기</a> &nbsp;&nbsp;
				<a class="btn btn-danger" href="delete.do?bId=${content_view.no}">삭제</a> &nbsp;&nbsp;
				<a class="btn btn-danger" href="reply_view.do?bId=${content_view.no}">답변</a>
			</td>
	
		</tr>
		
	</table>
<jsp:include page="../footer.jsp"></jsp:include>