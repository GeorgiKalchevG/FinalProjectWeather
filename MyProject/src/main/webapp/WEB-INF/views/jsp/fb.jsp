<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<script>
	function statusChangeCallback(response) {
		console.log('statusChangeCallback');
		console.log(response);
		if (response.status === 'connected') {
			testAPI();
		} else if (response.status === 'not_authorized') {
			document.getElementById('status').innerHTML = 'Please log '
					+ 'into this app.';
		} else {
			console.log('Izpalnqvam kato ne sam lognat');
			document.getElementById('showOrHide').style.display = 'none';
			document.getElementById('showOrHide1').style.display = 'none';
			document.getElementById('status').innerHTML = 'Please log '
					+ 'into Facebook.';
		}
	}

	// This function is called when someone finishes with the Login
	// Button.  See the onlogin handler attached to it in the sample
	// code below.
	function checkLoginState() {
		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});
	}
	window.fbAsyncInit = function() {
		FB.init({
			appId : '262037110796419',
			cookie : true, // enable cookies to allow the server to access 
			// the session
			xfbml : true, // parse social plugins on this page
			version : 'v2.5' // use graph api version 2.5
		});

		// Now that we've initialized the JavaScript SDK, we call 
		// FB.getLoginStatus().  This function gets the state of the
		// person visiting this page and can return one of three states to
		// the callback you provide.  They can be:
		//
		// 1. Logged into your app ('connected')
		// 2. Logged into Facebook, but not your app ('not_authorized')
		// 3. Not logged into Facebook and can't tell if they are logged into
		//    your app or not.
		//
		// These three cases are handled in the callback function.

		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});

	};

	// Load the SDK asynchronously
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/en_US/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));

	// Here we run a very simple test of the Graph API after login is
	// successful.  See statusChangeCallback() for when this call is made.
	function testAPI() {
		console.log('Welcome!  Fetching your information.... ');
		FB
				.api(
						'/me',
						function(response) {
							console.log('Successful login for: '
									+ response.name);
							document.getElementById('status').innerHTML = 'Thanks for logging in, '
									+ response.name
									+ '! Please wait to fetch your events and show you the future! ';
							FB
									.api(
											'/me',
											'GET',
											{
												"fields" : "name,events.limit(5){cover,description,name,place,start_time,ticket_uri}"
											},
											function(response) {
												var rezz = JSON
														.stringify(response);
												$
														.ajax({
															url : "facebook",
															type : 'POST',
															data : {
																ivanshishman : rezz,
																kakvo : "ds"
															},
															success : function(
																	data) {
																if (data == "true") {
																	location.reload();
																}
																document.getElementById('status').innerHTML = 'Thank you for wait '
																		+ response.name
																		+ '. Here is your weather for incoming events you will be attending. ';
																document.getElementById('showOrHide').style.display = 'inline';
																document.getElementById('showOrHide1').style.display = 'inline';
															},
															fail : function() {
															}
														});
											});
						});
	}
	function logoutFromFB() {
		FB.logout(function(response) {
			$
			.ajax({
				url : "logoutFromFacebook",
				type : 'POST',
				success : function() {
					console.log('tova tuka izpalnqva li se izosbtho');
					document.getElementById('showOrHide').style.display = 'none';
					document.getElementById('showOrHide1').style.display = 'none';
					document.getElementById('status').innerHTML = 'You just logged out from Facebook';
				}
			});
				});
	}
</script>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="generator" content="Bootply" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/styles.css" rel="stylesheet">
</head>
<body>
	<div class="page-container">

		<div class="container-fluid">
			<div class="row row-offcanvas row-offcanvas-left">
				<!--sidebar-->
				<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar"
					role="navigation">
					<div data-spy="affix" data-offset-top="45" data-offset-bottom="90">
						<div id="showOrHide" style="display:none;">
						<ul class="nav" id="sidebar-nav">
							<li><a href="#"><spring:message code="facebook.top" /></a></li>
							<li><a href="#section1">${facebookForecast[0].nameOfEvent}</a></li>
							<li><a href="#section2">${facebookForecast[1].nameOfEvent}</a></li>
							<li><a href="#section3">${facebookForecast[2].nameOfEvent}</a></li>
							<li><a href="#section4">${facebookForecast[3].nameOfEvent}</a></li>
							<li><a href="#section5">${facebookForecast[4].nameOfEvent}</a></li>
							
						</ul>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-9" data-spy="scroll"
					data-target="#sidebar-nav">
					<div class="row">
						<div class="col-sm-6">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4>
										Login
										<fb:login-button scope="user_events,public_profile,email"
											onlogin="checkLoginState();">
										</fb:login-button>
										Logout
										<fb:login-button scope="user_events,public_profile,email"
											onlogin="logoutFromFB();">
										</fb:login-button>
									</h4>
								</div>
								<div class="panel-body">
									<div id="status"></div>
									<div id="resp"></div>
								</div>
								<!--/panel-body-->
							</div>
							<!--/panel-->
						</div>
						<!--/col-->

					</div>
					<!--/row-->
<div id="showOrHide1" style="display:none;">
					<h1 id="section1">${facebookForecast[0].nameOfEvent}</h1>
						<c:set var="whatUnit" scope="session" value="${units}" />
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4><spring:message code="facebook.starttime" />:
								${facebookForecast[0].weather.startTime}</h4>
						</div>
						<div class="panel-body">
							<div class="col-sm-8">
								<img class="img-responsive"
									src="${facebookForecast[0].cover_url}">
								<p>${facebookForecast[0].country},
									${facebookForecast[0].city},${facebookForecast[0].street},
									${facebookForecast[0].nameOfPlace}
								<p>${facebookForecast[0].description}
									<a href="${facebookForecast[0].ticket_uri}">Tickets</a>
								</p>
								</p>

							</div>
							<div class="col-sm-4">
								<h3><spring:message code="facebook.forcast" /></h3>
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td><spring:message code="weather.sky" /></td>
											<td>${facebookForecast[0].weather.summary}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.temp" /></td>
											<td>${ whatUnit =='true' ? facebookForecast[0].weather.temperature : facebookForecast[0].weather.temperatureFH} ${unitTemp}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.windspeed" /></td>
											<td>${ whatUnit =='true' ? facebookForecast[0].weather.windSpeed  : facebookForecast[0].weather.windSpeedMPH} ${unitSpeed}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.visibility" /></td>
											<td>${ whatUnit =='true' ? facebookForecast[0].weather.visibility  : facebookForecast[0].weather.visibilityMiles} ${unitKmMiles}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.cloudy" /></td>
											<td>${facebookForecast[0].weather.cloudCover}%</td>
										</tr>
										<tr>
											<td><spring:message code="weather.humidity" /></td>
											<td>${facebookForecast[0].weather.humidity}%</td>
										</tr>
										<tr>
											<td><spring:message code="weather.pressure"/></td>
											<td>${facebookForecast[0].weather.pressure} hPa</td>
										</tr>
									</tbody>
								</table>
							</div>

						</div>
					</div>
					<!--/panel-->

					<h1 id="section2">${facebookForecast[1].nameOfEvent}</h1>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Start time of event:
								${facebookForecast[1].weather.startTime}</h4>
						</div>
						<div class="panel-body">
							<div class="col-sm-8">
								<img class="img-responsive"
									src="${facebookForecast[1].cover_url}">
								<p>${facebookForecast[1].country},
									${facebookForecast[1].city},${facebookForecast[1].street},
									${facebookForecast[1].nameOfPlace}
								<p>${facebookForecast[1].description}.<a
										href="${facebookForecast[1].ticket_uri}">Tickets</a>
								</p>
								</p>
							</div>
							<div class="col-sm-4">
								<h3>Weather forcast for this event</h3>
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td><spring:message code="weather.sky" /></td>
											<td>${facebookForecast[1].weather.summary}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.temp" /></td>
											<td>${ whatUnit =='true' ? facebookForecast[1].weather.temperature : facebookForecast[1].weather.temperatureFH} ${unitTemp}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.windspeed" /></td>
											<td>${ whatUnit =='true' ? facebookForecast[1].weather.windSpeed  : facebookForecast[1].weather.windSpeedMPH} ${unitSpeed}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.visibility" /></td>
											<td>${ whatUnit =='true' ? facebookForecast[1].weather.visibility  : facebookForecast[1].weather.visibilityMiles} ${unitKmMiles}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.cloudy" /></td>
											<td>${facebookForecast[1].weather.cloudCover}%</td>
										</tr>
										<tr>
											<td><spring:message code="weather.humidity" /></td>
											<td>${facebookForecast[1].weather.humidity}%</td>
										</tr>
										<tr>
											<td><spring:message code="weather.pressure"/></td>
											<td>${facebookForecast[1].weather.pressure} hPa</td>
										</tr>
									</tbody>
								</table>
							</div>

						</div>
					</div>
					<h1 id="section3">${facebookForecast[2].nameOfEvent}</h1>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Start time of event:
								${facebookForecast[2].weather.startTime}</h4>
						</div>
						<div class="panel-body">
							<div class="col-sm-8">
								<img class="img-responsive"
									src="${facebookForecast[2].cover_url}">
								<p>${facebookForecast[2].country},
									${facebookForecast[2].city},${facebookForecast[2].street},
									${facebookForecast[2].nameOfPlace}
								<p>${facebookForecast[2].description}.<a
										href="${facebookForecast[2].ticket_uri}">Tickets</a>
								</p>
								</p>
							</div>
							<div class="col-sm-4">
								<h3>Weather forcast for this event</h3>
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td><spring:message code="weather.sky" /></td>
											<td>${facebookForecast[2].weather.summary}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.temp" /></td>
											<td>${ whatUnit =='true' ? facebookForecast[2].weather.temperature : facebookForecast[2].weather.temperatureFH} ${unitTemp}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.windspeed" /></td>
											<td>${ whatUnit =='true' ? facebookForecast[2].weather.windSpeed  : facebookForecast[2].weather.windSpeedMPH} ${unitSpeed}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.visibility" /></td>
											<td>${ whatUnit =='true' ? facebookForecast[2].weather.visibility  : facebookForecast[2].weather.visibilityMiles} ${unitKmMiles}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.cloudy" /></td>
											<td>${facebookForecast[2].weather.cloudCover}%</td>
										</tr>
										<tr>
											<td><spring:message code="weather.humidity" /></td>
											<td>${facebookForecast[2].weather.humidity}%</td>
										</tr>
										<tr>
											<td><spring:message code="weather.pressure"/></td>
											<td>${facebookForecast[2].weather.pressure} hPa</td>
										</tr>
									</tbody>
								</table>
							</div>

						</div>
					</div>
					<h1 id="section4">${facebookForecast[3].nameOfEvent}</h1>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Start time of event:
								${facebookForecast[3].weather.startTime}</h4>
						</div>
						<div class="panel-body">
							<div class="col-sm-8">
								<img class="img-responsive"
									src="${facebookForecast[3].cover_url}">
								<p>${facebookForecast[3].country},
									${facebookForecast[3].city},${facebookForecast[3].street},
									${facebookForecast[3].nameOfPlace}
								<p>${facebookForecast[3].description}.<a
										href="${facebookForecast[3].ticket_uri}">Tickets</a>
								</p>
								</p>
							</div>
							<div class="col-sm-4">
								<h3>Weather forcast for this event</h3>
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td><spring:message code="weather.sky" /></td>
											<td>${facebookForecast[3].weather.summary}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.temp" /></td>
											<td>${ whatUnit =='true' ? facebookForecast[3].weather.temperature : facebookForecast[3].weather.temperatureFH} ${unitTemp}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.windspeed" /></td>
											<td>${ whatUnit =='true' ? facebookForecast[3].weather.windSpeed  : facebookForecast[3].weather.windSpeedMPH} ${unitSpeed}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.visibility" /></td>
											<td>${ whatUnit =='true' ? facebookForecast[3].weather.visibility  : facebookForecast[3].weather.visibilityMiles} ${unitKmMiles}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.cloudy" /></td>
											<td>${facebookForecast[3].weather.cloudCover}%</td>
										</tr>
										<tr>
											<td><spring:message code="weather.humidity" /></td>
											<td>${facebookForecast[3].weather.humidity}%</td>
										</tr>
										<tr>
											<td><spring:message code="weather.pressure"/></td>
											<td>${facebookForecast[3].weather.pressure} hPa</td>
										</tr>
									</tbody>
								</table>
							</div>

						</div>
					</div>
					<h1 id="section5">${facebookForecast[4].nameOfEvent}</h1>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Start time of event:
								${facebookForecast[4].weather.startTime}</h4>
						</div>
						<div class="panel-body">
							<div class="col-sm-8">
								<img class="img-responsive"
									src="${facebookForecast[4].cover_url}">
								<p>${facebookForecast[4].country},
									${facebookForecast[4].city},${facebookForecast[4].street},
									${facebookForecast[4].nameOfPlace}
								<p>${facebookForecast[4].description}.<a
										href="${facebookForecast[4].ticket_uri}">Tickets</a>
								</p>
								</p>
							</div>
							<div class="col-sm-4">
								<h3>Weather forcast for this event</h3>
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td><spring:message code="weather.sky" /></td>
											<td>${facebookForecast[4].weather.summary}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.temp" /></td>
											<td>${ whatUnit =='true' ? facebookForecast[4].weather.temperature : facebookForecast[4].weather.temperatureFH} ${unitTemp}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.windspeed" /></td>
											<td>${ whatUnit =='true' ? facebookForecast[4].weather.windSpeed  : facebookForecast[4].weather.windSpeedMPH} ${unitSpeed}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.visibility" /></td>
											<td>${ whatUnit =='true' ? facebookForecast[4].weather.visibility  : facebookForecast[4].weather.visibilityMiles} ${unitKmMiles}</td>
										</tr>
										<tr>
											<td><spring:message code="weather.cloudy" /></td>
											<td>${facebookForecast[4].weather.cloudCover}%</td>
										</tr>
										<tr>
											<td><spring:message code="weather.humidity" /></td>
											<td>${facebookForecast[4].weather.humidity}%</td>
										</tr>
										<tr>
											<td><spring:message code="weather.pressure"/></td>
											<td>${facebookForecast[4].weather.pressure} hPa</td>
										</tr>
									</tbody>
								</table>
							</div>

						</div>
					</div>
					<!--/panel-->
					</div>
				</div>
			</div>
			<!--/.col-xs-12-->
		</div>
		<!--/.row-->
	</div>
	
	<!--/.page-container-->

	<!-- script references -->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>