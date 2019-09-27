<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../header.jsp"></jsp:include>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>
<script>
	$(document).ready(function() {
		$('#summernote').summernote();
	});
</script>




<form action="write.do" method="post">
	<input type="hidden" name="category" value="${ccategory}">

	<div class="input-group input-group-lg">
		<div class="input-group-prepend">
			<span class="input-group-text" id="inputGroup-sizing-lg">닉네임</span>
		</div>
		<input type="text" name="name" class="form-control"
			aria-label="Sizing example input"
			aria-describedby="inputGroup-sizing-lg">
	</div>

	<div class="input-group input-group-lg">
		<div class="input-group-prepend">
			<span class="input-group-text" id="inputGroup-sizing-lg">제목</span>
		</div>
		<input type="text" name="title" class="form-control"
			aria-label="Sizing example input"
			aria-describedby="inputGroup-sizing-lg">
	</div>
	<textarea id="summernote" name="content"></textarea>
	<input class="btn btn-danger" type="submit" value="입력">
	&nbsp;&nbsp; <a class="btn btn-danger" href="${action}.do">목록보기</a>
</form>



<jsp:include page="../footer.jsp"></jsp:include>