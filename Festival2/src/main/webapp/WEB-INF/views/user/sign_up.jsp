<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="js/join_validate.js"></script>
<script type="text/javascript">
function validate( tag, pwd ){
	var result = $("[name="+tag+ "]").val();
	if( tag=="id" ){
		result = join_check.id_status( result );
	}else if( tag=="pwd" ){
		result = join_check.pwd_status( result );
	}else if( tag=="pwd_ck" ){
		result = join_check.pwd_check( result, pwd );
	}else if( tag=="birth" ){
		result = join_check.birth_status( result );
	}else if( tag=="email" ){
		result = join_check.email_status( result );
	}

	$("#"+tag+"_status").removeClass("valid").removeClass("invalid");
	$("#"+tag+"_status").addClass(result.code=='valid' ? 'valid' : 'invalid');
	$("#"+tag+"_status").text(result.desc);
	
	return result;
}

function after( date ){
	if( date > new Date() ){
		return [false];
	}else{
		return [true];
	}
}

$( function(){
	$("[name=birth]").datepicker({
		dateFormat: "yy-mm-dd",
		changeYear: true,
		changeMonth: true,
		monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		beforeShowDay: after
	});
	
} );
function duplicate( id ){
	var result = validate( "id" );
	if( result.code !="valid" ) {
		alert( "중복확인 불가: " + result.desc );
		return;
	}
// 	alert( "중복확인 가능");
	$.ajax({
		type: 'post',
		data: { id:  id },
		url: 'id_check',
		success: function(data){
			var result = join_check.id_usable( data );
			$("#id_status").addClass( result.code=="usable" ? "valid" : "invalid" );
			$("#id_status").text( result.desc );
			$("#id_check").val( result.code );
		},
		error: function(request, status){
			alert( "아이디 중복확인 실패! : "+ request.status );
		}
	});
}
</script>
</head>
<body>
<h3>회원가입</h3>
<form method="post" action="join" id="member">
<table border="1" width="60%">
<tr><td width="20%">* 성명</td>
		<td><input type="text" name="name" size="20"/></td>
</tr>
<tr><td>* 아이디</td>
		<td><input onkeyup="$('#id_check').val('n'); validate('id')" type="text" name="id" size="20"/>
			<input onclick="duplicate( $('[name=id]').val() )" class="click" type="button" value="중복확인"/>
			<div class="valid" id="id_status">아이디를 입력하세요(영문소문자, 숫자만 입력)</div>
		</td>
</tr>
<tr><td>* 비밀번호</td>
		<td><input onkeyup="validate('pwd')" type="password" name="pwd" size="20"/>
			<div class="valid" id="pwd_status">비밀번호를 입력하세요(영문 대/소문자, 숫자를 모두 포함)</div>
		</td>
</tr>
<tr><td>* 비밀번호확인</td>
		<td><input onkeyup="validate('pwd_ck', pwd.value)" type="password" name="pwd_ck" size="20"/>
			<div class="valid" id="pwd_ck_status">비밀번호를 다시 입력하세요</div>
		</td> 
</tr>
<tr><td>* 성별</td>
		<td><input type="radio" name="gender" value="남" checked/>남
				<input type="radio" name="gender"  value="여" />여</td>
</tr>
<tr><td>* 생년월일</td>
		<td><input onchange="validate('birth')"  type="text" name="birth"/>
			<div class="valid"  id="birth_status">생년월일을 입력하세요</div>
		</td>
</tr>
<tr><td>* 이메일</td>
		<td><input onkeyup="validate('email')"  type="text" name="email" size="40"/>
			<div class="valid" id="email_status">이메일을 입력하세요</div>
		</td>
</tr>
<tr><td>연락처</td>
		<td><input type="text" name="phone" size="4" maxlength="3"/>
		- <input type="text" name="phone" size="4"  maxlength="4" />
		- <input type="text" name="phone" size="4"  maxlength="4" />
		</td>
</tr>
<tr><td>주소</td>
		<td><input onclick="daum_post()" type="button" value="우편번호 찾기"/>
			<input type="text" name="post" size="5"/><br>
			<input type="text" name="address" size="50"/><br>
			<input type="text" name="address" size="50"/>
		</td>
</tr>

</table>
<br>
<img onclick="submit()" class="click" src="imgs/btn_join.png" />
<img class="click" src="imgs/btn_cancel.png" onclick="history.go(-1)"/>
<!-- 아이디중복확인여부를 관리할 태그 -->
<input type="hidden" id="id_check" value="n"/>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
function go_submit(){
	
	if( $.trim($("[name=name]").val())=="" ){
		alert( "성명을 입력하세요!" );
		$("[name=name]").val("");
		$("[name=name]").focus();
		return;
	}
	var result;
	
	// 아이디 중복확인 하지 않은 경우
	if( $("#id_check").val() =="n" ){
		result = validate( "id" );
		if( result.code !="valid" ){
			alert( result.desc );
			$("[name=id]").focus();
		}else{
			alert( join_check.result.id.valid.desc );
		}
		return;
	}else{
	// 아이디 중복확인을 한 경우
		if( $("#id_check").val()=="unusable" ){
			alert( join_check.result.id.unusable.desc );
			$("[name=id]").focus();
			return;
		}
	}

	result = validate( "pwd" );
	if( result.code !="valid" ){
		alert( result.desc );
		$("[name=pwd]").focus();
		$("[name=pwd]").val("");
		return;
	}
	
	result = validate( "pwd_ck", $("[name=pwd]").val() );
	if( result.code !="valid" ){
		alert( result.desc );
		$("[name=pwd_ck]").focus();
		$("[name=pwd_ck]").val("");
		return;
	}
	
	result = validate( "email" );
	if( result.code !="valid" ){
		alert( result.desc );
		$("[name=email]").focus();
		return;
	}
	
	result = validate( "birth" );
	if( result.code !="valid" ){
		alert( result.desc );
		$("[name=birth]").focus();
		return;
	}
	
	//필수입력항목에 대한 유효성을 모두 판단한 후에만 form을 submit 
	$("#member").submit();
}


function daum_post(){
	new daum.Postcode({ 
		oncomplete: function(data){
			var fullAddr = "";
			var post="";
			var extra="";
// 			사용자가 선택한 주소타입(도로명/지번)에 따라 해당 주소값을 가져온다
			if( data.userSelectedType=='R'){
				fullAddr = data.roadAddress;
				//법정동명이 있는 경우
				if( data.bname !="" ) extra += data.bname;
				//건물명이 있는 경우
				if( data.buildingName !="" ) extra += (extra=="" ? "" : ", " )+ data.buildingName;
				if( extra !="" ) fullAddr += "("+extra +")";
	
				post = data.zonecode;
			}else{ //data.userSelectedType=='J'
				fullAddr = data.jibunAddress;
				post = data.postcode;
			}
// 			구우편번호: postcode, 신규우편번호: zonecode
			$("[name=post]").val( post );
// 			$("[name=address]").eq(0).val( fullAddr );
			$("[name=address]:eq(0)").val( fullAddr );
			$("[name=address]:eq(1)").focus();
		}
	}).open();
}
</script>

</form>
</body>
</html>



