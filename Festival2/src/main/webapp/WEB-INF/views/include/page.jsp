<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" >
function go_page( page ){
	var form_id = "${category eq 'no' ? 'notice_list' :   (  category eq 'bo' ? 'board_list' : 'list' )  }" ;
	$("#"+form_id+ " #curPage").val( page );
	$("#"+form_id).submit();
}
</script>
</head>
<body>

<table>
<tr>
	<td>
	<c:if test="${ page.curBlock gt 1  }">
		<img src="imgs/btn_first.gif" onclick="go_page( 1 )"/>
<!-- 		이전블럭의 마지막페이지인 경우: page.beignPage-1 -->
<!-- 		이전블럭의 첫페이지인 경우:  page.beginPage-page.blockPage -->
		<img src="imgs/btn_prev.gif" onclick="go_page( ${page.beginPage-1 } )"/>
	</c:if>
	</td>
	
	<td>
		<c:forEach var="no" begin="${page.beginPage }" end="${page.endPage }" step="1">
		<div style="width: 30px; height: 25px; float: left;" 
						class="${ no eq page.curPage ? 'page' : 'click'}"
				<c:if test="${no ne page.curPage }">onclick="go_page( ${no } )"</c:if>>
			 ${no }
		</div>	
		</c:forEach>
	</td>
	
	<td>
		<c:if test="${page.curBlock lt  page.totalBlock }">
		<img src="imgs/btn_next.gif" onclick="go_page( ${page.endPage+1})"/>
		<img src="imgs/btn_last.gif" onclick="go_page( ${page.totalPage } )"/>
		</c:if>
	</td>
</tr>
</table>
</body>
</html>







