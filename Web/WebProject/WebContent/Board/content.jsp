<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${content_view.category == 0}">
		<c:set var="action" value="notice"></c:set>
	</c:when>
	<c:when test="${content_view.category == 1}">
		<c:set var="action" value="free"></c:set>
	</c:when>	
	<c:when test="${content_view.category == 2}">
		<c:set var="action" value="down"></c:set>
	</c:when>	
</c:choose>


<jsp:include page="../header.jsp"></jsp:include>
<div class="container-fluid">
	<table class="table table-hover table-striped table-dark  shadow p-3 mb-5 mt-2">
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
			<td> 내용 </td>
			<td> ${content_view.content} </td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="d-flex justify-content-end">
				<c:if test="${member.no == content_view.member.no}">
					<a class="btn btn-danger" href="modify_view.do?bId=${content_view.no}">수정</a> &nbsp;&nbsp;
					<a class="btn btn-danger" href="delete.do?bId=${content_view.no}">삭제</a> &nbsp;&nbsp;
				</c:if>
				<a class="btn btn-danger" href="${action}.do?page=<%= session.getAttribute("cpage") %>">목록보기</a> &nbsp;&nbsp;
				<a class="btn btn-danger" href="reply_view.do?bId=${content_view.no}">답변</a>
				</div>
			</td>
	
		</tr>
		
	</table>
	
	<table class="table table-hover table-striped table-dark shadow p-3 mb-5">	

		<tr>
			<td> 이름 </td>
			<td class="w-75 "> 내용 </td>
		</tr>
		
	</table>
	
	

<form>
  <div class="form-group row">
    <label for="inputEmail3" class="col-12 col-sm-2 col-xl-1 col-form-label">
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
      <input type="email" class="form-control" id="inputEmail3" placeholder="${placeholder}" ${readonly}>
    </div>
    <div class="col-12 mt-2 mt-md-0 col-md-2 col-xl-1">
    	<button type="button" class="btn btn-danger btn-block">댓글</button>
    </div>
  </div>
</form>
</div>
<jsp:include page="../footer.jsp"></jsp:include>