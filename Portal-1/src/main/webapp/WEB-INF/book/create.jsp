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
<title>Insert title here</title>
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
</head>
<body>
	<div class="container">
		<header>
			<h1>Portal</h1>
		</header>
		<nav>
			<jsp:include page="../menu.jsp" />
		</nav>
		<form:form action="book/save" method="post" modelAttribute="book"
			enctype="multipart/form-data">
			<form:hidden path="id" readonly="true" />
			<div>
				<label>Tên sách</label>
				<form:input path="name" />
			</div>
			<div>
				<label>Giá</label>
				<form:input path="price" />
			</div>
			<diV>
				<label>Ghi chú</label>
				<form:textarea path="note" />
			</div>
			<div>
				<label>Link</label>
				<form:input path="link" />
			</div>
			<div>
				<label>Source</label> <input type="file" name="sourceFile" />
			</div>
			<div>
				<label>Nhà xuất bản</label>
				<form:select path="publishing" items="${publishings }"
					itemLabel="name" itemValue="id" />
			</div>
			<div>
				<label>Ngành</label>
				<form:select path="branch" items="${branchs }" itemLabel="name"
					itemValue="id" />
			</div>
			<div>
				<label>Bìa</label>
				<form:select path="bookCover" items="${bookCovers }"
					itemLabel="name" itemValue="id" />
			</div>
			<div>
				<label>Ngôn ngữ</label>
				<form:select path="language" items="${languages }" itemLabel="name"
					itemValue="id" />
			</div>
			<form:hidden path="createdAt" />
			<form:hidden path="updatedAt" />

			<div>
				<label>Tác giả</label><br />
				<div style="width: 380px">
					<table id="myTable" border="1">
						<thead>
							<tr>
								<th>Tên tác giả</th>
								<th>Chọn</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div>
				<button>Save</button>
			</div>
		</form:form>
		<footer>FOOTER</footer>
	</div>
	<script>
		//mở popup
		function openPopup() {
			$("#ModalTitle").html("Add New Category");
			$("#MyModal").modal();
		}

		//Datatable author ngoài popup
		$(document)
				.ready(
						function() {
							var listData = eval('${listAuthors}');
							$('#myTable')
									.DataTable(
											{
												aaData : listData,
												aoColumns : [
														{
															data : "name"
														},
														{
															data : "id",
															mRender : function(
																	data) {
																return '<input type="checkbox" name="listIdAuthor" value="' + data + '" />';
															}
														}, ]
											});
						});
	</script>
</body>
</html>