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

<div class="container-fluid">
<form action="write.do" method="post" class="mt-2">
<input type="hidden" name="category" value="${ccategory}">
<input type="hidden" name="memberNo" value="${member.no}">
 <fieldset disabled>
  <div class="form-group row">
    <label for="inputName" class="col-sm-2 col-form-label">이름</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="name"  placeholder="${member.nickName}" >
    </div>
  </div>
  </fieldset>
    <div class="form-group row">
    <label for="inputTitle" class="col-sm-2 col-form-label">제목</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="title" name="title" placeholder="제목" >
    </div>
  </div>
  <div class="form-group row">
      <div class="col-sm-12">
  <textarea id="summernote" name="content"></textarea>
  </div>	
  </div>
   <div class="form-group row">
    <div class="col-sm-12">
      <button type="submit" class="btn btn-danger">입력</button>
      <a class="btn btn-danger" href="${action}.do">목록보기</a>
    </div>
  </div>
</form>
</div>
<jsp:include page="../footer.jsp"></jsp:include>