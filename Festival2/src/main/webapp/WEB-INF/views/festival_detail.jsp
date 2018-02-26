<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<title>Detail</title>
<script type="text/javascript">
$(function(){
	var list ="";
	var subevent = "${ fn:replace(fn:replace( fn:replace(dto2.subevent, enter1, ''), enter2, '' ),enter3, ',' ) }";
	var usetimefestival = "${ fn:replace(fn:replace( fn:replace(dto2.usetimefestival, enter1, ''), enter2, '' ),enter3, ',' ) }";
	var program = "${ fn:replace(fn:replace( fn:replace(dto2.program, enter1, ''), enter2, '' ),enter3, ',' ) }";
	var placeinfo = "${ fn:replace(fn:replace( fn:replace(dto2.placeinfo, enter1, ''), enter2, '' ),enter3, ',' ) }";
	var eventplace = "${ fn:replace(fn:replace( fn:replace(dto2.eventplace, enter1, ''), enter2, '' ),enter3, ',' ) }";
	var playtime = "${ fn:replace(fn:replace( fn:replace(dto2.playtime, enter1, ''), enter2, '' ),enter3, ',' ) }";
	var infos = festival_info.detail_info('${dto2.agelimit}','${dto2.discountinfofestival}','${dto2.eventhomepage}',eventplace, placeinfo,playtime,program,'${dto2.sponsor1}','${dto2.sponsor1tel}','${dto2.sponsor2}','${dto2.sponsor2tel}',subevent,usetimefestival) ;
	list += "<li><i class='fa fa-retweet'></i>${dto1.term }</li>";
	if( infos.agelimit !="" ) { list += "<li><i class='fa fa-male'></i>"+ infos.agelimit+"</li>"; }
	if( infos.discountinfofestival !="" ) { list += "<li><i class='fa fa-male'></i>"+ infos.discountinfofestival +"</li>"; }
	if( infos.usetimefestival !="" ) { list += "<li><i class='fa fa-won'></i>"+ infos.usetimefestival +"</li>"; }
	if( infos.sponsor !="" ) { list += "<li><i class='fa fa-handshake-o'></i>"+ infos.sponsor+"</li>"; }
	if( infos.sponsortel !="" ) { list += "<li><i class='fa fa-phone'></i>"+ infos.sponsor_tel+"</li>"; }
	if( infos.eventhomepage !="" ) { list += "<li><i class='fa fa-link'></i><a href='"+infos.eventhomepage+"'>"+ infos.eventhomepage+"</a></li>"; }
	if( infos.playtime !="" ) { list += "<li><i class='fa fa-clock-o'></i>"+ infos.playtime+"</li>"; }
	if( infos.program !="" ) { list += "<li><i class='fa fa-cog'></i>"+ infos.program+"</li>"; }
	if( infos.subevent !="" ) { list += "<li><i class='fa fa-cogs'></i>"+ infos.subevent+"</li>"; }
	if( infos.eventplace !="" ) { list += "<li><i class='fa fa-map-marker'></i>"+ infos.eventplace+"</li>"; }
	if( infos.placeinfo !="" ) { list += "<li><i class='fa fa-map-marker'></i>"+ infos.placeinfo+"</li>"; }
	$(".contact-details").html( list );
// 	map_info.detail_map(${dto1.mapy},${dto1.mapx});
	$(".embed-responsive-item").attr('src', map_info.roadView());
	
});

</script>

<!-- Google font -->
<link
	href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600"
	rel="stylesheet">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

<!-- Font Awesome Icon -->
<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="css/style.css" />
<!-- Gallery -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.css">
<link rel="stylesheet" href="css/festival-detail.css">
<!-- Daum Map Api -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3b2e903c6b5d0e3f26c207ac7a816138"></script>
<script type="text/javascript" src="js/daum-map.js"></script>
<script type="text/javascript" src="js/detail-festival.js"></script>
</head>
<body>
	<!-- Hero-area -->
	<div class="hero-area section">

		<!-- Backgound Image -->
		<div class="bg-image bg-parallax overlay"
			style="background-image: url(./img/default.jpg)"></div>
		<!-- /Backgound Image -->

		<div class="container">
			<div class="row">
				<div class="col-md-10 col-md-offset-1 text-center">
					<ul class="hero-area-tree">
						<li><a href="index">Home</a></li>
						<li><a href="season">Season</a></li>
						<li>Detail</li>
					</ul>
					<h1 class="white-text">Detail Information</h1>

				</div>
			</div>
		</div>

	</div>
	<!-- /Hero-area -->

	<!-- Contact -->
	<div id="contact" class="section">
		<h4>${dto1.title }</h4>
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">


				<!-- contact information -->
				<div class="col-md-6 ">
					<div class="container info-container">
						<ul class="contact-details"></ul>
						<!-- contact map -->
						<!-- map_wrap -->
						<div class="map_wrap">
							<!-- 지도타입 컨트롤 div 입니다 -->
							<div id="contact-map"></div>
							<div class="custom_typecontrol radius_border">
								<span id="btnRoadmap" class="selected_view_btn"
									onclick="map_info.setMapType('roadmap')">지도</span> <span
									id="btnSkyview" class="view_btn"
									onclick="map_info.setMapType('skyview')">스카이뷰</span>
							</div>
							<!-- 지도 확대, 축소 컨트롤 div 입니다 -->
							<div class="custom_zoomcontrol radius_border">
								<span onclick="map_info.zoomIn()"><img
									src="./img/plus.png" alt="확대"></span> <span
									onclick="map_info.zoomOut()"><img src="./img/minus.png"
									alt="축소"></span>
							</div>
							<div class="custom_roadcontrol radius_border">
								<span data-toggle="modal" data-target="#roadViewModal"><img
									src="./img/camera.png" alt="로드뷰"></span>
							</div>
							<script type="text/javascript">map_info.detail_map(${dto1.mapy},${dto1.mapx})</script>
						</div>

						<!-- /contact map -->
					</div>
				</div>
				<!-- /contact information -->


				<!-- contact information -->
				<div class="col-md-6 col-md-offset-0">

					<!-- Gallery -->
					<div class="container gallery-container">
						<div class="tz-gallery">

							<div class="row">
								<div class="col-sm-6 col-md-9">
									<a class="lightbox" href="${dto1.image}"> <img
										src="${dto1.image}">
									</a>
								</div>
								<c:forEach items="${img_list}" var="img">
									<div class="col-sm-6 col-md-3">
										<a class="lightbox" href="${img}"> <img src="${img}">
										</a>
									</div>
								</c:forEach>

							</div>
						</div>
					</div>
				</div>
				<!-- /contact information -->
			</div>
			<!-- /row -->

		</div>
		<!-- /container -->
		<!-- Map -->

		<!-- Modal -->
		<div class="modal fade" id="roadViewModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="road-modal-dialog" role="document">
				<div class="modal-content">
					<div class="road-modal-body">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<!-- 16:9 aspect ratio -->
						<div class="embed-responsive embed-responsive-16by9">
							<iframe class="embed-responsive-item" src=""></iframe>
						</div>


					</div>

				</div>
			</div>
		</div>
		<!--/ Modal -->
	</div>
	<!-- /Contact -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.js"></script>
	<script>
		baguetteBox.run('.tz-gallery');
	</script>
</body>
</html>
