<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/bc6916a687.js"></script>
<script type="text/javascript" src="../resources/js/jquery-3.4.1.min.js"></script>
<base href="${pageContext.servletContext.contextPath}/">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
<title>Insert title here</title>
</head>
<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header Section Begin -->
	<header class="header">
		<jsp:include page="../header.jsp" />
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
								<input type="text" name="q"
									placeholder="Bạn cần tìm sản phẩm nào?">
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
							<a href="">Trang chủ</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Product Section Begin -->
	<section class="product spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-5">
					<div class="sidebar">
						<div class="sidebar__item">
							<h4>Mục lục</h4>
							<ul>
								<c:forEach var="item" items="${listBranchs }">
									<li><a href="/contents/${item[1] }/${item[0] }">${item[1] }
											(${item[2] })</a></li>
								</c:forEach>
							</ul>
						</div>
						<div class="sidebar__item">
							<h4>Mức giá</h4>
							<div class="price-range-wrap">
								<div
									class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
									data-min="0" data-max="5000000">
									<div class="ui-slider-range ui-corner-all ui-widget-header"></div>
									<span tabindex="0"
										class="ui-slider-handle ui-corner-all ui-state-default"></span>
									<span tabindex="0"
										class="ui-slider-handle ui-corner-all ui-state-default"></span>
								</div>
								<div class="range-slider">
									<div class="price-input">
										<input type="text" id="minamount"> <input type="text"
											id="maxamount">
									</div>
								</div>
							</div>
						</div>
						<div class="sidebar__item sidebar__item__color--option">
							<h4>Colors</h4>
							<div class="sidebar__item__color sidebar__item__color--white">
								<label for="white"> White <input type="radio" id="white">
								</label>
							</div>
							<div class="sidebar__item__color sidebar__item__color--gray">
								<label for="gray"> Gray <input type="radio" id="gray">
								</label>
							</div>
							<div class="sidebar__item__color sidebar__item__color--red">
								<label for="red"> Red <input type="radio" id="red">
								</label>
							</div>
							<div class="sidebar__item__color sidebar__item__color--black">
								<label for="black"> Black <input type="radio" id="black">
								</label>
							</div>
							<div class="sidebar__item__color sidebar__item__color--blue">
								<label for="blue"> Blue <input type="radio" id="blue">
								</label>
							</div>
							<div class="sidebar__item__color sidebar__item__color--green">
								<label for="green"> Green <input type="radio" id="green">
								</label>
							</div>
						</div>
						<div class="sidebar__item">
							<h4>Từ khóa thông dụng</h4>
							<div class="sidebar__item__size">
								<label for="large"> Large <input type="radio" id="large">
								</label>
							</div>
							<div class="sidebar__item__size">
								<label for="medium"> Medium <input type="radio"
									id="medium">
								</label>
							</div>
							<div class="sidebar__item__size">
								<label for="small"> Small <input type="radio" id="small">
								</label>
							</div>
							<div class="sidebar__item__size">
								<label for="tiny"> Tiny <input type="radio" id="tiny">
								</label>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-9 col-md-7">
					<div class="filter__item">
						<div class="row">
							<div class="col-lg-4 col-md-5">
								<div class="filter__sort"></div>
							</div>
							<div class="col-lg-4 col-md-4">
								<div class="filter__found">
									<h6>
										<span>${totalItems }</span> Kết quả được tìm thấy
									</h6>
									<h6>
										<span>${time }</span>milliseconds
									</h6>
								</div>
							</div>
							<div class="col-lg-4 col-md-3">
								<div class="filter__option">
									<span class="icon_grid-2x2"></span> <span class="icon_ul"></span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<c:forEach var="book" items="${listBooks.content }">
							<div class="col-lg-4 col-md-6 col-sm-6">
								<div class="product__item">
									<c:forEach var="image" items="${book.imageBook }">
										<c:if test="${image.main == 1 }">
											<div class="product__item__pic set-bg"
												data-setbg="../resources/image/product/${image.name }">
												<ul class="product__item__pic__hover">
													<li><a href="#"><i class="fa fa-heart"></i></a></li>
													<li><a href="#"><i class="fa fa-retweet"></i></a></li>
													<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
												</ul>
											</div>
										</c:if>
									</c:forEach>
									<div class="product__item__text">
										<h6>
											<a href="/${book.name }&${book.id }&${book.branch.id }"
												class="text">${book.name }</a>
										</h6>
										<h5>${book.price }</h5>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="product__pagination">
						<ul class="pagination pagination">
							<c:if test="${totalItems > 0 }">
								<c:choose>
									<c:when test="${current == 1 }">
									</c:when>
									<c:otherwise>
										<li><a href="/${cUrl }?page=${1 }">First</a></li>
										<li><a href="/${cUrl }?page=${current - 1 }">Prev</a></li>
									</c:otherwise>
								</c:choose>

								<c:forEach begin="${begin }" end="${end }" var="i">
									<c:choose>
										<c:when test="${i == current }">
											<li class="active"><a href="/${cUrl }?page=${i }">${i }</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="/${cUrl }?page=${i }">${i}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${current == 1 && totalItems > 12}">
										<li><a href="/${cUrl }?page=${current + 1}">Next</a></li>
										<li><a href="/${cUrl }?page=${last }">Last</a></li>
									</c:when>
									<c:when test="${current == last }">
									</c:when>
									<c:otherwise>
										<li><a href="/${cUrl }?page=${current + 1}">Next</a></li>
										<li><a href="/${cUrl }?page=${last }">Last</a></li>
									</c:otherwise>
								</c:choose>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Product Section End -->

	<footer class="footer spad">
		<jsp:include page="../footer.jsp" />
	</footer>

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