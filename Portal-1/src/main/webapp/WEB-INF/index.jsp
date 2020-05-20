<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/bc6916a687.js"></script>
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
	<%
		Integer hitsCount = (Integer) application.getAttribute("hitCounter");
		if (hitsCount == null || hitsCount == 0) {
			/* First visit */
			hitsCount = 1;
		} else {
			/* return visit */
			hitsCount += 1;
		}
		application.setAttribute("hitCounter", hitsCount);
	%>

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
						<td><a href="${book.name }&${book.id}">Chi tiết</a></td>
					</tr>
				</c:forEach>
			</table>

			<br />
			<p>Sách được tìm kiếm nhiều nhất</p>
			<table border="1">
				<tr>
					<th>Tên</th>
					<th>Giá</th>
					<th>Ảnh</th>
				</tr>
				<c:forEach var="book" items="${topViews }">
					<tr>
						<td>${book.name }</td>
						<td>${book.price }</td>
						<c:forEach var="image" items="${book.imageBook }">
							<c:if test="${image.main == 1 }">
								<td><img src="../resources/image/product/${image.name }"></img></td>
							</c:if>
						</c:forEach>
						<td><a href="${book.name }&${book.id}">Chi tiết</a></td>
					</tr>
				</c:forEach>
			</table>
			<p>
				Total number of visits:
				<%=hitsCount%></p>

		</article>
		<footer>FOOTER</footer>
	</div>
</body>
</html>