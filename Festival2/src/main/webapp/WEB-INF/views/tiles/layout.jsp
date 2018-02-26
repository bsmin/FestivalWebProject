<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Good Festival</title>
<link rel="stylesheet" type="text/css" href="css/default.css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600"
	rel="stylesheet">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

<!-- Font Awesome Icon -->
<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="css/style.css" />

<link rel="stylesheet" type="text/css" href="css/default.css">

<script src="https://code.jquery.com/jquery-1.10.1.min.js"></script>
<!-- jQuery Plugins -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>

</head>
<body>
	<form method="post" action="season" id="list">
		<input type="hidden" name="contentId" id="contentId" /> <input
			type="hidden" name="title" id="title" /> <input type="hidden"
			name="image" id="image" /> <input type="hidden" name="term"
			id="term" /> <input type="hidden" name="addr" id="addr" /> <input
			type="hidden" name="mapx" id="mapx" /> <input type="hidden"
			name="mapy" id="mapy" /> <input type="hidden" name="curPage"
			id="curPage" /> <input type="hidden" name="season" id="season"
			value="${season}" />
	</form>

	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="main" />
	<tiles:insertAttribute name="footer" />
</body>
</html>