<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<script>
	// This is called with the results from from FB.getLoginStatus().
	function statusChangeCallback(response) {
		console.log('statusChangeCallback');
		console.log(response);
		// The response object is returned with a status field that lets the
		// app know the current login status of the person.
		// Full docs on the response object can be found in the documentation
		// for FB.getLoginStatus().
		if (response.status === 'connected') {
			// Logged into your app and Facebook.
			testAPI();
		} else if (response.status === 'not_authorized') {
			// The person is logged into Facebook, but not your app.
			document.getElementById('status').innerHTML = 'Please log '
					+ 'into this app.';
		} else {
			// The person is not logged into Facebook, so we're not sure if
			// they are logged into this app or not.
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
																	location
																			.reload();
																}
																document
																		.getElementById('status').innerHTML = 'Thank you for wait '
																		+ response.name
																		+ '. Here is your weather for incoming events you will be attending. ';
															},
															fail : function() {
															}
														});
											});
						});
	}
	function logoutFromFB() {
		FB.logout(function(response) {
			// Person is now logged out
		});
	}
	function getFromFB() {
		FB
				.api(
						'/me',
						'GET',
						{
							"fields" : "name,events.limit(5){cover,description,name,place,start_time,ticket_uri}"
						}, function(response) {
							var rezz = JSON.stringify(response);
							console.log('Response: ' + resp);
							$.ajax({
								url : "facebook",
								type : 'POST',
								data : {
									ivanshishman : rezz,
									kakvo : "ds"
								},
								success : function() {
								},
								fail : function() {
								}
							});
						});
	}
</script>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>Holo Theme</title>
<meta name="generator" content="Bootply" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
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
						<ul class="nav" id="sidebar-nav">
							<li><a href="#">To the top</a></li>
							<li><a href="#section1">${facebookForecast[0].nameOfEvent}</a></li>
							<li><a href="#section2">${facebookForecast[1].nameOfEvent}</a></li>
							<li><a href="#section3">${facebookForecast[2].nameOfEvent}</a></li>
							<li><a href="#section4">${facebookForecast[3].nameOfEvent}</a></li>
							<li><a href="#section5">${facebookForecast[4].nameOfEvent}</a></li>
						</ul>
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
										<fb:login-button autologoutlink="true" scope="user_events,public_profile,email"
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

					<h1 id="section1">${facebookForecast[0].nameOfEvent}</h1>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Start time of event:
								${facebookForecast[0].weather.startTime}</h4>
						</div>
						<div class="panel-body">
							<div class="col-sm-8">
								<img class="img-responsive"
									src="${facebookForecast[0].cover_url}">
								<p>${facebookForecast[0].country},
									${facebookForecast[0].city},${facebookForecast[0].street},
									${facebookForecast[0].nameOfPlace}
								<p>${facebookForecast[0].description} <a href="${facebookForecast[0].ticket_uri}">Tickets</a>
								</p>
								</p>

							</div>
							<div class="col-sm-4">
								<h3>Weather forcast for this event</h3>
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td>Summary</td>
											<td>${facebookForecast[0].weather.summary}</td>
										</tr>
										<tr>
											<td>Temperature</td>
											<td>${facebookForecast[0].weather.temperature}</td>
										</tr>
										<tr>
											<td>Apparent temperature</td>
											<td>${facebookForecast[0].weather.apparentTemperature}</td>
										</tr>
										<tr>
											<td>Wind speed</td>
											<td>${facebookForecast[0].weather.windSpeed}</td>
										</tr>
										<tr>
											<td>Visibility</td>
											<td>${facebookForecast[0].weather.visibility}</td>
										</tr>
										<tr>
											<td>Cloud cover</td>
											<td>${facebookForecast[0].weather.cloudCover}</td>
										</tr>
										<tr>
											<td>Humidity</td>
											<td>${facebookForecast[0].weather.humidity}</td>
										</tr>
										<tr>
											<td>Pressure</td>
											<td>${facebookForecast[0].weather.pressure}</td>
										</tr>
										<tr>
											<td>Ozone</td>
											<td>${facebookForecast[0].weather.ozone}</td>
										</tr>
										<tr>
											<td>Dew point</td>
											<td>${facebookForecast[0].weather.dewPoint}</td>
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
								<p>${facebookForecast[1].description}.<a href="${facebookForecast[1].ticket_uri}">Tickets</a></p>
								</p>
							</div>
							<div class="col-sm-4">
								<h3>Weather forcast for this event</h3>
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td>Summary</td>
											<td>${facebookForecast[1].weather.summary}</td>
										</tr>
										<tr>
											<td>Temperature</td>
											<td>${facebookForecast[1].weather.temperature}</td>
										</tr>
										<tr>
											<td>Apparent temperature</td>
											<td>${facebookForecast[1].weather.apparentTemperature}</td>
										</tr>
										<tr>
											<td>Wind speed</td>
											<td>${facebookForecast[1].weather.windSpeed}</td>
										</tr>
										<tr>
											<td>Visibility</td>
											<td>${facebookForecast[1].weather.visibility}</td>
										</tr>
										<tr>
											<td>Cloud cover</td>
											<td>${facebookForecast[1].weather.cloudCover}</td>
										</tr>
										<tr>
											<td>Humidity</td>
											<td>${facebookForecast[1].weather.humidity}</td>
										</tr>
										<tr>
											<td>Pressure</td>
											<td>${facebookForecast[1].weather.pressure}</td>
										</tr>
										<tr>
											<td>Ozone</td>
											<td>${facebookForecast[1].weather.ozone}</td>
										</tr>
										<tr>
											<td>Dew point</td>
											<td>${facebookForecast[1].weather.dewPoint}</td>
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
								<p>${facebookForecast[2].description}.<a href="${facebookForecast[2].ticket_uri}">Tickets</a></p>
								</p>
							</div>
							<div class="col-sm-4">
								<h3>Weather forcast for this event</h3>
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td>Summary</td>
											<td>${facebookForecast[2].weather.summary}</td>
										</tr>
										<tr>
											<td>Temperature</td>
											<td>${facebookForecast[2].weather.temperature}</td>
										</tr>
										<tr>
											<td>Apparent temperature</td>
											<td>${facebookForecast[2].weather.apparentTemperature}</td>
										</tr>
										<tr>
											<td>Wind speed</td>
											<td>${facebookForecast[2].weather.windSpeed}</td>
										</tr>
										<tr>
											<td>Visibility</td>
											<td>${facebookForecast[2].weather.visibility}</td>
										</tr>
										<tr>
											<td>Cloud cover</td>
											<td>${facebookForecast[2].weather.cloudCover}</td>
										</tr>
										<tr>
											<td>Humidity</td>
											<td>${facebookForecast[2].weather.humidity}</td>
										</tr>
										<tr>
											<td>Pressure</td>
											<td>${facebookForecast[2].weather.pressure}</td>
										</tr>
										<tr>
											<td>Ozone</td>
											<td>${facebookForecast[2].weather.ozone}</td>
										</tr>
										<tr>
											<td>Dew point</td>
											<td>${facebookForecast[2].weather.dewPoint}</td>
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
								<p>${facebookForecast[3].description}.<a href="${facebookForecast[3].ticket_uri}">Tickets</a></p>
								</p>
							</div>
							<div class="col-sm-4">
								<h3>Weather forcast for this event</h3>
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td>Summary</td>
											<td>${facebookForecast[3].weather.summary}</td>
										</tr>
										<tr>
											<td>Temperature</td>
											<td>${facebookForecast[3].weather.temperature}</td>
										</tr>
										<tr>
											<td>Apparent temperature</td>
											<td>${facebookForecast[3].weather.apparentTemperature}</td>
										</tr>
										<tr>
											<td>Wind speed</td>
											<td>${facebookForecast[3].weather.windSpeed}</td>
										</tr>
										<tr>
											<td>Visibility</td>
											<td>${facebookForecast[3].weather.visibility}</td>
										</tr>
										<tr>
											<td>Cloud cover</td>
											<td>${facebookForecast[3].weather.cloudCover}</td>
										</tr>
										<tr>
											<td>Humidity</td>
											<td>${facebookForecast[3].weather.humidity}</td>
										</tr>
										<tr>
											<td>Pressure</td>
											<td>${facebookForecast[3].weather.pressure}</td>
										</tr>
										<tr>
											<td>Ozone</td>
											<td>${facebookForecast[3].weather.ozone}</td>
										</tr>
										<tr>
											<td>Dew point</td>
											<td>${facebookForecast[3].weather.dewPoint}</td>
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
								<p>${facebookForecast[4].description}.<a href="${facebookForecast[4].ticket_uri}">Tickets</a></p>
								</p>
							</div>
							<div class="col-sm-4">
								<h3>Weather forcast for this event</h3>
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td>Summary</td>
											<td>${facebookForecast[4].weather.summary}</td>
										</tr>
										<tr>
											<td>Temperature</td>
											<td>${facebookForecast[4].weather.temperature}</td>
										</tr>
										<tr>
											<td>Apparent temperature</td>
											<td>${facebookForecast[4].weather.apparentTemperature}</td>
										</tr>
										<tr>
											<td>Wind speed</td>
											<td>${facebookForecast[4].weather.windSpeed}</td>
										</tr>
										<tr>
											<td>Visibility</td>
											<td>${facebookForecast[4].weather.visibility}</td>
										</tr>
										<tr>
											<td>Cloud cover</td>
											<td>${facebookForecast[4].weather.cloudCover}</td>
										</tr>
										<tr>
											<td>Humidity</td>
											<td>${facebookForecast[4].weather.humidity}</td>
										</tr>
										<tr>
											<td>Pressure</td>
											<td>${facebookForecast[4].weather.pressure}</td>
										</tr>
										<tr>
											<td>Ozone</td>
											<td>${facebookForecast[4].weather.ozone}</td>
										</tr>
										<tr>
											<td>Dew point</td>
											<td>${facebookForecast[4].weather.dewPoint}</td>
										</tr>
									</tbody>
								</table>
							</div>

						</div>
					</div>
					<!--/panel-->
				</div>
			</div>
			<!--/.col-xs-12-->
		</div>
		<!--/.row-->
	</div>
	</div>
	<!--/.container-->
	</div>
	<!--/.page-container-->

	<!-- script references -->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>