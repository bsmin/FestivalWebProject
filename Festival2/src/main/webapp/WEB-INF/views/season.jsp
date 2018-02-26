<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Season</title>
</head>
<body>
	<!-- Hero-area -->
	<div class="hero-area section">

		<!-- Backgound Image -->
		<div class="bg-image bg-parallax overlay"
			style="background-image: url(./img/cta1-background.jpg)"></div>
		<!-- /Backgound Image -->

		<div class="container">
			<div class="row">
				<div class="col-md-10 col-md-offset-1 text-center">
					<ul class="hero-area-tree">
						<li><a href="index.html">Home</a></li>
						<li>Season</li>
					</ul>
					<h1 class="white-text">Season Festival</h1>

				</div>
			</div>
		</div>

	</div>
	<!-- /Hero-area -->

	<!-- Blog -->
	<div id="blog" class="section">

		<!-- container -->
		<div class="container">

			<!-- row -->
			<div class="row">

				<!-- main blog -->
				<div id="main" class="col-md-9">

					<!-- row -->
					<div class="row">

						<c:forEach items="${dto.listItem}" var="dto1">

							<!-- single blog -->
							<div class="col-md-6">
								<div class="single-blog">
									<div class="blog-img">
										<a onclick="go_detail(  '${dto1.contentId}','${dto1.title}','${dto1.image}','${dto1.term}','${dto1.addr1}','${dto1.mapx}','${dto1.mapy}','${dto.curPage}'   )"><img src="${dto1.image}" alt=""></a>
									</div>
									<h4>
										<a onclick="go_detail(  '${dto1.contentId}','${dto1.title}','${dto1.image}','${dto1.term}','${dto1.addr1}','${dto1.mapx}','${dto1.mapy}','${dto.curPage}'   )"><p id="title">${dto1.title}</p></a>
									</h4>
									<div class="blog-meta">
										<span class="blog-meta-author">By: <a href="#">administrator</a></span>
										<div class="pull-right">
											<span>${dto1.term }</span> <span class="blog-meta-comments"><a
												href="#"><i class="fa fa-eye"></i> ${dto1.readcount}</a></span>
										</div>
									</div>
								</div>
							</div>
							<!-- /single blog -->

						</c:forEach>
					</div>
					<!-- /row -->



					<!-- row -->
					<div class="row">

						<!-- pagination -->
						<div class="col-md-12">
							<div class="post-pagination">
								<c:if test="${ dto.curBlock gt 1  }">
									<!-- 		이전블럭의 마지막페이지인 경우: page.beignPage-1 -->
									<!-- 		이전블럭의 첫페이지인 경우:  page.beginPage-page.blockPage -->
									<a href="#" onclick="go_page(1)"
										class="pagination-back pull-left">First</a>
									<a href="#" onclick="go_page( ${dto.beginPage-1 } )" class="pagination-back pull-left">Back</a>
								</c:if>
								<ul class="pages">
									<c:forEach var="page" begin="${dto.beginPage }" end="${dto.endPage }" step="1">
										<li <c:if test="${page eq dto.curPage }">class="active"</c:if>
											onclick="<c:if test='${page ne dto.curPage }'>go_page( ${page } ) </c:if>">${page}</li>
									</c:forEach>
								</ul>

								<c:if test="${dto.curBlock lt  dto.totalBlock }">
									<a href="#" onclick="go_page(${dto.totalPage})"
										class="pagination-next pull-right">End</a>
									<a href="#" onclick="go_page( ${dto.endPage+1})"
										class="pagination-next pull-right">Next</a>
								</c:if>
							</div>
						</div>
						<!-- pagination -->

					</div>
					<!-- /row -->
				</div>
				<!-- /main blog -->

				<!-- aside blog -->
				<div id="aside" class="col-md-3">

					<!-- search widget -->
					<div class="widget search-widget">
						<form>
							<input class="input" type="text" name="search">
							<button>
								<i class="fa fa-search"></i>
							</button>
						</form>
					</div>
					<!-- /search widget -->

					<!-- category widget -->
					<div class="widget category-widget">
						<h3>Categories</h3>
						<a class="category" href="#" onclick="go_season( 1 )" <c:if test="${season eq 1 }">id="active"</c:if>>봄<span>spring</span></a>
						<a class="category" href="#" onclick="go_season( 2 )" <c:if test="${season eq 2 }">id="active"</c:if>>여름 <span>summer</span></a>
						<a class="category" href="#" onclick="go_season( 3 )" <c:if test="${season eq 3 }">id="active"</c:if>>가을 <span>autumn</span></a>
						<a class="category" href="#" onclick="go_season( 4 )" <c:if test="${season eq 4 }">id="active"</c:if>>겨울 <span>winter</span></a>
					</div>
					<!-- /category widget -->

					<!-- posts widget -->
					<div class="widget posts-widget">
						<h3>Recommendation of the festival</h3>
						<c:forEach items="${dto.listItem}" var="dto1" begin="0" end="3"
							step="1">
							<!-- single posts -->
							<div class="single-post">
								<a class="single-post-img" onclick="go_detail(  '${dto1.contentId}','${dto1.title}','${dto1.image}','${dto1.term}','${dto1.addr1}','${dto1.mapx}','${dto1.mapy}','${dto.curPage}'   )">
									<img src="${dto1.image}" alt=""> </a> <a onclick="go_detail(  '${dto1.contentId}','${dto1.title}','${dto1.image}','${dto1.term}','${dto1.addr1}','${dto1.mapx}','${dto1.mapy}','${dto.curPage}'   )">${dto1.title}
								</a>
								<p>
									<small>${dto1.term }</small>
								</p>
							</div>
							<!-- /single posts -->
						</c:forEach>
					</div>
					<!-- /posts widget -->

					<!-- tags widget -->
					<div class="widget tags-widget">
						<h3>Tags</h3>
						<c:forEach items="${dto.listItem}" var="dto1" begin="0" end="7"
							step="1">
							<a class="tag" onclick="go_detail(  '${dto1.contentId}','${dto1.title}','${dto1.image}','${dto1.term}','${dto1.addr1}','${dto1.mapx}','${dto1.mapy}','${dto.curPage}'   )">${dto1.title}</a>
						</c:forEach>
					</div>
					<!-- /tags widget -->

				</div>
				<!-- /aside blog -->

			</div>
			<!-- row -->

		</div>
		<!-- container -->

	</div>
	<!-- /Blog -->

</body>
</html>