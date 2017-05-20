<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>PCMC</title>
<link href="./static/assets/css/reset.css" type="text/css" rel="stylesheet" />
<link href="./static/assets/css/screen.css" type="text/css" rel="stylesheet" />
<link href="./static/assets/css/thickbox.css" type="text/css" rel="stylesheet"
	media="screen" />
<link href="./static/assets/css/bootstrap.min.css" rel="stylesheet">
<link href="./static/assets/css/nav-bar.css" rel="stylesheet">
</head>
<body>
	<tiles:insertAttribute name="header" />
	<div class="mainWrapper">
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
	</div>
	<script src="./static/assets/js/jquery-3.1.0.min.js"></script>
	<script src="./static/assets/js/bootstrap.min.js"></script>
</body>
</html>
