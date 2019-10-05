<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:include page="../header.jsp"></jsp:include>



<div class="container-fluid">
	<table class="table table-hover table-striped shadow p-3 mb-5 mt-2">
		<tr>
			<td> 번호 </td>
			<td class="w-75"> ${content_view.no} </td>
		</tr>
		<tr>
			<td> 히트 </td>
			<td> ${content_view.hit} </td>
		</tr>
		<tr>
			<td> 이름 </td>
			<td> ${content_view.member.nickName} </td>
		</tr>
		<tr>
			<td> 제목 </td>
			<td> ${content_view.title} </td>
		</tr>
		<tr>
			<td> 첨부파일 </td>
			<td> 	
				<c:forEach items="${files}" var="file">
					<i class="far fa-file"></i>
					<a href="${file.path}${file.realName}"  class="text-dark Stretched link mr-2">${file.submitName}</a> 
					<i class="fas fa-download"></i>
					<a href="fileDown.do?realName=${file.realName}&submitName=${file.submitName}" class="text-dark Stretched link mr-5">다운로드</a>
				</c:forEach> 
			</td>
		</tr>
		<tr>
			<td> 내용 </td>
			<td> ${content_view.content} </td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="d-flex justify-content-end">
				<c:if test="${member.no == content_view.member.no}">
					<a class="btn btn-danger" href="updateView.do?no=${content_view.no}">수정</a> &nbsp;&nbsp;
					<button class="btn btn-danger" id="deleteBtn">삭제</button> &nbsp;&nbsp;
				</c:if>
				<a class="btn btn-danger" href="${action}?page=${cpage}">목록보기</a> &nbsp;&nbsp;
				<a class="btn btn-danger" href="replyView.do?boardNo=${content_view.no}">답변</a>
				</div>
			</td>
	
		</tr>
		
	</table>
		<div class="d-flex justify-content-center mb-5">
			<button class="btn btn-outline-dark mr-3" id="thumbUpBtn">
				<i class="far fa-thumbs-up <c:if test="${thumb.up == 1}">text-primary</c:if> h1 m-3"> 
					<c:choose>
						<c:when test="${thumb.upCount == null}">
							0
						</c:when>
						<c:otherwise>
							${thumb.upCount}
						</c:otherwise>
					</c:choose>
				</i>
			</button>
			<button class="btn btn-outline-dark thumb" id="thumbDownBtn">
				<i class="far fa-thumbs-down <c:if test="${thumb.down == 1}">text-danger</c:if> h1 m-3">
					<c:choose>
						<c:when test="${thumb.downCount == null}">
							0
						</c:when>
						<c:otherwise>
							${thumb.downCount}
						</c:otherwise>
					</c:choose>
				</i>
			</button>
		</div>
	
	
	
	<table class="table table-hover table-striped shadow p-3 mb-5" id="commnetList">	
		<thead>
			<tr>
				<td> 이름 </td>
				<td class="w-50"> 내용 </td>
				<td> 날짜 </td>
				<td> 수정 / 삭제 </td>
			</tr>
		</thead>
		<tbody>
		</tbody>	
	</table>
	
	

<form id="commentForm">
	<input type="hidden" name="memberNo" value="${member.no}">
	<input type="hidden" name="boardNo" value="${content_view.no}">
  	<div class="form-group row">
    <label for="inputComment" class="col-12 col-sm-2 col-xl-1 col-form-label">
    <c:choose>
    	<c:when test="${member != null}">
    		${member.nickName}
    		<c:set var="placeholder" value="여기에 댓글을 입력"></c:set> 		
    	</c:when>
    	<c:otherwise>
    		이름
    		<c:set var="placeholder" value="로그인 후 이용해주세요"></c:set>
    		<c:set var="readonly" value="readonly"></c:set>
    	</c:otherwise>
    </c:choose>
    </label>
    <div class="col">
      <input type="text" class="form-control" id="content" name="content" placeholder="${placeholder}" ${readonly}>
    </div>
    <div class="col-12 mt-2 mt-md-0 col-md-2 col-xl-1">
    	<button type="button" id="commentWriteBtn" class="btn btn-danger btn-block">댓글</button>
    </div>
  </div>
</form>
</div>

<script>
$("#commentWriteBtn").click(function(){
	$.ajax({
	    url: 'commentWrite.do',
	    type: 'post',
	    dataType: 'json',
	    data: $("#commentForm").serialize()
	}).done(function(data) {
		$("#content").val("");
		var result = data["result"];
		if (result == 1) {
			commentList(true);
		} else {
			$(".modal-body").html(data["msg"]);
			if(result == 0) {
				$("#loginFooter").removeClass("d-none");
			} else {
				$("#confirmFooter").removeClass("d-none");	
			}
			$("#alertModal").modal();
		}
	});
});

$("#deleteBtn").click(function() {	
	$.ajax({
	    url: 'delete.do',
	    type: 'post',
	    dataType: 'json',
	    data: {
	    	memberNo: ${content_view.member.no},
	    	boardNo: ${content_view.no}
	    }
	}).done(function(data) {
		alert("SDf");
		var result = data.result;
		$(".modal-body").html(data.msg);
		if (result == 1) {
			$("#confirmFooter").removeClass("d-none");
			$('#alertModal').on('hidden.bs.modal', function (e) {
				location.href = data.location;
			});
		} else {
			if(result == 0) {
				$("#loginFooter").removeClass("d-none");
			} else {
				$("#confirmFooter").removeClass("d-none");	
			}
			
		}
		$("#alertModal").modal();
	});
});

$("#thumbUpBtn").click(function(){
	thumbUpDown("up");
})

$("#thumbDownBtn").click(function(){
	thumbUpDown("down");
})

function thumbUpDown(upDown){
	
	$.ajax({
	    url: 'upDown.do',
	    type: 'post',
	    dataType: 'json',
	    data: {
	    	memberNo: "${member.no}",
	    	boardNo: ${content_view.no},
	    	upDown: upDown
	    }
	}).done(function(data) {
		var result = data["result"];
		if (result == 1) {
			thumbState();
		} else {
			$(".modal-body").html(data["msg"]);
			if(result == 0) {
				$("#loginFooter").removeClass("d-none");
			} else {
				$("#confirmFooter").removeClass("d-none");	
			}
			$("#alertModal").modal();
		}
	});
	
}

function thumbState() {
	$.ajax({
	    url: 'thumbState.do',
	    type: 'post',
	    dataType: 'json',
	    data: {
	    	no: ${content_view.no}
	    }
	}).done(function(data) {
		
		if(data.up == 0 && data.down == 0) {
			$("#thumbUpBtn > i ").removeClass("text-primary");
			$("#thumbDownBtn > i ").removeClass("text-danger");
		} else if(data.up == 1) {
			$("#thumbUpBtn > i ").addClass("text-primary");
			$("#thumbDownBtn > i ").removeClass("text-danger");
		} else if(data.down == 1) {
			$("#thumbDownBtn > i ").addClass("text-danger");
			$("#thumbUpBtn > i ").removeClass("text-primary");
		}
		

		$("#thumbUpBtn > i ").text(" " + data["upCount"]);
		$("#thumbDownBtn > i ").text(" " + data["downCount"]);

	});
	
}

function commentList(isScroll){
	$.ajax({
	    url: 'commentList.do',
	    type: 'post',
	    dataType: 'json',
	    data: {
	    	boardNo: "${content_view.no}"
	    }
	}).done(function(data) {
		$("#commnetList > tbody").empty();
		$.each(data, function(i, e){
			var btn = $("<button>").addClass("btn btn-danger");
			$("<tr>").attr("id", "comment"+i).appendTo($("#commnetList > tbody"))
			.append($("<td>").text(e["member"]["nickName"]))
			.append($("<td>").text(e["content"]))
			.append($("<td>").text(e["commentDate"]))
			.append($("<td>"));
			
			if(e["member"]["no"] == "${member.no}"){
				$("#comment" + i + " > td:eq(3)").append(btn.text("수정").addClass("mr-0 mt-2 mr-md-2 mt-md-0").click(function(){
					$("#comment"+i)
					.after($("<tr>")
						.append($("<td>").attr("colspan", 3)
						.append($("<input>").attr({
							type: "text",
							value: e["content"],
							id: "updateText" + e["no"]
						}).addClass("form-control"))						
					).append($("<td>")
						.append($(this).off().click(function(){
							commentUpdate(e["member"]["no"], e["no"], $("#updateText" + e["no"]).val());
						})))
						.hide().show('slow'));
				})).append(btn.clone().text("삭제").addClass("mt-2 mt-md-0").click(function(){
					commentDelete(e["member"]["no"], e["no"]);
				}))	
			}
			
			
		});

		if(isScroll) {
			$(document).scrollTop($(document).height());	
		}
	});
}

function commentDelete(memberNo, commentNo) {
	$.ajax({
	    url: 'commentDelete.do',
	    type: 'post',
	    dataType: 'json',
	    data: {
			memberNo: memberNo,
			commentNo: commentNo
		}
	}).done(function(data) {
		commentList(false);
	});
}

function commentUpdate(memberNo, commentNo, content) {
	$.ajax({
	    url: 'commentUpdate.do',
	    type: 'post',
	    dataType: 'json',
	    data: {
			memberNo: memberNo,
			commentNo: commentNo,
			content: content
		}
	}).done(function(data) {
		commentList(false);
	});
}

$(document).ready(function(){
	commentList(false);
});

</script>
<jsp:include page="../footer.jsp"></jsp:include>