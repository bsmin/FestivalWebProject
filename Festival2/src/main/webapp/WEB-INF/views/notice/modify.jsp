<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지글 안내 변경</title>
<script src="js/needs_check.js"></script>

</head>
<body>
<h3>공지글 안내 변경</h3>

<form method="post" action="update.no" enctype="multipart/form-data">
<input type="hidden" name="id" value="${dto.id }"/>
<table border="1" width="80%">
<tr><th width="100px">제목</th>
	<td class="left"><input value="${dto.title }" type="text" name="title" 
		class="needs" title="제목" size="73"/></td>
</tr>
<tr><th>작성자</th>
	<td class="left">${dto.writer }</td>
</tr>
<tr><td>내용</td>
	<td class="left"><textarea rows="15" name="content" cols="74" 
		class="needs" title="내용">${dto.title }</textarea></td>
</tr>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<tr><td>첨부파일</td>
	<td class="left"><original>${dto.filename }</original>
	<input type="file" name="file" onchange="file_change(this)"/>
	<c:if test="${!empty dto.filename}">
	<input type="button" value="파일삭제" 
			onclick="$('original').html(''); $('#delete').css('display','none')"/>
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
		$("original").html("${dto.filename}");
	}	
}
</script>


<img src="imgs/btn_save.png" onclick="if(necessary()) {submit()}"/>
<img src="imgs/btn_cancel.png" onclick="history.go(-1)"/>
<img src="imgs/btn_list.png" onclick="$('form').attr('action','list.no'); submit()"/>

<input type="hidden" name="curPage" value="${page.curPage }"/>
<input type="hidden" name="search" value="${page.search }"/>
<input type="hidden" name="keyword" value="${page.keyword }"/>

</form>
</body>
</html>