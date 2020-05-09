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
	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
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
		<br />
		<div class="modal fade" id="MyModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<a href="#" class="close" data-dismiss="modal">&times;</a>
						<h4 id="ModalTitle"></h4>
					</div>
					<div class="modal-body">
						<form:form action="branch/save" method="post"
							modelAttribute="branch" id="form">
							<fieldset id="SubmitForm">
								<label>ID</label>
								<form:input path="id" readonly="true" />
								<div class="form-group">
									<label>Name</label>
									<form:input path="name" />
								</div>
								<div class="form-group">
									<label>Category</label>
									<form:select path="category" items="${categories }"
										itemLabel="name" itemValue="id" />
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
			<button class="btn btn-info" onclick="newBranch();">Thêm mới</button>
			<br /> <br />
			<div style="width: 1200px">
				<table id="myTable" border="1">
					<thead>
						<tr>
							<th>Chuyên ngành</th>
							<th>Loại sách</th>
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
		function newBranch() {
			document.getElementById("form").reset();
			$("#ModalTitle").html("Thêm mới ngành");
			$("#MyModal").modal();
		}

		//edit
		function editBranch(id) {
			$("#ModalTitle").html("Cập nhật ngành");
			$("#MyModal").modal();
			var url = "/branch/edit/" + id;
			$.getJSON(url, function(data) {
				$("#id").val(data.id);
				$("#name").val(data.name);
				$("#category").val(data.category.id);
			});
		}

		//delete
		function deleteBranch(id) {
			$.ajax({
				type : "GET",
				url : "/branch/delete/" + id,
				success : function(data) {
					window.location.href = "http://localhost:8080/branch/";
				}
			})
		}
		//Datatable
		$(document).ready(function(){
			var data = eval('${listBranchs}');
			$('#myTable').DataTable({
				aaData : data,
				aoColumns : [
					{data: "name"},
					{data: "category.name"},
					{
						data: "id", 
						mRender: function(data){
							var str = '';
							str += '<button onClick="editBranch(' + data + ')" class="btn btn-warning">Sửa</button>' + ' ';
							str += '<button onClick="deleteBranch(' + data + ')" class="btn btn-danger">Xóa</button>';
							return str;
						}	
					},
				]
			});
		});
	</script>
</body>
</html>