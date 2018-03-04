<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
						<li><a href="notice">Notice</a></li>
					</ul>
					<h1 class="white-text">Notice</h1>

				</div>
			</div>
		</div>

	</div>
	<!-- /Hero-area -->
<h3>공지글 상세</h3>
<br>
<table border="1" width="60%" bordercolor="#BDBDBD">
<tr><th width="100px" class="center">제목</th>
	<td class="left" colspan="5">${detail.n_title }</td></tr>
<tr><th class="center">작성자</th>
	<td class="left">${detail.n_writer }</td>
	<th width="100px" class="center">작성일자</th>
	<td width="120px" class="center">${detail.n_writedate }</td>
	<th width="100px" class="center">조회수</th>
	<td width="70px">${detail.n_readcnt }</td>
</tr>
<tr><td>내용</td>
<td colspan="5" style="height: 100px;" class="left">${detail.n_content }</td>
</tr>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!empty detail.f_name }">
<tr><td>첨부파일</td>
	<td class="left" colspan="5">${detail.f_name }
		<input type="button" value="다운로드" onclick="$('#download').submit()"/>
	</td>
</tr>
</c:if>
</table>
<br>


<!-- 관리자로 로그인 되어 있는 경우 수정/삭제 권한 -->

<c:if test="${login_info.admin eq 1 }">
<img class="click" src="imgs/btn_modify.png" onclick="location='modify.no?id=${detail.n_id}'"/>
<img class="click" src="imgs/btn_delete.png" 
	onclick="if(confirm('[${detail.n_title}] 삭제하시겠습니까?')) {location='delete.no?id=${detail.n_id}'}"/>
</c:if>	
<img class="click" src="imgs/btn_list.png" onclick="$('#detail').submit()"/>

<form id="detail" method="post" action="list.no">
	<input type="hidden" name="curPage" value="${page.curPage }"/>
	<input type="hidden" name="search" value="${page.search }"/>
	<input type="hidden" name="keyword" value="${page.keyword }"/>
</form>

<form id="download" method="post" action="download.no">
	<input type="hidden" name="filepath" value="${detail.f_path }"/>
	<input type="hidden" name="filename" value="${detail.f_name }"/>
</form>

</body>
</html>










