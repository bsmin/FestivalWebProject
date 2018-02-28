<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>공지글 안내</h3>
<br>
<table border="1" width="80%">
<tr><th width="100px">제목</th>
	<td class="left" colspan="5">${detail.title }</td></tr>
<tr><th>작성자</th>
	<td>${detail.writer }</td>
	<th width="100px">작성일자</th>
	<td width="120px">${detail.writedate }</td>
	<th width="100px">조회수</th>
	<td width="70px">${detail.readcnt }</td>
</tr>
<tr><td>내용</td>
<td colspan="5">${detail.content }</td>
</tr>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!empty detail.filename }">
<tr><td>첨부파일</td>
	<td class="left" colspan="5">${detail.filename }
		<input type="button" value="다운로드" onclick="$('#download').submit()"/>
	</td>
</tr>
</c:if>
</table>
<br>


<!-- 관리자로 로그인 되어 있는 경우 수정/삭제 권한 -->

<c:if test="${login_info.admin eq 1 }">
<img class="click" src="imgs/btn_modify.png" onclick="location='modify.no?id=${detail.id}'"/>
<img class="click" src="imgs/btn_delete.png" 
	onclick="if(confirm('[${detail.title}] 삭제하시겠습니까?')) {location='delete.no?id=${detail.id}'}"/>
</c:if>	
<img class="click" src="imgs/btn_list.png" onclick="$('#detail').submit()"/>

<form id="detail" method="post" action="list.no">
	<input type="hidden" name="curPage" value="${page.curPage }"/>
	<input type="hidden" name="search" value="${page.search }"/>
	<input type="hidden" name="keyword" value="${page.keyword }"/>
</form>

<form id="download" method="post" action="download.no">
	<input type="hidden" name="filepath" value="${detail.filepath }"/>
	<input type="hidden" name="filename" value="${detail.filename }"/>
</form>








</body>
</html>










