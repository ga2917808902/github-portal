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
<link href="../resources/bootstrap/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="../resources/css/layout.css" />
<style type="text/css">
img {
	height: 90px;
	width: 100px;
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
		<br />
		<form:form action="image-book/save" method="post"
			modelAttribute="imageBook" enctype="multipart/form-data">
			<form:hidden path="id" readonly="true" />
			<div>
				<label>Hình ảnh</label> <input type="file" name="imageFile" /> <input
					type="hidden" name="tempImageFile"
					value="<%=request.getAttribute("image")%>" />
			</div>
			<br />
			<div>
				<label>Ảnh chính</label>
				<c:choose>
					<c:when test="${result == 0 }">
						<form:select path="main">
							<form:option value="1">Ảnh chính</form:option>
							<form:option value="0">Ảnh phụ</form:option>
						</form:select>
					</c:when>

					<c:when test="${result == 1 }">
						<form:select path="main">
							<form:option value="0">Ảnh phụ</form:option>
						</form:select>
					</c:when>

					<c:when test="${result == 2 }">
						<form:select path="main">
							<form:option value="1">Ảnh chính</form:option>
							<form:option value="0">Ảnh phụ</form:option>
						</form:select>
					</c:when>

				</c:choose>

			</div>
			<div>
				<button>Save</button>
			</div>
		</form:form>
		<footer>Footer</footer>
	</div>

</body>
</html>