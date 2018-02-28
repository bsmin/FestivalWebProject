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

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="css/style.css" />
<!-- Login stlylesheet -->
<link rel="stylesheet" href="css/sign.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="js/join_validate.js"></script>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
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

	function validate(tag, pwd) {
		var result = $("[name=" + tag + "]").val();
		if (tag == "id") {
			result = join_check.id_status(result);
		} else if (tag == "pwd") {
			result = join_check.pwd_status(result);
		} else if (tag == "pwd_ck") {
			result = join_check.pwd_check(result, pwd);
		} else if (tag == "birth") {
			result = join_check.birth_status(result);
		} else if (tag == "email") {
			result = join_check.email_status(result);
		}

		$("#" + tag + "_status").removeClass("valid").removeClass("invalid");
		$("#" + tag + "_status").addClass(
				result.code == 'valid' ? 'valid' : 'invalid');
		$("#" + tag + "_status").text(result.desc);

		return result;
	}

	function after(date) {
		if (date > new Date()) {
			return [ false ];
		} else {
			return [ true ];
		}
	}

	$(function() {
		$("[name=birth]").datepicker(
				{
					dateFormat : "yy-mm-dd",
					changeYear : true,
					changeMonth : true,
					monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
							'7월', '8월', '9월', '10월', '11월', '12월' ],
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
					beforeShowDay : after
				});

	});
	function duplicate(id) {
		var result = validate("id");
		if (result.code != "valid") {
			alert("중복확인 불가: " + result.desc);
			return;
		}
		//	 	alert( "중복확인 가능");
		$.ajax({
			type : 'post',
			data : {
				id : id
			},
			url : 'id_check',
			success : function(data) {
				var result = join_check.id_usable(data);
				$("#id_status").addClass(
						result.code == "usable" ? "valid" : "invalid");
				$("#id_status").text(result.desc);
				$("#id_check").val(result.code);
			},
			error : function(request, status) {
				alert("아이디 중복확인 실패! : " + request.status);
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
							<!-- <li><a href="blog">Blog</a></li> -->
							<li><a href="board">Board</a></li>
							<li><a href="notice">Notice</a></li>
						</ul>
					</nav> <!-- /Navigation -->

				</li>
				<!-- 로그인 폼 -->
				<li style="padding-left: 20px;">
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
				</li>
				<!-- /로그인 폼 -->
			</ul>

		</div>
	</header>


	<!-- 아이디중복확인여부를 관리할 태그 -->
	<input type="hidden" id="id_check" value="n" />

	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script type="text/javascript">
						function go_submit() {

							if ($.trim($("[name=name]").val()) == "") {
								alert("성명을 입력하세요!");
								$("[name=name]").val("");
								$("[name=name]").focus();
								return;
							}
							var result;

							// 아이디 중복확인 하지 않은 경우
							if ($("#id_check").val() == "n") {
								result = validate("id");
								if (result.code != "valid") {
									alert(result.desc);
									$("[name=id]").focus();
								} else {
									alert(join_check.result.id.valid.desc);
								}
								return;
							} else {
								// 아이디 중복확인을 한 경우
								if ($("#id_check").val() == "unusable") {
									alert(join_check.result.id.unusable.desc);
									$("[name=id]").focus();
									return;
								}
							}

							result = validate("pwd");
							if (result.code != "valid") {
								alert(result.desc);
								$("[name=pwd]").focus();
								$("[name=pwd]").val("");
								return;
							}

							result = validate("pwd_ck", $("[name=pwd]").val());
							if (result.code != "valid") {
								alert(result.desc);
								$("[name=pwd_ck]").focus();
								$("[name=pwd_ck]").val("");
								return;
							}

							result = validate("email");
							if (result.code != "valid") {
								alert(result.desc);
								$("[name=email]").focus();
								return;
							}

							result = validate("birth");
							if (result.code != "valid") {
								alert(result.desc);
								$("[name=birth]").focus();
								return;
							}

							//필수입력항목에 대한 유효성을 모두 판단한 후에만 form을 submit 
							$("#member").submit();
						}

						function daum_post() {
							new daum.Postcode({
								oncomplete : function(data) {
									var fullAddr = "";
									var post = "";
									var extra = "";
									// 			사용자가 선택한 주소타입(도로명/지번)에 따라 해당 주소값을 가져온다
									if (data.userSelectedType == 'R') {
										fullAddr = data.roadAddress;
										//법정동명이 있는 경우
										if (data.bname != "")
											extra += data.bname;
										//건물명이 있는 경우
										if (data.buildingName != "")
											extra += (extra == "" ? "" : ", ")
													+ data.buildingName;
										if (extra != "")
											fullAddr += "(" + extra + ")";

										post = data.zonecode;
									} else { //data.userSelectedType=='J'
										fullAddr = data.jibunAddress;
										post = data.postcode;
									}
									// 			구우편번호: postcode, 신규우편번호: zonecode
									$("[name=post]").val(post);
									// 			$("[name=address]").eq(0).val( fullAddr );
									$("[name=address]:eq(0)").val(fullAddr);
									$("[name=address]:eq(1)").focus();
								}
							}).open();
						}
					</script>

	</form>
	</div>
	</div>
	</div>
</body>
</html>


