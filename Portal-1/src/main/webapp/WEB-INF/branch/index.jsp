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
		<br />
		<div class="modal fade" id="MyModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<a href="#" class="close" data-dismiss="modal">&times;</a>
						<h4 id="ModalTitle"></h4>
					</div>
					<div class="modal-body">
						<form:form action="branch/save" method="post" modelAttribute="branch"
							id="form">
							<fieldset id="SubmitForm">
								<label>ID</label>
								<form:input path="id" readonly="true" />
								<div class="form-group">
									<label>Name</label>
									<form:input path="name" />
								</div>
								<div class="form-group">
									<label>Branch</label>
									<form:input path="category" />
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
		<div class="container" style="margin-top: 3%">
			<button class="btn btn-info" onclick="newBranch();">Thêm mới</button>
			<br /> <br />
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
					</tr>
				</thead>
				<c:forEach var="branch" items="${listBranchs }">
					<tr>
						<td>${branch.id }</td>
						<td>${branch.name }</td>
						<td>${branch.category.name }</td>
						<td><button class="btn btn-warning"
								onclick="editBranch(${branch.id});">Sửa</button></td>
						<td><button class="btn btn-danger"
								onclick="deleteBranch(${branch.id});">Xóa</button></td>
					</tr>

				</c:forEach>
			</table>
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
		function editBranch(id){
			$("#ModalTitle").html("Cập nhật ngành");
			$("#MyModal").modal();
			var url = "/branch/edit/" + id;
			$.getJSON(url, function(data){
				$("#id").val(data.id);
				$("#name").val(data.name);
			});
		}
		
		//delete
		function deleteBranch(id){
			$.ajax({
				type: "GET",
				url : "/branch/delete/" + id,
				success: function(data){
					window.location.href = "http://localhost:8080/branch/";
				}
			})
		}
	</script>
</body>
</html>