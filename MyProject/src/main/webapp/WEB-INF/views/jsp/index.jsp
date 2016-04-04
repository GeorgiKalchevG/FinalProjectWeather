<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<link rel="icon" type="image/png" href="https://cdn4.iconfinder.com/data/icons/iconsimple-freebies/512/umbrella.png">
<title>Weather</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/base/jquery-ui.css"
	type="text/css" media="all">
	<!-- css -->
	<spring:url value="css/popup.css" var="popupCss" />

	<%-- <spring:url value="/static/js/main.js" var="mainJs" /> --%>
	
	<link href="${popupCss}" rel="stylesheet" />
  
    <%-- <script src="${mainJs}"></script> --%>
</head>

<body>
	<!-- heading start -->
	<div class="row">
		<!-- 	<div class="col-sm-12"> -->
		<nav class="navbar navbar-inverse "> <!-- <div class="container-fluid main-nav"> -->
		<div class="row">
			<div class="col-sm-6" >
				<a class="navbar-brand" href="index" style="height: 76px;"><img
					class="img-circle"
					src="https://cdn4.iconfinder.com/data/icons/iconsimple-freebies/512/umbrella.png"
					alt="Mountain View"
					style="width: 90px; height: 90px; background: yellow;"></a>
				<h1 style="color: white; padding-top: 0px">Weather</h1>
			</div>
			
			<c:if test="${empty user}">
			<div class="col-sm-4" align="right" >
				<a href="javascript:void(0)" onclick="toggle_visibility('logIn');"><button type="button" class="btn btn-info" id="login"><span class="glyphicons glyphicons-cogwheels"></span>Log In</button></a>
				<a href="javascript:void(0)" onclick="toggle_visibility('register');"><button type="button" class="btn btn-info" id="reg">Register</button></a>
			</div>
			
			</c:if>
			<c:if test="${not empty user}">
				<div class="col-sm-4" align="right" >
					<form action="editUser">
						<h3>Logged in as <input type="submit" value="${user.userName}"></h3>
					</form>
				</div>
			</c:if>
			
			
			<div class="col-sm-2" align="right" >
				<form class="form-inline" role="form" action="ChangeLanguage" method="POST">
						<button type="submit" class="btn btn-default" style="background-image: url(${flag}); background-repeat: no-repeat; background-position: center center; size: 38px;background-color: black;border-width: 0;">...</button>
				</form>
			
				<form class="form-inline" role="form" action="ChangeUnits" method="get">
					<button type="submit" class="btn btn-default" >F/C</button>
				</form>
			</div>
		</div>

	<div class="row">
		<div class="col-sm-8" style="padding-right: 0px;padding-left: 0px;"></div>
		<div class="col-sm-4"align="right" >
			<form class="form-inline" role="form" action="search" method="post">
				<input id="search-city" type="text" placeholder="search city"
					class="form-control" id="email" name="city"> <input
					name="country" type="hidden">
				<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span> Search</button>
			</form>
			<div class="col-sm-8" align="right"></div>
			<div class="col-sm-4" >
				<div class="cities">
					<ul class="search-result"></ul>
				</div>
			</div>
		</div>
	</div>

		<!-- </div> --> </nav>
		<!-- </div> -->
	</div>
	<div class="row"></div>
	<!-- heading end -->
	<!-- heading start -->
	<div class="row">
		<div class="col-sm-12">
			<nav class="navbar navbar-inverse second-nav">
			<div class="container-fluid second-nav">
				<ul class="nav navbar-nav">
					<li><span class="glyphicons glyphicons-home"></span><a href="index"><span class="glyphicons glyphicons-home"></span> Home</a></li>
					<li><a href="planner"><span class="glyphicons glyphicons-plane"></span> Plan your trip!</a></li>
					<li><a href="#"><span class="glyphicons glyphicons-globe-af"></span> World map?</a></li>
					<li><a href="shukarii"><span class="glyphicons glyphicons-ice-cream"></span> Shukarii</a></li>
				</ul>
			</div>
			</nav>
		</div>
	</div>

	<!-- heading end -->
	<div class="row">
		<div class="col-sm-12">
			<div class="city-weather">
				<c:import url="${sessionScope.page}" />
			</div>
		</div>
	</div>
	<c:import url="loginOrRegister.jsp"></c:import>
</body>
<style>
.row {
	width: 100%;
	margin: 0 auto;
}

.navbar-inverse {
	margin-bottom: 0;
}

.container-fluid.main-nav {
	line-height: 75px;
	height: 75px;
}

.cities {
	height: 0px;
	overflow: scroll;
	position: absolute;
	/* 	top: 55px; */
	/* left: 352px; */
	text-align: left;
	z-index: 2;
	background-color: white;
	line-height: 30px;
	transition-property: all;
	transition-duration: .5s;
	transition-timing-function: cubic-bezier(0, 1, 0.5, 1);
}

.cities.opened {
	overflow: hidden;
	height: 150px;
	transition-property: all;
	transition-duration: .5s;
	transition-timing-function: cubic-bezier(0, 1, 0.5, 1);
}

.search-result {
	height: 150px;
	overflow-y: scroll;
	list-style-type: none;
	padding: 5px;
}

.search-result li {
	border-bottom: 1px solid rgb(200, 200, 200);
}

.search-result li:hover {
	background: #FFFAC2;
	cursor: pointer;
}

.main-container {
	padiing: 0 15px;
}

.additional-info {
	height: 0px;
	overflow: hidden;
	transition-property: all;
	transition-duration: .5s;
	transition-timing-function: cubic-bezier(0, 1, 0.5, 1);
}

.additional-info.opened {
	height: 200px;
	overflow: hidden;
	list-style-type: none;
	padding: 5px;
	transition-property: all;
	transition-duration: .5s;
	transition-timing-function: cubic-bezier(0, 1, 0.5, 1);
}

.table th {
	width: 25%;
}
</style>
<script>
	/* $('.open-additional-info').on('click', function() {
		$('.additional-info').toggleClass('opened');
		console.log('ds');
	}); */

	$('#search-city')
			.on(
					'keyup',
					function() {
						var city = $(this).val();
						if (city.length >= 3) {
							console.log(city);
							$
									.ajax({
										url : "add",
										type : 'POST',
										data : {
											city : city,
										},
										success : function(response) {
											var response = JSON.parse(response);
											var cities = response.RESULTS;
											$('.search-result').text('');
											for ( var city in cities) {
												var name = cities[city].name;
												// Create a variable to contain the array
												var names = name.split(", ");

												var list = '<li class="searched-city" city="' + names[0] + '" country="'+ names[1] +'">'
														+ cities[city].name
														+ '</li>';
												$('.search-result')
														.append(list);
											}
											$('.cities').addClass('opened');

										},
										fail : function() {

										}
									});
						}
					});

	$('.search-result').on('click', '.searched-city', function() {
		$.ajax({
			url : "search",
			type : 'POST',
			data : {
				city : $(this).attr('city'),
				country : $(this).attr('country')
			},
			success : function(response) {
				$(".city-weather").text('');
				$(".city-weather").append(response);
				$('.cities').removeClass('opened');
			},
			fail : function() {

			}
		});

	});
	function changeLanguage() {
		$.POST("ChangeLanguage", function(result) {
		});
	}
</script>
</html>