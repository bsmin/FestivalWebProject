<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>게시글 안내</h3>
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
<br>
<style type="text/css">
#comment_regist div {float: left;}
</style>
<!-- 댓글목록 -->
<div id="comments" style="text-align: left; margin: 0 auto; width: 500px;">

</div>

<!-- 댓글 입력부분 -->
<div id="comment_regist" style="text-align: center; margin: 0 auto; width: 500px;">
	<div style="width: 250px;" class="left"><strong>댓글작성</strong></div>
	<div style="width: 250px;" class="right">
		<input onclick="go_comment_regist()" style="vertical-align: middle;" type="button" value="등록"/>
	</div>
	<textarea style="width: 500px; height: 100px;" name="editor" id="editor"></textarea>
</div>

<script type="text/javascript">

$(function() {
	comment_list();
});


function comment_list() {
	$.ajax({
		url:"board/comments/${detail.id}",	//어떤글인지 알아서 댓글을 달아야하기위한 설정
// 		url:"list.no", //게시판클릭시 list보이게
		success:function(result){ 		//board/comment/list.jsp 자체를 리턴받음
		$("#comments").html(result);
			
		},error:function(request, status){
			alert("댓글 목록조회 실패!" + request.status);
		}
		
	});
}


function go_comment_regist() {
	//로그인되어 있어야 댓글 등록 가능
	if( ${empty login_info} ){ 
		alert("댓글을 등록하려면 로그인 하세요!");
		return;
	}
	
	
	if($.trim($("#editor").val()) == ""){
		alert("댓글을 입력하세요!"); 
		$("#editor").val(""); //초기화
		$("#editor").focus();
		return;
	}
	
	$.ajax({
		url:"board/comments/insert", //->BoardCommentController 가 받을 준비를 해야함 
		data:{content: $("#editor").val(), pid:"${detail.id}" },
		success:function( data ){
			if(data == "success"){//데이터가 등록되었다면
				$("#editor").val("");//초기화
				alert("댓글이 등록되었습니다.");
				comment_list();
			}else{
				alert("댓글 등록 실패!");
			}
		},
		error:function(){
		}
	});
}

</script>
</body>
</html>






