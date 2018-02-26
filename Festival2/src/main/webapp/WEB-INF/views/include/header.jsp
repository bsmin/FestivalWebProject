<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>HTML Education Template</title>

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

<title>Good Festival</title>
<style type="text/css">
#header_login {
	float: right;
	padding-top: 5px;
}
.needs {
	background-color: gray;
	color: white;
}
</style>

<script src="js/needs_check.js"></script>
<script type="text/javascript">
	function go_logout() {
		$.ajax({
			type : 'post',
			url : "logout",
			success : function() {
				location.reload();
			},
			error : function(request, status) {
				alert("로그아웃 실패! : " + request.status);
			}
		});
	}

	function go_login() {
		if (!necessary())
			return;

		$.ajax({
			type : 'post',
			data : {
				id : $("#login_id").val(),
				pwd : $("#login_pwd").val()
			},
			url : "login",
			success : function(data) {
				if (data == "fail") {
					alert("아이디나 비밀번호가 일치하지 않습니다!");
					$("#login_id").val("");
					$("#login_pwd").val("");
					$("#login_id").focus();
				} else {
					location.reload();
				}
			},
			error : function(request, status) {
				alert("로그인 실패! : " + request.status);
			}
		});
	}
</script>
<style type="text/css">
/* 	.containter > ul > li { float:  right !important;} */
</style>
</head>
<body>
	<!-- Header -->
	<header id="header" class="transparent-nav">
		<div class="container">

			<div class="navbar-header">
				<!-- Logo -->
				<div class="navbar-brand">
					<a onclick="location='index'" class="click"><h3>A Good
							Festival</h3></a>
				</div>
				<!-- /Logo -->

				<!-- Mobile toggle -->
				<button class="navbar-toggle">
					<span></span>
				</button>
				<!-- /Mobile toggle -->
			</div>

			<ul>
				<li style="padding-left: 20px;">
					<!-- Navigation -->
					<nav id="nav">
						<ul class="main-menu nav navbar-nav navbar-right">
							<li><a href="index">Home</a></li>
							<li><a href="season">Season</a></li>
							<li><a href="blog">Blog</a></li>
							<li><a href="notice">Notice</a></li>
							<li><a href="contact">Contact</a></li>
						</ul>
					</nav> <!-- /Navigation -->

				</li>
				<li style="padding-left: 20px;">
					<!-- UserLogin -->
					<div id="header_login">
						<!-- 로그인상태 -->
						<c:if test="${ !empty login_info }">
							<ul>
								<li>${login_info.id }[${login_info.name }]&nbsp;&nbsp;</li>
								<li><img onclick="go_logout()" class="click"
									src="imgs/btn_logout.png" /></li>
							</ul>
						</c:if>

						<!-- 로그아웃상태 -->
						<c:if test="${ empty login_info }">
							<table>
								<tr>
									<td><input class="needs" title="아이디" size="8" type="text"
										id="login_id" placeholder="아이디" /></td>
									<td><input class="needs" title="비밀번호"
										onkeypress="if(event.keyCode==13) go_login()" size="8"
										type="password" id="login_pwd" placeholder="비밀번호" /></td>
									<td><Button onclick="go_login()" class="click">로그인</Button></td>
									<td><Button onclick="location='member'" class="click">회원가입</Button></td>
								</tr>
							</table>
						</c:if>
					</div> 
					<!-- /UserLogin -->
				</li>
			</ul>
		</div>
	</header>
</body>
</html>


