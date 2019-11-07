<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<table class="table table-hover table-striped table-dark  shadow p-3 mb-5 rounded">
		<tr>
			<td> 번호 </td>
			<td class="w-75 "> ${content_view.bId} </td>
		</tr>
		<tr>
			<td> 히트 </td>
			<td> ${content_view.bHit} </td>
		</tr>
		<tr>
			<td> 이름 </td>
			<td> ${content_view.bName} </td>
		</tr>
		<tr>
			<td> 제목 </td>
			<td> ${content_view.bTitle} </td>
		</tr>
		<tr>
			<td> 내용 </td>
			<td> ${content_view.bContent} </td>
		</tr>
		<tr>
			<td colspan="2">
				<a class="btn btn-danger" href="modify_view?bId=${content_view.bId}">수정</a> &nbsp;&nbsp;
				<a class="btn btn-danger" href="list?page=<%= session.getAttribute("cpage") %>">목록보기</a> &nbsp;&nbsp;
				<a class="btn btn-danger" href="delete?bId=${content_view.bId}">삭제</a> &nbsp;&nbsp;
				<a class="btn btn-danger" href="reply_view?bId=${content_view.bId}">답변</a>
			</td>
	
		</tr>
		
	</table>
</body>
</html>