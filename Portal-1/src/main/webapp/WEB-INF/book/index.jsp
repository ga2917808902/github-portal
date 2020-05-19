<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.servletContext.contextPath}/">
<script type="text/javascript" src="resources/js/jquery-3.4.1.min.js"></script>
<script src="../resources/bootstrap/jquery-1.10.2.min.js"></script>
<script src="../resources/bootstrap/bootstrap.min.js"></script>
<link href="../resources/bootstrap/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="../resources/css/layout.css" />
<style>
/* Modal Content */
.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 30%;
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
		<div class="container" style="margin-top: 3%">
			<form action="book/search" method="get">
				<input type="text" name="q" />
				<button>Search</button>
			</form><br/>
			<a href="/book/new" class="btn btn-info">Thêm mới</a> <br /> <br />
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
					</tr>
				</thead>
				<c:forEach var="book" items="${listBooks.content }">
					<tr>
						<td>${book.id }</td>
						<td>${book.name }</td>
						<td><a class="btn btn-info"
							href="/image-book/view/${book.id }">Xem ảnh</a></td>
						<td><a class="btn btn-warning" href="/book/edit/${book.id }">Sửa</a></td>
						<td><a class="btn btn-danger"
							onClick="deleteBook(${book.id});">Xóa</a></td>
					</tr>

				</c:forEach>
			</table>
		</div>
		<ul>
			<li><c:forEach var="i" items="${pageNumbers}">
					<a style="color: red;" href="/book/?page=${i }">${i }</a>
				</c:forEach></li>
		</ul>

		<footer>FOOTER</footer>
	</div>

	<script>
		//delete
		function deleteBook(id) {
			$.ajax({
				type : "GET",
				url : "/book/delete/" + id,
				success : function(data) {
					window.location.href = "http://localhost:8080/book/";
				}
			})
		}
	</script>
</body>
</html>