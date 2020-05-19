<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" type="text/css"
	href="../resources/css/layout.css" />
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
			<jsp:include page="menu.jsp" />
		</nav>
		<article>
			<table border="1">
				<tr>
					<th>Tên</th>
					<th>Giá</th>
					<th>Lượt truy cập</th>
					<th>Ảnh</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
				<tr>
					<td>${book.name }</td>
					<td>${book.price }</td>
					<td>${book.views }</td>
					<c:forEach var="image" items="${book.imageBook }">
						<td><img src="../resources/image/product/${image.name }"></img></td>
					</c:forEach>
					<td><a href="${book.link }">Download</a></td>
					<td><a href="updating">Preview</a></td>
					<td><a href="updating">Send link download to Friend(Email)</a></td>
				</tr>
			</table>
			<br/>
			
			<table border="1">
				<tr>
					<td>Bình luận bởi</td>
					<td>Nội dung</td>
					<td>Thời gian đăng</td>
				</tr>
				<c:forEach var="cm" items="${listComments }">
					<tr>
						<td>Người lạ</td>
						<td>${cm.content }</td>
						<td>${cm.updatedAt }</td>
						<td><a href="/edit-comment/${cm.id }">Chỉnh sửa</a></td>
						<td><a href="/delete-comment/${cm.id }">Xóa bình luận</a></td>
					</tr>
				</c:forEach>
			</table>
			
			<br/>
			<form:form action="comment" modelAttribute="comment" method="post">
				<form:hidden path="id"/>
				<form:textarea path="content"/>
				<input type="hidden" name="book" value="${id }"/>
				<form:hidden path="createdAt"/>
				<form:hidden path="updatedAt"/>
				<button>Đăng</button>
			</form:form>
		</article>
		<footer>FOOTER</footer>
	</div>
</body>
</html>