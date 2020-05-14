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
			<jsp:include page="../menu.jsp" />
		</nav>
		<aside>
			<br />
			<c:forEach var="item" items="${listBranchs}">
				<a href="/contents/${item[0] }/${item[1] }">${item[1] }</a>(${item[2] })<br />
				<br />
			</c:forEach>
		</aside>
		<article>
			<table border="1">
				<thead>
					<tr>
						<th>Tên</th>
						<th>Giá</th>
						<th colspan="2">Tác giả</th>
						<th>Hình ảnh</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${listBooks }">
						<tr>
							<td>${book.name }</td>
							<td>${book.price }</td>
							<c:choose>
								<c:when test="${book.bookAuthor.size() <= 2}">
									<c:forEach var="author" items="${book.bookAuthor }">
										<td>${author.author.name }</td>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<td>Nhiều tác giả</td>
								</c:otherwise>
							</c:choose>
							<c:forEach var="image" items="${book.imageBook }">
								<c:if test="${image.main == 1 }">
									<td>${image.name }</td>
								</c:if>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</article>
		<footer>FOOTER</footer>
	</div>
</body>
</html>