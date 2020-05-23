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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
			</form>
			<br /> <a href="/book/new" class="btn btn-info">Thêm mới</a> <br />
			<br />
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Tên sách</th>
						<th>Lượt xem</th>
					</tr>
				</thead>
				<c:forEach var="book" items="${listBooks.content }">
					<tr>
						<td>${book.id }</td>
						<td>${book.name }</td>
						<td>${book.views }</td>
						<td><a class="btn btn-info"
							href="/image-book/view/${book.id }">Xem ảnh</a></td>
						<td><a class="btn btn-warning" href="/book/edit/${book.id }">Sửa</a></td>
						<td><a class="btn btn-danger"
							onClick="deleteBook(${book.id});">Xóa</a></td>
					</tr>

				</c:forEach>
			</table>
		</div>
		<ul class="pagination pagination">
 			<c:choose>
				<c:when test="${current == 1 }">
				</c:when>
				<c:otherwise>
					<li><a href="/book/?page=${1 }">First</a></li>
					<li><a href="/book/?page=${current - 1 }">Prev</a></li>
				</c:otherwise>
			</c:choose>

			<c:forEach begin="${begin }" end="${end }" var="i">
				<c:choose>
					<c:when test="${i == current }">
						<li class="active"><a href="/book/?page=${i }">${i }</a></li>
					</c:when>
					<c:otherwise>
						<li ><a href="/book/?page=${i }">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:choose>
				<c:when test="${current == 1 }">
					<li><a href="/book/?page=${current + 1}">Next</a></li>
					<li><a href="/book/?page=${last }">Last</a></li>
				</c:when>
				<c:when test="${current == last }">
				</c:when>
				<c:otherwise>
					<li><a href="/book/?page=${current + 1}">Next</a></li>
					<li><a href="/book/?page=${last }">Last</a></li>
				</c:otherwise>
			</c:choose>
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