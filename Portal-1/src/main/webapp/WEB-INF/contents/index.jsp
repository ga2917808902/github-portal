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
				<a href="#">${item[1] }</a>(${item[2] })<br/><br/>
			</c:forEach>
		</aside>
		<article>
			<table border="1">
				<thead>
					<tr>
						<th>Tên</th>
						<th>Hình</th>
						<th>Giá</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${listBooks }">
						<tr>
							<td>${book.name }</td>
							<td>${book.imageBook.name}</td>
							<td>${book.price }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</article>
		<footer>FOOTER</footer>
	</div>
</body>
</html>