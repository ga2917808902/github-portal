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
<style type="text/css">
img {
	height: 45px;
	width: 60px;
}
</style>
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
			<br />
			<p>Sách được thêm gần đây</p>
			<table border="1">
				<tr>
					<th>Tên</th>
					<th>Giá</th>
					<th>Ảnh</th>
				</tr>
				<c:forEach var="book" items="${recentlyBooks }">
					<tr>
						<td>${book.name }</td>
						<td>${book.price }</td>
						<c:forEach var="image" items="${book.imageBook }">
							<c:if test="${image.main == 1 }">
								<td><img src="../resources/image/product/${image.name }"></img></td>
							</c:if>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</article>
		<footer>FOOTER</footer>
	</div>
</body>
</html>