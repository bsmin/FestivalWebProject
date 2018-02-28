<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${list }" var="comment" varStatus="idx">
${idx.index == 0 ? "<hr>" : ""}
<div>${comment.str_writedate }:${comment.name} [<fmt:formatDate pattern="yyyy-MM-dd kk:mm:ss" 
								value="${comment.writedate }"/> ] </div>
<!-- 						  content데이터에서 enter1을 br로 변경     enter2를 br 로변경				 -->
<div id="comment_original_${comment.id }">${ fn:replace(fn:replace(comment.content, enter1, '<br>'), enter2, '<br>') }</div>

<!-- 로그인한 회원이 작성한 글에 대해서만 수정/삭제 가능 -->
<!-- 댓글 수정화면에서는 수정.삭제버튼 → 저장.취소버튼:value 속성 변경 -->

<c:if test="${login_info.id eq comment.userid }">
<div style="display: none;" id="comment_modify_${comment.id }"></div>
<div class="right">
	<input onclick="comment_modify_save(${comment.id})" type="button" value="수정" id="btn_modify_save_${comment.id }"/>
	<input onclick="comment_delete_cancel(${comment.id})" type="button" value="삭제" id="btn_delete_cancel_${comment.id }"/>
</div>
</c:if>


<hr>
</c:forEach>
<br>
<script type="text/javascript">

//댓글 삭제
function comment_delete( id ){
	if( confirm("정말 삭제하시겠습니까?") ){
		$.ajax({
			url : "board/comments/delete/"+id, 
			success: function(){
				comment_list();
			},
			error: function(request, status){
				alert("댓글삭제 실패!: "+request.status );
			}
		});
	}
}

//취소버튼 클릭시 원래 화면으로 
function comment_delete_cancel(id) {
	if($("#btn_delete_cancel_" + id).attr("value")=="삭제"){
		comment_delete( id );
	}else{
	display_original(id); 
		
	}
}
//취소버튼 클릭시 보여지는 형태
function display_original(id) {
	$("#btn_modify_save_" + id).attr("value", "수정");
	$("#btn_delete_cancel_" + id).attr("value", "삭제");
	//댓글원본은 보이지 않게하고, 변경할 수 있는 댓글은 보이게 처리
	$("#comment_modify_" + id).css('display', 'none');
	$("#comment_original_" + id).css('display', 'block');
}

//수정/저장 버튼 클릭시 수정화면 처리
function comment_modify_save( id ) {
	//수정버튼인 경우 
	if($("#btn_modify_save_" + id).attr("value")=="수정"){
	$("#comment_modify_" + id).html(
			"<textarea id='comment_editor' style='width:500px; height:50px;'>"+
			$("#comment_original_"+id).html().replace(/<br>/g, "\n")
			+"</textarea>");
	display_modify(id);
	}else{
	//저장버튼인 경우
		comment_save(id);
		display_original(id);
	}
}
//변경저장처리
function comment_save(id) {
	//json데이터 처리
	var datas = new Object();
	datas.id = id;
	datas.content = $("#comment_editor").val();
	$.ajax({
		type:"post",
		url:'board/comments/update',
		data:JSON.stringify(datas),
		contentType:'application/json; chareset=utf-8',
		success:function(result){
			alert("댓글변경" + result);
			comment_list();
		},
		error:function(requst,status){
			alert("댓글변경실패:" + request.status);
		}
	});
}

//수정버튼 클릭시 보여지는 형태
function display_modify(id) {
	$("#btn_modify_save_" + id).attr("value", "저장");
	$("#btn_delete_cancel_" + id).attr("value", "취소");
	//댓글원본은 보이지 않게하고, 변경할 수 있는 댓글은 보이게 처리
	$("#comment_modify_" + id).css('display', 'block');
	$("#comment_original_" + id).css('display', 'none');
}
</script>

</body>
</html>