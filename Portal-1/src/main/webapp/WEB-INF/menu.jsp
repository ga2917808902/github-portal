<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" type="text/css"
	href="../resources/css/dropdown.css" />
<title>Insert title here</title>
</head>
<body>
	<div class="dropdown">
		<a><span>Quản lý</span></a>
		<div class="dropdown-content">
			<a href="/book/">Sách</a> <br /> <a href="/category/">Loại</a> <br />
			<a href="/book-cover/">Trang bìa</a><br /> <a href="/publishing/">Nhà
				xuất bản</a><br /> <a href="/branch/">Chuyên ngành</a><br /> <a
				href="/author/">Tác giả</a><br /> <a href="/lang/">Ngôn ngữ</a>
		</div>
	</div>
</body>
</html>