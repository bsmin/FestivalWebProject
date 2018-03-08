<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<title>Home</title>
</head>
<body>
	<div id="home" class="hero-area">

		<!-- Backgound Image -->
		<div class="bg-image bg-parallax overlay"
			style="background-image: url(./img/home-background.jpg)"></div>
		<!-- /Backgound Image -->

		<div class="home-wrapper">
			<div class="container">
				<div class="row">
					<div class="col-md-8">
						<h1 class="white-text">
							I believe that if life gives you lemons, you should make
							lemonade... <br />And try to find somebody whose life has given
							them vodka, and have a party.<br />-Ron White-
						</h1>
<!-- 						<p class="lead white-text"> -->
<!-- 							나는 인생이 당신에게 레몬을 주면 레모네이드를 만들어야한다고 믿습니다 ... <br />그리고 삶이 보드카를주고 -->
<!-- 							파티를 열어주는 누군가를 찾으십시오.<br />-론 화이트- -->
<!-- 						</p> -->
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- /Home -->


	<!-- About -->
	<div id="about" class="section">

		<!-- container -->
		<div class="container">

			<!-- row -->
			<div class="row">

				<div class="col-md-6">
					<div class="section-header">
						<h2>Welcome to GoodFestival</h2>
						<p class="lead">축제의 중심 GoodFestival "Good center of the
							festival."</p>
					</div>

					<!-- feature -->
					<div class="feature">
						<i class="feature-icon fa fa-flask"></i>
						<div class="feature-content">
							<h4>exact information</h4>
							<p>
								이용자에게 정확하고 신뢰있는 정보 제공<br />Accurate and reliable information is
								to users.
							</p>
						</div>
					</div>
					<!-- /feature -->

					<!-- feature -->
					<div class="feature">
						<i class="feature-icon fa fa-users"></i>
						<div class="feature-content">
							<h4>Various festivals</h4>
							<p>
								다양한 체험과 함께 즐길수 있는 축제정보로 가득합니다.<br />Various experiences and to
								enjoy the festival with full information.
							</p>
						</div>
					</div>
					<!-- /feature -->

					<!-- feature -->
					<div class="feature">
						<i class="feature-icon fa fa-comments"></i>
						<div class="feature-content">
							<h4>Community</h4>
							<p>
								이용자들간의 원활한 소통 및 교류를 할 수 있습니다.<br />Efficient communication
								between users and exchanges can do.
							</p>
						</div>
					</div>
					<!-- /feature -->

				</div>

				<div class="col-md-6">
					<div class="about-img">
						<img src="./img/about.png" alt="">
					</div>
				</div>

			</div>
			<!-- row -->

		</div>
		<!-- container -->
	</div>
	<!-- /About -->

	<!-- Courses -->
	<div id="courses" class="section">

		<!-- container -->
		<div class="container">

			<!-- row -->
			<div class="row">
				<div class="section-header text-center">
					<h2>Hot Festival</h2>
					<p class="lead">
						오늘을 즐기며 살아라.<br />Seize the day and live the day.
					</p>
				</div>
			</div>
			<!-- /row -->

			<!-- courses -->
			<div id="courses-wrapper">

				<!-- row -->
				<div class="row">
					<c:forEach items="${dto.listItem}" var="dto">
						<!-- single course -->
						<div class="col-md-3 col-sm-6 col-xs-6">
							<div class="course">
								<a onclick="go_detail( '${dto.contentId}','${dto.title}','${dto.image}','${dto.term}','${dto.addr1}','${dto.mapx}','${dto.mapy}',0  )" class="course-img">
								 	<img src="${dto.image}" alt=""> 
								 	<i class="course-link-icon fa fa-link"></i>
								</a> 
								<a class="course-title" onclick="go_detail( '${dto.contentId}','${dto.title}','${dto.image}','${dto.term}','${dto.addr1}','${dto.mapx}','${dto.mapy}',0  )">${dto.title}</a>
								<div class="course-details">
									<span class="course-term">${dto.term}</span> <span
										class="course-addr course-free"><p id="addr">${dto.addr1}</p></span>
								</div>
							</div>
						</div>
						<!-- /single course -->

					</c:forEach>
				</div>
				<!-- /row -->


			</div>
			<!-- /courses -->

			<div class="row">
				<div class="center-btn">
					<a class="main-button icon-button" href="season">More Festival</a>
				</div>
			</div>

		</div>
		<!-- container -->

	</div>
	<!-- /Courses -->

	<!-- Call To Action -->
	<div id="cta" class="section">

		<!-- Backgound Image -->
		<div class="bg-image bg-parallax overlay"
			style="background-image: url(./img/cta1-background.jpg)"></div>
		<!-- /Backgound Image -->

		<!-- container -->
		<div class="container">

			<!-- row -->
			<div class="row">

				<div class="col-md-6">
					<h2 class="white-text">Festival information for each season
						can enjoy.</h2>
					<p class="lead white-text">시즌별로 즐길수 있는 축제정보.</p>
					<a class="main-button icon-button" href="season">Get Started!</a>
				</div>

			</div>
			<!-- /row -->

		</div>
		<!-- /container -->

	</div>
	<!-- /Call To Action -->

	<!-- Why us -->
	<div id="why-us" class="section">

		<!-- container -->
		<div class="container">

			<!-- row -->
			<div class="row">
				<div class="section-header text-center">
					<h2>Why GoodFestival</h2>
					<p class="lead">
						Accurate information, for having fun with a variety of
						festivities.<br />정확한 정보, 다양한 축제를 함께 즐기기 위함이다.
					</p>
				</div>

				<!-- feature -->
				<div class="col-md-4">
					<div class="feature">
						<i class="feature-icon fa fa-flask"></i>
						<div class="feature-content">
							<h4>exact information</h4>
							<p>
								이용자에게 정확하고 신뢰있는 정보를 제공합니다.<br />Access to accurate and reliable
								information.
							</p>
						</div>
					</div>
				</div>
				<!-- /feature -->

				<!-- feature -->
				<div class="col-md-4">
					<div class="feature">
						<i class="feature-icon fa fa-users"></i>
						<div class="feature-content">
							<h4>Various festivals</h4>
							<p>
								다양한 체험과 함께 즐길수 있는 축제정보로 가득합니다.<br />Various experiences and to
								enjoy the festival with full information.
							</p>
						</div>
					</div>
				</div>
				<!-- /feature -->

				<!-- feature -->
				<div class="col-md-4">
					<div class="feature">
						<i class="feature-icon fa fa-comments"></i>
						<div class="feature-content">
							<h4>Community</h4>
							<p>
								이용자들간의 원활한 소통 및 교류를 할 수 있습니다.<br />Efficient communication
								between users and exchanges can do.
							</p>
						</div>
					</div>
				</div>
				<!-- /feature -->

			</div>
			<!-- /row -->

			<hr class="section-hr">

			<!-- row -->
			<div class="row">

				<div class="col-md-6">
					<h3>A comfortable viewing and joyous celebration.</h3>
					<p class="lead">편안한 관람 및 즐거운 축제</p>
					<p>
						항상 이용자들에게 정확하고 신뢰있는 정보와 편안하고 다양한 축제 정보를 제공하도록 노력하겠습니다.<br />Comfortable
						and various festivals and information users with accurate and all
						the confidence I will try to provide information.
					</p>
				</div>

				<div class="col-md-5 col-md-offset-1">
					<a class="about-video video-btn" data-toggle="modal"
						data-target="#myModal" data-src="./img/Concert.mp4"> <img
						src="./img/mainvideothumb.JPG" alt=""> <i
						class="play-icon fa fa-play"></i>
					</a>
				</div>

			</div>
			<!-- /row -->

			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">


						<div class="modal-body">

							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<!-- 16:9 aspect ratio -->
							<div class="embed-responsive embed-responsive-16by9">
								<iframe class="embed-responsive-item" src="" id="video"
									allowscriptaccess="always">></iframe>
							</div>


						</div>

					</div>
				</div>
			</div>
			<!--/ Modal -->
		</div>
		<!-- /container -->

	</div>
	<!-- /Why us -->

	<!-- Contact CTA -->
	<div id="contact-cta" class="section">

		<!-- Backgound Image -->
		<div class="bg-image bg-parallax overlay"
			style="background-image: url(./img/cta2-background.jpg)"></div>
		<!-- Backgound Image -->

		<!-- container -->
		<div class="container">

			<!-- row -->
			<div class="row">

				<div class="col-md-8 col-md-offset-2 text-center">
					<h2 class="white-text">Board Us</h2>
					<p class="lead white-text">
						궁금한 점은 관리자에게 문의해 주세요.<br />Please contact the administrator is
						have any questions.
					</p>
					<a class="main-button icon-button" href="board">Board Us Now</a>
				</div>

			</div>
			<!-- /row -->

		</div>
		<!-- /container -->

	</div>
	<!-- /Contact CTA -->
</body>
</html>
