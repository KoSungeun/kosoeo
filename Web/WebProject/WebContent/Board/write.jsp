<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../header.jsp"></jsp:include>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>
<script>
	$(document).ready(function() {
		$('#summernote').summernote();
	});
</script>

<form action="write.do"  method="post">
	<input type="hidden" name="category" value="${ccategory}"> <input
		type="text" name="name" size="50">
	 <input type="text" name="title" size="50">




	<textarea id="summernote" name="content"></textarea>


	<input class="btn btn-danger" type="submit" value="입력">
	&nbsp;&nbsp; <a class="btn btn-danger" href="list.do">목록보기</a>
</form>



<jsp:include page="../footer.jsp"></jsp:include>