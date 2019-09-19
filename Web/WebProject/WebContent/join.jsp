<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<div class=container>
<form action="main.jsp" method="post">
  <div class="form-group">
    <label for="exampleInputEmail1">이메일</label>
    <input type="email" name="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="이메일">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">패스워드</label>
    <input type="password" name="password" class="form-control" id="password" placeholder="패스워드">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">패스워드확인</label>
    <input type="password" name="passwordCheck" class="form-control" id="passwordCheck" placeholder="패스워드확인">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">이름</label>
    <input type="text" name="name" class="form-control" id="name" placeholder="이름">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">별명</label>
    <input type="text" name="nickname" class="form-control" id="nickname" placeholder="별명">
  </div>
  <button type="submit" class="btn btn-primary">가입</button>
</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>