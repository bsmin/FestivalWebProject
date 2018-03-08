<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/needs_check.js"></script>
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
						<li><a href="list.no">Board</a></li>
					</ul>
					<h1 class="white-text">Board</h1>

				</div>
			</div>
		</div>

	</div>
	<!-- /Hero-area -->

<br>
<br>
<br>
<br>
<br>
<br>
<!-- 파일업로드처리를 할 수 있도록 파일태그를 전송하기 위해서는 
반드시 
1.form 태그에 enctype='multipart/form-data' 속성지정  → 파일 업로드하기위새너는 enctype='multipart/form-data' 필수
2.method='post'로 지정
 -->
<form method="post" action="insert.no" enctype='multipart/form-data'>
<table border="1" width="40%" bordercolor="#BDBDBD">
<tr><th width="100px" class="center">제목</th>
	<td class="left" ><input type="text" name="n_title" class="needs" title="제목" size="73" style="color:black;"/></td>
</tr>
<tr><th class="center">작성자</th>
	<td class="left">${login_info.name }</td>
</tr>
<tr><td class="center">내용</td>
	<td class="left"><textarea rows="15" name="n_content" cols="74" class="need" title="내용" style="height: 200px;"></textarea></td>
</tr>
<tr><td class="center">첨부파일</td>
	<td class="left"><input type="file" name="file"/></td>
</tr>
</table>
<br>
<img src="imgs/btn_save.png" class="click" onclick="if( necessary() ){ submit() }"/>
<img src="imgs/btn_list.png" class="click" onclick="location='list.no'"/>
</form>

</body>
</html>