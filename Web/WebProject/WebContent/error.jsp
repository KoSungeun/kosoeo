<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<div class="container-fluid">


<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

<div class="d-flex justify-content-center">

<h1 class="display-1">페이지가 없습니다.</h1>


</div>

<div class="d-flex justify-content-center mt-3">
<button class="btn btn-outline-dark" onclick="history.back()"><h1 class="m-5">이전페이지</h1></button>
</div>

<script>
	if(${msg != null}) {
		$(".modal-body").html("${msg}");
		$("#confirmFooter").removeClass("d-none");	
		$("#alertModal").modal();
		
		
		$('#alertModal').on('hidden.bs.modal', function (e) {
			history.back();
		})	
	}
</script>

</div>
<jsp:include page="footer.jsp"></jsp:include>