<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.ckeditor.com/4.12.1/standard/ckeditor.js"></script>
</head>
<body>
	<table width="500" cellpadding="0" cellspacing="0"  border="1">
		<form action="modify" method="post">
			<input type="hidden" name="bId" value="${content_view.bId}">
			<tr>
				<td> 번호 </td>
				<td> ${content_view.bId} </td>
			</tr>
			<tr>
				<td> 히트 </td>
				<td> ${content_view.bHit} </td>
			</tr>
			<tr>
				<td> 이름 </td>
				<td> <input type="text" name="bName" value="${content_view.bName}"> </td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="bTitle" value="${content_view.bTitle}"> </td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td> <textarea rows="10" name="bContent" id="ck">${content_view.bContent}</textarea>
				<script>
				CKEDITOR.replace('ck');
				</script>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정"> &nbsp;&nbsp;
					<a href="content_view?bId=${content_view.bId}">취소</a> &nbsp;&nbsp;
					<a href="list?page=<%= session.getAttribute("cpage") %>">목록보기</a> &nbsp;&nbsp;
				</td>
			</tr>
		</form>
	</table>
</body>
</html>