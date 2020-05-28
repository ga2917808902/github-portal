<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
<script type="text/javascript" src="resources/js/jquery-3.4.1.min.js"></script>
<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="../resources/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="../resources/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="../resources/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet" href="../resources/css/nice-select.css"
	type="text/css">
<link rel="stylesheet" href="../resources/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet" href="../resources/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="../resources/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet" href="../resources/css/style.css" type="text/css">
</head>
<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header Section Begin -->
	<header class="header">
		<jsp:include page="header.jsp" />
	</header>
	<!-- Header Section End -->

	<!-- Hero Section Begin -->
	<section class="hero hero-normal">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="hero__categories">
						<div class="hero__categories__all">
							<i class="fa fa-bars"></i> <span>THƯ MỤC</span>
						</div>
						<ul style="display: none;">
							<c:forEach var="item" items="${listCategories }">
								<li><a href="/contents/${item[1] }&${item[0] }"><b>${item[1]}</b>
										(${item[2] })</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col-lg-9">
					<div class="hero__search">
						<div class="hero__search__form">
							<form action="search" method="get">
								<div class="hero__search__categories">
									All Categories <span class="arrow_carrot-down"></span>
								</div>
								<input type="text" name="q" placeholder="What do yo u need?">
								<button type="submit" class="site-btn">TÌM KIẾM</button>
							</form>
						</div>
						<div class="hero__search__phone">
							<div class="hero__search__phone__icon">
								<i class="fa fa-phone"></i>
							</div>
							<div class="hero__search__phone__text">
								<h5>+0902.914.892</h5>
								<span>Hỗ trợ 24/7</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Hero Section End -->
	<!-- Breadcrumb Section Begin -->
	<section class="breadcrumb-section set-bg"
		data-setbg="../resources/image/product/breadcrumb.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
						<h2>${book.name }</h2>
						<div class="breadcrumb__option">
							<a href="">Trang chủ</a> <a
								href="/contents/${book.branch.category.name }&${book.branch.category.id }">${book.branch.name }</a>
							<span>${book.name }</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Product Details Section Begin -->
	<section class="product-details spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6">
					<div class="product__details__pic">
						<c:forEach var="image" items="${book.imageBook }">
							<c:choose>
								<c:when test="${image.main == 1 }">
									<div class="product__details__pic__item">
										<img class="product__details__pic__item--large"
											src="../resources/image/product/${image.name }" alt="">
									</div>
								</c:when>
							</c:choose>
						</c:forEach>
						<div class="product__details__pic__slider owl-carousel">
							<c:forEach var="image" items="${book.imageBook }">
								<c:if test="${image.main == 0 }">
									<img data-imgbigurl="../resources/image/product/${image.name }"
										src="../resources/image/product/${image.name }" alt="">
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6">
					<div class="product__details__text">
						<h3>${book.name }</h3>
						<div class="product__details__rating">
							<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star-half-o"></i> <span>(18 reviews)</span> <span>Lượt
								xem: ${views }</span>
						</div>
						<div class="product__details__price">${book.price }</div>
						<p>${book.note }.</p>
						<a href="/review/${book.source}&${book.id}" class="primary-btn">REVIEW SÁCH</a> <a href="#"
							class="heart-icon"><span class="icon_heart_alt"></span></a>
						<ul>
							<li><b>Tải sách</b> <span><a href="${book.link }">Tải</a></span></li>
							<li><b>Review</b> <span><a
									href="/review/${book.source}&${book.id}">Review</a></span></li>
							<li><b>Gửi đường dẫn</b> <span><a href="updating">Gửi
										link tải đến bạn bè (Email)</a></span></li>
							<li><b>Share on</b>
								<div class="share">
									<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
										class="fa fa-twitter"></i></a> <a href="#"><i
										class="fa fa-instagram"></i></a> <a href="#"><i
										class="fa fa-pinterest"></i></a>
								</div></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-12">
					<div class="product__details__tab">
						<ul class="nav nav-tabs" role="tablist">
							<li class="nav-item"><a class="nav-link active"
								data-toggle="tab" href="#tabs-1" role="tab" aria-selected="true">Ghi
									chú</a></li>
							<li class="nav-item"><a class="nav-link" data-toggle="tab"
								href="#tabs-2" role="tab" aria-selected="false">Thông tin</a></li>
							<li class="nav-item"><a class="nav-link" data-toggle="tab"
								href="#tabs-3" role="tab" aria-selected="false">Bình luận <span>(${totalComment })</span></a>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="tabs-1" role="tabpanel">
								<div class="product__details__tab__desc">
									<h6>Thông tin sản phẩm</h6>
									<p>${book.note }</p>
								</div>
							</div>
							<div class="tab-pane" id="tabs-2" role="tabpanel">
								<div class="product__details__tab__desc">
									<h6>Thông tin sản phẩm</h6>
									<p>Tính năng đang được cập nhật...</p>
								</div>
							</div>
							<div class="tab-pane" id="tabs-3" role="tabpanel">
								<div class="product__details__tab__desc">
									<c:forEach var="cm" items="${listComments }">
										<b>Người lạ</b>
										<br />
											${cm.content }
											(Thời gian ${cm.updatedAt })
											<button onClick="editComment('${cm.id}');">Chỉnh sửa</button>
										<a href="/delete-comment/${cm.id }">Xóa bình luận</a>
										<br />
									</c:forEach>

									<hr />
									<form:form action="comment" modelAttribute="comment"
										method="post">
										<form:hidden path="id" />
										<form:textarea path="content" />
										<input type="hidden" name="book" value="${id }" />
										<form:hidden path="createdAt" />
										<form:hidden path="updatedAt" />
										<button>Đăng</button>
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Product Details Section End -->
	</section>

	<!-- Related Product Section Begin -->
	<section class="related-product">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title related__product__title">
						<h2>Sản phẩm liên quan</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<c:forEach var="book" items="${recommendBooks }">
					<div class="col-lg-3 col-md-4 col-sm-6">
						<div class="product__item">
							<c:forEach var="image" items="${book.imageBook }">
								<div class="product__item__pic set-bg"
									data-setbg="../resources/image/product/${image.name }">
									<ul class="product__item__pic__hover">
										<li><a href="#"><i class="fa fa-heart"></i></a></li>
										<li><a href="#"><i class="fa fa-retweet"></i></a></li>
										<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
									</ul>
								</div>
								<div class="product__item__text">
									<h6>
										<a href="/${book.name }&${book.id}&${book.branch.id }"
											class="text">${book.name }</a>
									</h6>
									<h5>${book.price }</h5>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- Related Product Section End -->
	<footer class="footer spad">
		<jsp:include page="footer.jsp" />
	</footer>
	<script>
		function editComment(id) {
			var url = "/edit-comment/" + id;
			$.getJSON(url, function(data) {
				$("#id").val(data.id);
				$("#content").val(data.content);
			});
		}
	</script>

	<!-- Js Plugins -->
	<script src="../resources/js/jquery-3.3.1.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
	<script src="../resources/js/jquery.nice-select.min.js"></script>
	<script src="../resources/js/jquery-ui.min.js"></script>
	<script src="../resources/js/jquery.slicknav.js"></script>
	<script src="../resources/js/mixitup.min.js"></script>
	<script src="../resources/js/owl.carousel.min.js"></script>
	<script src="../resources/js/main.js"></script>

</body>
</html>