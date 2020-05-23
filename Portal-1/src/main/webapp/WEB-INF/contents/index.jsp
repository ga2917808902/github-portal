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
<link href="../resources/bootstrap/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="../resources/css/layout.css" />
<title>Insert title here</title>
<style type="text/css">
img {
	height: 45px;
	width: 60px;
}
</style>
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
			<div class="container" style="margin-top: 3%">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Tên</th>
							<th>Giá</th>
							<th>Ảnh</th>
						</tr>
					</thead>
					<c:forEach var="book" items="${listBooks.content }">
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
									<td><img src="../resources/image/product/${image.name }"></img></td>
								</c:if>
							</c:forEach>
							<td><a href="${book.name }">Chi tiết</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<ul class="pagination pagination">
				<c:choose>
					<c:when test="${current == 1 }">
					</c:when>
					<c:otherwise>
						<li><a href="/contents/${id }/?page=${i }">First</a></li>
						<li><a href="/contents/${id }/?page=${current - 1 }">Prev</a></li>
					</c:otherwise>
				</c:choose>

				<c:forEach begin="${begin }" end="${end }" var="i">
					<c:choose>
						<c:when test="${i == current }">
							<li class="active"><a href="/contents/${id }/?page=${i }">${i }</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/contents/${id }/?page=${i }">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${current == 1 }">
						<li><a href="/contents/${id }/?page=${current + 1}">Next</a></li>
						<li><a href="/contents/${id }/?page=${last }">Last</a></li>
					</c:when>
					<c:when test="${current == last }">
					</c:when>
					<c:otherwise>
						<li><a href="/contents/${id }/?page=${current + 1}">Next</a></li>
						<li><a href="/contents/${id }/?page=${last }">Last</a></li>
					</c:otherwise>
				</c:choose>
			</ul>

		</article>
		<footer>FOOTER</footer>
	</div>
</body>
</html>