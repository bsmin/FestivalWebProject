<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 수정</title>
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
						<li><a href="notice">Board</a></li>
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

<form method="post" action="update.no" enctype="multipart/form-data">
<input type="hidden" name="n_id" value="${dto.n_id }"/>
<table border="1" width="60%" bordercolor="#BDBDBD">
<tr><th width="100px" class="center">제목</th>
	<td class="left"><input value="${dto.n_title }" type="text" name="n_title" 
		class="needs" title="제목" size="73"  style="color:black;"/></td>
</tr>
<tr><th class="center">작성자</th>
	<td class="left">${dto.n_writer }</td>
</tr>
<tr><td >내용</td>
	<td class="left"><textarea rows="15" name="n_content" cols="74" style="height: 200px;" 
		class="need" title="내용">${dto.n_content }</textarea></td>
</tr>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<tr><td>첨부파일</td>
	<td class="left"><original>${dto.f_name }</original>
	<input type="file" name="file" onchange="file_change(this)"/>
	<c:if test="${!empty dto.f_name}">
	<input type="button" value="파일삭제" 
			onclick="$('original').html(''); $('#delete').css('display','none'); "/>
	</c:if>
	</td>
<!-- 	display:block/none, visibility:hidden/visible -->
	
</tr>
</table>
<br>

<script type="text/javascript">
function file_change(attach) {
	if($(attach).val()!=""){
		$("original").html("");
		 $('#delete').css('display','block');
	}else{
		$("original").html("${dto.f_name}");
	}	
}
</script>


<img src="imgs/btn_save.png" class="click" onclick="if( necessary() ) {  $('[name=f_name]').val($('original').html());  submit()  }"/>
<img src="imgs/btn_cancel.png" onclick="history.go(-1)"/>
<img src="imgs/btn_list.png" onclick="$('form').attr('action','list.no'); submit()"/>

<input type="hidden" name="curPage" value="${page.curPage }"/>
<input type="hidden" name="search" value="${page.search }"/>
<input type="hidden" name="keyword" value="${page.keyword }"/>
<input type="hidden" name="f_name" />
<input type="hidden" name="f_path"  value="${dto.f_path }"/>

</form>
</body>
</html>