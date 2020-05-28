<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.servletContext.contextPath}/">
<link href="../resources/bootstrap/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
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
			<jsp:include page="../menu.jsp" />
		</nav>
		<br /> <a href="/image-book/new">Thêm ảnh</a>
		<table border="1">
			<tr>
				<th>Hình ảnh</th>
				<th>Sách</th>
				<th>Phân loại</th>
			</tr>
			<c:forEach var="item" items="${listImageBooks }">
				<tr>
					<c:choose>
						<c:when test="${item.main == 2}">
							<td><img src="../resources/image/pdf/${item.name }" /></td>
						</c:when>
						<c:otherwise>
							<td><img src="../resources/image/product/${item.name }" /></td>
						</c:otherwise>
					</c:choose>
					<td>${item.book.name }</td>
					<c:choose>
						<c:when test="${item.main == 1}">
							<td>Ảnh chính</td>
						</c:when>
						<c:when test="${item.main == 0}">
							<td>Ảnh phụ</td>
						</c:when>
						<c:otherwise>
							<td>Ảnh review</td>
						</c:otherwise>
					</c:choose>
					<td><a href="/image-book/edit/${item.id }">Sửa</a></td>
					<td><a href="/image-book/delete/${item.id }">Xóa</a></td>
				</tr>
			</c:forEach>

		</table>
		<footer>Footer</footer>
	</div>

</body>
</html>