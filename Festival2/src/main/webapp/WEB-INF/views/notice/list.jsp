<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 리스트</title>

<style type="text/css">
.sd{margin-right: }
</style>
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
<form method="post" action="list.no" id="notice_list">
<div class="search1">
<table width="40%" >
<tr>
	<td class="left">
	<select name="search" style="height: 29px; width: 80px">
		<option value="all" ${page.search eq "all" ? "selected":"" }>전체</option>
		<option value="title" ${page.search eq "title" ? "selected":"" }>제목</option>
		<option value="content" ${page.search eq "content" ? "selected":"" }>내용</option>
		<option value="writer" ${page.search eq "writer" ? "selected":"" }>작성자</option>
	</select>
	<input value="${page.keyword }" name="keyword" type="text" style="height: 23px; width: 500px"/>
	<img class="click" onclick="submit()" src="imgs/magnifier.png" style="vertical-align: bottom; margin-right: 80px;"/>	
	<!-- 관리자로 로그인되어 있는 경우 글쓰기 권한 -->
	<c:if test="${!empty login_info}">
	<img src="imgs/pencil-edit-button.png"class="click" onclick="location='new.no'"/>
 	</c:if>
	</td>
	<tr>
	<td class="right">
	
	
	</td>
</tr>
</table>
</div>
<br/>
	<input type="hidden" name="id" id="id"/>
	<input type="hidden" name="curPage" id="curPage"/>

<script type="text/javascript">
function go_detail(id) {
	
	$('#id').val(id);
	$('#curPage').val(${page.curPage});
	$('#notice_list').attr('action', 'detail.no');
	$('#notice_list').submit();
}
</script>

<table border="1" width="60%" bordercolor="#BDBDBD">
<tr><th width="70px" class="center">번호</th><th class="center">제목</th>
	<th width="70px" class="center">작성자</th>
	<th width="100px" class="center">등록일자</th>
	<th width="70px" class="center">조회수</th>
</tr>
<c:forEach items="${page.list }" var="dto">
	<tr>
		<td>${dto.no }</td>
		<td class="left"><a onclick="go_detail(${dto.n_id})">${dto.n_title }</a></td>
		<td>${dto.n_writer }</td>
		<td>${dto.n_writedate }</td>
		<td>${dto.n_readcnt }</td>
		
	</tr>
	
</c:forEach>
</table>
<br>
<jsp:include page="/WEB-INF/views/include/page.jsp"/>
</form>
</body>

</html>