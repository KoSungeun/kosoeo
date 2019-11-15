<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../header.jsp"></jsp:include>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bs-custom-file-input/dist/bs-custom-file-input.min.js"></script>
<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			height: 400,
		});
		bsCustomFileInput.init()
	});
</script>

<div class="container-fluid">
	<form action="update.do" method="post" class="mt-2"
		enctype="multipart/form-data">
		<input type="hidden" name="category" value="${ccategory}"> 
		<input type="hidden" name="memberNo" value="${member.no}">
		<input type="hidden" name="no" value="${content_view.no}">
		<fieldset disabled>
			<div class="form-group row">
				<label for="inputName" class="col-sm-2 col-form-label">이름</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name"
						placeholder="${member.nickName}">
				</div>
			</div>
		</fieldset>
		<div class="form-group row">
			<label for="inputTitle" class="col-sm-2 col-form-label">제목</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="title" name="title"
					placeholder="제목" value="${content_view.title}">
			</div>
		</div>
		
		
		<div class="form-group row">
			<div class="col-sm-12">
				<c:forEach items="${files}" var="file">
					<span id="${file.no}remove">
						<i class="far fa-file"></i>
						<a href="${file.path}${file.realName}"  class="Stretched link text-dark mr-2">${file.submitName}</a> 
						<i class="far fa-trash-alt mr-5" id="${file.no}/${file.realName}" style="cursor:pointer"></i>
					</span>
				</c:forEach> 
			</div>
		</div>
		
		
		
	
		
		<div class="form-group row">
			<div class="col-sm-12">
				<div class="custom-file">
				<input type="file" class="custom-file-input" name="file"
				id="customFile" multiple> 
				<label class="custom-file-label"
				for="customFile" data-browse="찾아보기">파일 선택</label>
				</div>
			</div>
		</div>
		
		<div class="form-group row">
			<div class="col-sm-12">
				<textarea id="summernote" name="content">${content_view.content }</textarea>
			</div>
		</div> 
		<script>

		</script>


		<div class="form-group row">
			<div class="col-sm-12">
				<button type="submit" class="btn btn-danger">수정</button>
				<a class="btn btn-danger" href="${action}">취소</a>
			</div>
		</div>
	</form>
</div>

<script>
$(".fa-trash-alt").click(function(){
	
	var fileInfo = $(this).attr('id');
	var fileNo = fileInfo.substr(0, fileInfo.indexOf("/"));
	var removeTaget = "#" + fileNo + "remove";
	$.ajax({
	    url: 'fileDelete.do',
	    type: 'post',
	    dataType: 'json',
	    data: {
	    	fileNo: fileNo,
	    	realName: fileInfo.substr(fileInfo.indexOf("/") + 1)
	    }
	}).done(function(data) {
		$(".modal-body").html(data.msg);
		$("#confirmFooter").removeClass("d-none");
		$("#alertModal").modal();
		$(removeTaget).remove();
	});
});

</script>


<jsp:include page="../footer.jsp"></jsp:include>