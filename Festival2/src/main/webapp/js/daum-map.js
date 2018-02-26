//$(document).ready(function() {
//$('#roadViewModal').on('shown.bs.modal', function (e) {
//    
//	// set the video src to autoplay and not to show related video. Youtube related video is like a box of chocolates... you never know what you're gonna get
//	$("#roadView").attr('src',"https://www.google.com/maps/embed/v1/streetview?key=AIzaSyB8udQCILI2T17BKFwcSldQWGKiTw0bWU8&location=46.414382,10.013988&heading=210&pitch=10&fov=35" ); 
//	})
//	// stop playing the youtube video when I close the modal
//	$('#roadViewModal').on('hide.bs.modal', function (e) {
//	    // a poor man's stop video
//	    $("#roadView").attr('src',""); 
//});
//});
var map_info = {
	map_datas: {x:'', y:'', map:'' },	
	
	detail_map: function (mapy,mapx){
		this.map_datas.x = mapx;
		this.map_datas.y = mapy;
		var mapContainer = document.getElementById('contact-map'), // 지도를 표시할 div 
		mapOption = { 
		    center: new daum.maps.LatLng(mapy, mapx), // 지도의 중심좌표
		    level: 3 // 지도의 확대 레벨
		};
	
		this.map_datas.map = new daum.maps.Map(mapContainer, mapOption);
		
		//마커가 표시될 위치입니다 
		var markerPosition  = new daum.maps.LatLng(mapy, mapx); 
		//마커를 생성합니다
		var marker = new daum.maps.Marker({
			position: markerPosition
		});
		//마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(this.map_datas.map);
	
		var iwContent = '<div style="padding:5px;">festival! <br><a href="http://map.daum.net/link/map/festival!,'+mapy+','+ mapx+'" style="color:blue" target="_blank">큰지도보기</a> | <a href="http://map.daum.net/link/to/festival!,'+mapy+','+mapx+'" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
		iwPosition = new daum.maps.LatLng(mapy, mapx); //인포윈도우 표시 위치입니다
	
		//인포윈도우를 생성합니다
		var infowindow = new daum.maps.InfoWindow({
			position : iwPosition, 
			content : iwContent 
		});
	
		//마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
		infowindow.open(this.map_datas.map, marker);
	},
	
	setMapType:function (maptype) { 
		var roadmapControl = document.getElementById('btnRoadmap');
		var skyviewControl = document.getElementById('btnSkyview'); 
		if (maptype === 'roadmap') {
			this.map_datas.map.setMapTypeId(daum.maps.MapTypeId.ROADMAP);    
			roadmapControl.className = 'selected_view_btn';
			skyviewControl.className = 'view_btn';
		} else {
			this.map_datas.map.setMapTypeId(daum.maps.MapTypeId.HYBRID);    
			skyviewControl.className = 'selected_view_btn';
			roadmapControl.className = 'view_btn';
		}
	},
	
	//지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
	zoomIn:function zoomIn() {
		this.map_datas.map.setLevel(this.map_datas.map.getLevel() - 1);
	},
	
	// 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
	zoomOut:function zoomOut() {
		this.map_datas.map.setLevel(this.map_datas.map.getLevel() + 1);
	},
	
	roadView:function (){
		return "https://www.google.com/maps/embed/v1/streetview?key=AIzaSyB8udQCILI2T17BKFwcSldQWGKiTw0bWU8&location="+this.map_datas.y+","+this.map_datas.x+"&heading=210&pitch=10&fov=35";
	}
}
