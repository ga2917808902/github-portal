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
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
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
		<br />
		<div class="container" style="margin-top: 2%">
			<a class="btn btn-info" href="/book/new">Thêm mới</a>
			<br /> <br />
			<div style="width: 1200px">
				<table id="myTable" border="1">
					<thead>
						<tr>
							<th>Tên sách</th>
							<th>Giá</th>
							<th>Nhà xuất bản</th>
							<th>Ngành</th>
							<th>Bìa</th>
							<th>Ngôn ngữ</th>
							<th>Ngày tạo</th>
							<th>Ngày cập nhật</th>
							<th></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>

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
		
		//Datatable
		$(document).ready(function(){
			var data = eval('${listBooks}');
			$('#myTable').DataTable({
				aaData : data,
				aoColumns : [
					{data: "name"},
					{data: "price"},
					{data: "publishing.name"},
					{data: "branch.name"},
					{data: "bookCover.name"},
					{data: "language.name"},
					{data: "createdAt"},
					{data: "updatedAt"},
					{
						data: "id", 
						mRender: function(data){
							var str = '';
							str += '<a href="/image-book/view/' + data + '" class="btn btn-info">Xem ảnh</a>' + ' ';
							str += '<a href="/book/edit/' + data + '" class="btn btn-warning">Sửa</a>' + ' ';
							str += '<a onClick="deleteBook(' + data + ')" class="btn btn-danger">Xóa</a>';
							return str;
						}	
					},
				]
			});
		});
	</script>
</body>
</html>