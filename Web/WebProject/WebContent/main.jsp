<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"></jsp:include>
<style>

.carousel-item > img {
	max-height: 800px;
	object-fit: cover;
}

</style>


<div class="bd-example">
  <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="File/main1.jpg" class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h1>${nlist[0].title}</h1>
          ${nlist[0].content} 
        </div>
      </div>
      <div class="carousel-item">
        <img src="File/main2.jpg" class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h1>${nlist[1].title}</h1>
          ${nlist[1].content} 
        </div>
      </div>
      <div class="carousel-item">
        <img src="File/main3.jpg" class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
         <h1>${nlist[2].title}</h1>
          ${nlist[2].content} 
        </div>
      </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>

<div class="container-fluid">
	
		<div class="row mt-5">
			<div class="col">
			 <div class="d-flex justify-content-center">
	 			<h1>좋아요 많은 게시물</h1>
			 </div>
				<table class="table">
					<thead>
					    <tr>
	    					<th scope="col">분류</th>
	    					<th scope="col">작성자</th>
	    					<th scope="col">제목</th>
	     					<th scope="col">좋아요</th>
	  					</tr>
	  				</thead>
	 			 	<tbody>
	 			 		<c:forEach items="${tlist}" var="thumb">
	    					<tr>
	      					<th scope="row">
	      					<c:if test="${thumb.category == 0}">공지</c:if>
	      					<c:if test="${thumb.category == 1}">자유</c:if>
	      					<c:if test="${thumb.category == 2}">자료</c:if>
	      					</th>
	      					<td>${thumb.member.nickName }</td>
	      					<td><a href="Board/content.do?no=${thumb.no}" class=text-dark>${thumb.title }</a></td>
	      					<td>${thumb.thumbUpCount }</td>
								 </tr>
						</c:forEach>
					  </tbody>
						</table>
	
			</div>
			<div class="col">
			 <div class="d-flex justify-content-center">
	 	<h1>조회수 많은 게시물</h1>
	 </div>
				<table class="table">
					<thead>
					    <tr>
	    					<th scope="col">분류</th>
	    					<th scope="col">작성자</th>
	    					<th scope="col">제목</th>
	     					<th scope="col">조회수</th>
	  					</tr>
	  				</thead>
	 			 	<tbody>	
	 			 		<c:forEach items="${hlist}" var="hit">
	    				<tr>
	      					<th scope="row">
								<c:if test="${hit.category == 0}">공지</c:if>
		      					<c:if test="${hit.category == 1}">자유</c:if>
		      					<c:if test="${hit.category == 2}">자료</c:if>
							</th>
	      					<td>${hit.member.nickName }</td>
	      					<td><a href="Board/content.do?no=${hit.no}" class=text-dark>${hit.title }</a></td>
	      					<td>${hit.hit }</td>

					    </tr>
						</c:forEach>
						</tbody>
						</table>
			</div>
		</div>
		

</div>
<jsp:include page="footer.jsp"></jsp:include>