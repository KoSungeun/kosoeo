<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
	<div id="map" style="width:auto;height:600px;"></div>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6431066fae7f218669fc25b9b26e2665"></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(37.479042, 126.878910),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
		
		var markerPosition  = new kakao.maps.LatLng(37.479042, 126.878910); 
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});
		
		marker.setMap(map);
		
		
		var iwContent = '<div class="m-3 badge badge-danger">KOSMO</div>',
	    iwPosition = new kakao.maps.LatLng(37.479042, 126.878910); //인포윈도우 표시 위치입니다
	
		var infowindow = new kakao.maps.InfoWindow({
		    position : iwPosition, 
		    content : iwContent 
		});
		  
		
		infowindow.open(map, marker); 
		
		
	</script>
	
<jsp:include page="footer.jsp"></jsp:include>