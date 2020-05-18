<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/bc6916a687.js"></script>
<script type="text/javascript" src="resources/js/jquery-3.4.1.min.js"></script>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" type="text/css"
	href="../resources/css/layout.css" />
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<header>
			<h1>Portal</h1>
		</header>
		<nav>
			<jsp:include page="menu.jsp" />
		</nav>
		<aside>
			<br />
			<c:forEach var="item" items="${listCategories }">
				<a href="/contents/${item[0] }"><b>${item[1]}</b></a>(${item[2] })
				<br />
				<br />
			</c:forEach>
		</aside>
		<article>
			
		</article>
		<footer>FOOTER</footer>
	</div>
</body>
</html>