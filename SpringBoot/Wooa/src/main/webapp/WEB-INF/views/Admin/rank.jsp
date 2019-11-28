<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp"></jsp:include>
<jsp:include page="header.jsp"></jsp:include>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'bar' ]
	});
	google.charts.setOnLoadCallback(boardRankDrawChart);
	google.charts.setOnLoadCallback(commentRankDrawChart);

	
	function boardRankDrawChart() {
		
		var bRank = JSON.parse('${bRank}');
		console.log(bRank);
	      var data = new google.visualization.DataTable();
	      data.addColumn('string', '이름');
	      data.addColumn('number', '게시글');
	      
	      bRank.forEach(e => {
	    	    	  
	    	 var row = [e.nickName, e.posts];
	    	 data.addRow(row); 
	      });
	      
	      
		var options = {
			'title' : '최근 일주일간 게시글 많은 회원',
			legend: { position: "none" },
			height: 600
		};

		var chart = new google.charts.Bar(document
				.getElementById('boardRankChart'));

		chart.draw(data, google.charts.Bar.convertOptions(options));
	}
	
	function commentRankDrawChart() {
		
		var bRank = JSON.parse('${cRank}');
		console.log(bRank);
	      var data = new google.visualization.DataTable();
	      data.addColumn('string', '이름');
	      data.addColumn('number', '댓글');
	      
	      bRank.forEach(e => {
	    	    	  
	    	 var row = [e.nickName, e.posts];
	    	 data.addRow(row); 
	      });
	      
	      
	      
		var options = {
			title: '최근 일주일간 댓글 많은 회원',
			legend: { position: "none" },
			height: 600
			
		};

		var chart = new google.charts.Bar(document
				.getElementById('commantRankChart'));

		
		chart.draw(data, google.charts.Bar.convertOptions(options));
	}
</script>

  <div class="row mt-3 ">
    <div class="col">
      <div id="boardRankChart" class="border"></div>
    </div>
    <div class="col">
      <div id="commantRankChart" class="border"></div>
    </div>
  </div>

	

<jsp:include page="../footer.jsp"></jsp:include>