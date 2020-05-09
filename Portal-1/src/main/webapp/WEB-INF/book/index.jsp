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
		<div class="modal fade" id="MyModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<a href="#" class="close" data-dismiss="modal">&times;</a>
						<h4 id="ModalTitle"></h4>
					</div>
					<div class="modal-body">
						<form:form action="book/save" method="post"
							modelAttribute="book" id="form">
							<fieldset id="SubmitForm">
								<label>ID</label>
								<form:input path="id" readonly="true" />
								<div class="form-group">
									<label>Tên sách</label>
									<form:input path="name" />
								</div>
								<div class="form-group">
									<label>Giá</label>
									<form:input path="price" />
								</div>
								<div class="form-group">
									<label>Ghi chú</label>
									<form:textarea path="note" />
								</div>
								<div class="form-group">
									<label>Nhà xuất bản</label>
									<form:select path="publishing" items="${publishings }" itemLabel="name" itemValue="id"/>
								</div>
								<div class="form-group">
									<label>Ngành</label>
									<form:select path="branch" items="${branchs }" itemLabel="name" itemValue="id"/>
								</div>
								<div class="form-group">
									<label>Bìa</label>
									<form:select path="bookCover" items="${bookCovers }" itemLabel="name" itemValue="id"/>
								</div>
								<div class="form-group">
									<label>Ngôn ngữ</label>
									<form:select path="language" items="${languages }" itemLabel="name" itemValue="id"/>
								</div>
								<div class="form-group">
									<button class="btn btn-block btn-danger" id="save">
										Save</button>
								</div>
							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		<div class="container" style="margin-top: 2%">
			<button class="btn btn-info" onclick="newBook();">Thêm mới</button>
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
		//mở popup
		function newBook() {
			document.getElementById("form").reset();
			$("#ModalTitle").html("Thêm mới sách");
			$("#MyModal").modal();
		}

		//edit
		function editBook(id) {
			$("#ModalTitle").html("Cập nhật sách");
			$("#MyModal").modal();
			var url = "/book/edit/" + id;
			$.getJSON(url, function(data) {
				$("#id").val(data.id);
				$("#name").val(data.name);
				$("#price").val(data.price);
				$("#note").val(data.note);
			});
		}

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
							str += '<button onClick="editBook(' + data + ')" class="btn btn-warning">Sửa</button>' + ' ';
							str += '<button onClick="deleteBook(' + data + ')" class="btn btn-danger">Xóa</button>';
							return str;
						}	
					},
				]
			});
		});
	</script>
</body>
</html>