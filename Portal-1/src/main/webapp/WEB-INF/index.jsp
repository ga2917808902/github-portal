<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/bc6916a687.js"></script>
<base href="${pageContext.servletContext.contextPath}/">
<script type="text/javascript" src="resources/js/jquery-3.4.1.min.js"></script>
<meta name="description" content="đọc sách miễn phí">
<meta name="keywords" content="sách">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ogani | Template</title>

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
		<jsp:include page="header.jsp" />
	</header>
	<!-- Header Section End -->

	<!-- Hero Section Begin -->
	<section class="hero">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="hero__categories">
						<div class="hero__categories__all">
							<i class="fa fa-bars"></i> <span>THƯ MỤC</span>
						</div>
						<ul>
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
					<div class="hero__item set-bg"
						data-setbg="../resources/image/product/banner_book.png"></div>
				</div>
			</div>
		</div>
	</section>
	<!-- Hero Section End -->

	<!-- Top product views  Section Begin -->
	<section class="categories">
		<div class="container">
			<br /> <br /> <br />
			<h5 style="color: #7fad39">
				<i>Sản phẩm mới nhất</i>
			</h5>
			<br />
			<div class="row">
				<div class="categories__slider owl-carousel">
					<c:forEach var="book" items="${recentlyBooks }">
						<div class="col-lg-3">
							<c:forEach var="image" items="${book.imageBook }">
								<c:if test="${image.main == 1 }">
									<div class="categories__item set-bg"
										data-setbg=""><img src="../resources/image/product/${image.name }" width="210px" height="210px"/></div>
								</c:if>
							</c:forEach>
							<p>
								<a href="${book.name }&${book.id}&${book.branch.id }"
									class="text">${book.name }</a>
							</p>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</section>
	<!-- Categories Section End -->

	<!-- Featured Section Begin -->
	<section class="featured spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title">
						<h2>SẢN PHẨM NỔI BẬT</h2>
					</div>
					<div class="featured__controls">
						<ul>
							<li class="active"><a href="">Nhiều lượt truy cập</a></li>
							<c:forEach var="item" items="${topFourRankViews }">
								<li><a href="rank-views-category?q=${item.name }">${item.name }</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="row featured__filter">
				<c:forEach var="book" items="${topViews }">
					<div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat">
						<div class="featured__item">
							<c:forEach var="image" items="${book.imageBook }">
								<c:if test="${image.main == 1 }">
									<div class="featured__item__pic set-bg"
										data-setbg="../resources/image/product/${image.name }">
										<ul class="featured__item__pic__hover">
											<li><a href="#"><i class="fa fa-heart"></i></a></li>
											<li><a href="#"><i class="fa fa-retweet"></i></a></li>
											<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
										</ul>
									</div>
								</c:if>
							</c:forEach>
							<div class="featured__item__text">
								<h6>
									<a href="${book.name }&${book.id}&${book.branch.id }"
										class="text">${book.name }</a>
								</h6>
								<h5>${book.price }</h5>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- Featured Section End -->

	<!-- Banner Begin -->
	<div class="banner">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="banner__pic">
						<img src="../resources/image/product/banner-book2305.jpg"
							width="1200px" height="180px">
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="banner__pic">
						<img src="../resources/image/product/banner-book2305-1.jpg"
							width="1200px" height="180px">
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Banner End -->

	<!-- Latest Product Section Begin -->
	<section class="latest-product spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title">
						<h2>SẢN PHẨM DÀNH CHO BẠN</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 col-md-6">
					<div class="latest-product__text">
						<h4>Sản phẩm mới nhất</h4>
						<div class="latest-product__slider owl-carousel">
							<div class="latest-prdouct__slider__item">
								<c:forEach begin="${begin1 }" end="${end1 }"
									items="${recentlyBooks }" var="book">
									<c:forEach var="image" items="${book.imageBook }">
										<c:if test="${image.main == 1 }">
											<a href="${book.name }&${book.id}&${book.branch.id }" class="latest-product__item">
												<div class="latest-product__item__pic">
													<img src="../resources/image/product/${image.name }">
												</div>
												<div class="latest-product__item__text">
													<p class="text">${book.name }</p>
													<span>${book.price }</span>
												</div>
											</a>
										</c:if>
									</c:forEach>
								</c:forEach>
							</div>
							<div class="latest-prdouct__slider__item">
								<c:forEach begin="${begin2 }" end="${end2 }"
									items="${recentlyBooks }" var="book">
									<c:forEach var="image" items="${book.imageBook }">
										<c:if test="${image.main == 1 }">
											<a href="#" class="latest-product__item">
												<div class="latest-product__item__pic">
													<img src="../resources/image/product/${image.name }">
												</div>
												<div class="latest-product__item__text">
													<p class="text">${book.name }</p>
													<span>${book.price }</span>
												</div>
											</a>

										</c:if>
									</c:forEach>
								</c:forEach>
							</div>
							<div class="latest-prdouct__slider__item">
								<c:forEach begin="${begin3 }" end="${end3 }"
									items="${recentlyBooks }" var="book">
									<c:forEach var="image" items="${book.imageBook }">
										<c:if test="${image.main == 1 }">
											<a href="#" class="latest-product__item">
												<div class="latest-product__item__pic">
													<img src="../resources/image/product/${image.name }">
												</div>
												<div class="latest-product__item__text">
													<p class="text">${book.name }</p>
													<span>${book.price }</span>
												</div>
											</a>

										</c:if>
									</c:forEach>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="latest-product__text">
						<h4>Sản phẩm phổ biến</h4>
						<div class="latest-product__slider owl-carousel">
							<div class="latest-prdouct__slider__item">
								<c:forEach begin="${begin1 }" end="${end1 }"
									items="${recentlyBooks }" var="book">
									<c:forEach var="image" items="${book.imageBook }">
										<c:if test="${image.main == 1 }">
											<a href="#" class="latest-product__item">
												<div class="latest-product__item__pic">
													<img src="../resources/image/product/${image.name }">
												</div>
												<div class="latest-product__item__text">
													<p class="text">${book.name }</p>
													<span>${book.price }</span>
												</div>
											</a>
										</c:if>
									</c:forEach>
								</c:forEach>
							</div>
							<div class="latest-prdouct__slider__item">
								<c:forEach begin="${begin2 }" end="${end2 }"
									items="${recentlyBooks }" var="book">
									<c:forEach var="image" items="${book.imageBook }">
										<c:if test="${image.main == 1 }">
											<a href="#" class="latest-product__item">
												<div class="latest-product__item__pic">
													<img src="../resources/image/product/${image.name }">
												</div>
												<div class="latest-product__item__text">
													<p class="text">${book.name }</p>
													<span>${book.price }</span>
												</div>
											</a>

										</c:if>
									</c:forEach>
								</c:forEach>
							</div>
							<div class="latest-prdouct__slider__item">
								<c:forEach begin="${begin3 }" end="${end3 }"
									items="${recentlyBooks }" var="book">
									<c:forEach var="image" items="${book.imageBook }">
										<c:if test="${image.main == 1 }">
											<a href="#" class="latest-product__item">
												<div class="latest-product__item__pic">
													<img src="../resources/image/product/${image.name }">
												</div>
												<div class="latest-product__item__text">
													<p class="text">${book.name }</p>
													<span>${book.price }</span>
												</div>
											</a>

										</c:if>
									</c:forEach>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="latest-product__text">
						<h4>Sản phẩm nổi bật</h4>
						<div class="latest-product__slider owl-carousel">
							<div class="latest-prdouct__slider__item">
								<c:forEach begin="${begin1 }" end="${end1 }"
									items="${recentlyBooks }" var="book">
									<c:forEach var="image" items="${book.imageBook }">
										<c:if test="${image.main == 1 }">
											<a href="#" class="latest-product__item">
												<div class="latest-product__item__pic">
													<img src="../resources/image/product/${image.name }">
												</div>
												<div class="latest-product__item__text">
													<p class="text">${book.name }</p>
													<span>${book.price }</span>
												</div>
											</a>
										</c:if>
									</c:forEach>
								</c:forEach>
							</div>
							<div class="latest-prdouct__slider__item">
								<c:forEach begin="${begin2 }" end="${end2 }"
									items="${recentlyBooks }" var="book">
									<c:forEach var="image" items="${book.imageBook }">
										<c:if test="${image.main == 1 }">
											<a href="#" class="latest-product__item">
												<div class="latest-product__item__pic">
													<img src="../resources/image/product/${image.name }">
												</div>
												<div class="latest-product__item__text">
													<p>${book.name }</p>
													<span>${book.price }</span>
												</div>
											</a>

										</c:if>
									</c:forEach>
								</c:forEach>
							</div>
							<div class="latest-prdouct__slider__item">
								<c:forEach begin="${begin3 }" end="${end3 }"
									items="${recentlyBooks }" var="book">
									<c:forEach var="image" items="${book.imageBook }">
										<c:if test="${image.main == 1 }">
											<a href="#" class="latest-product__item">
												<div class="latest-product__item__pic">
													<img src="../resources/image/product/${image.name }">
												</div>
												<div class="latest-product__item__text">
													<p class="text">${book.name }</p>
													<span>${book.price }</span>
												</div>
											</a>

										</c:if>
									</c:forEach>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Latest Product Section End -->

	<!-- Blog Section Begin -->
	<section class="from-blog spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title from-blog__title">
						<h2>BLOG</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-6">
					<div class="blog__item">
						<div class="blog__item__pic">
							<img src="../resources/image/product/book-blog-1.jpg"
								height="230px" width="500px">
						</div>
						<div class="blog__item__text">
							<ul>
								<li><i class="fa fa-calendar-o"></i> May 4,2019</li>
								<li><i class="fa fa-comment-o"></i> 5</li>
							</ul>
							<h5>
								<a href="#">Đọc sách là một kỹ năng?</a>
							</h5>
							<p>Theo bạn thì đọc sách có phải cần có 1 kỹ năng...</p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6">
					<div class="blog__item">
						<div class="blog__item__pic">
							<img src="../resources/image/product/book-blog-2.jpg"
								height="230px" width="500px">
						</div>
						<div class="blog__item__text">
							<ul>
								<li><i class="fa fa-calendar-o"></i> May 10,2019</li>
								<li><i class="fa fa-comment-o"></i> 5</li>
							</ul>
							<h5>
								<a href="#">Lợi ích về việc đọc sách là gì?</a>
							</h5>
							<p>Với thời buổi hiện nay việc đọc sách là điều khá xa lạ với
								giởi trẻ...</p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6">
					<div class="blog__item">
						<div class="blog__item__pic">
							<img src="../resources/image/product/book-blog-3.jpg"
								height="230px" width="500px">
						</div>
						<div class="blog__item__text">
							<ul>
								<li><i class="fa fa-calendar-o"></i> May 8,2019</li>
								<li><i class="fa fa-comment-o"></i> 5</li>
							</ul>
							<h5>
								<a href="#" class="text">Sách nào tốt khi bắt đầu học lập
									trình?</a>
							</h5>
							<p>Lập trình thường sẽ có rất nhiều ngôn ngữ lập trình như
								Java, PHP, C#...</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<!-- Blog Section End -->
	<footer class="footer spad">
		<jsp:include page="footer.jsp" />
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