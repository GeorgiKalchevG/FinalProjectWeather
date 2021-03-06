<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Facebook Login JavaScript Example</title>
<meta charset="UTF-8">
</head>
<body>
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
      document.getElementById('status').innerHTML = 'Please log ' +
        'into this app.';
    } else {
      // The person is not logged into Facebook, so we're not sure if
      // they are logged into this app or not.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into Facebook.';
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
    appId      : '262037110796419',
    cookie     : true,  // enable cookies to allow the server to access 
                        // the session
    xfbml      : true,  // parse social plugins on this page
    version    : 'v2.5' // use graph api version 2.5
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
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
      console.log('Successful login for: ' + response.name);
      document.getElementById('status').innerHTML =
        'Thanks for logging in, ' + response.name + '! Please wait to fetch your events and show you the future! ';
      FB.api(
			  '/me',
			  'GET',
			  {"fields":"name,email,events.limit(10){place,start_time}"},
			  function(response) {
				  var rezz = JSON.stringify(response);
				        $.ajax({
							url : "facebook",
							type : 'POST',
							data : {
								ivanshishman : rezz,
								kakvo: "ds"
							},
							success : function(data){
								if(data=="true"){
								location.reload();
									}
								document.getElementById('status').innerHTML =
							        'Thank you for wait ' + response.name + '. Here is your weather for incoming events you will be attending. ';},
							fail:function(){}
							});
			  }
			);
    });
  }
function logoutFromFB(){
	FB.logout(function(response) {
		   // Person is now logged out
	  });
}
function getFromFB(){
	FB.api(
			  '/me',
			  'GET',
			  {"fields":"name,email,events.limit(10){place,start_time}"},
			  function(response) {
				  var rezz = JSON.stringify(response);
				  console.log('Response: ' + resp);
				        $.ajax({
							url : "facebook",
							type : 'POST',
							data : {
								ivanshishman : rezz,
								kakvo: "ds"
							},
							success : function(){},
							fail:function(){}
							});
			  }
			);
}
</script>

<!--
  Below we include the Login Button social plugin. This button uses
  the JavaScript SDK to present a graphical Login button that triggers
  the FB.login() function when clicked.
-->
login
<fb:login-button scope="user_events,public_profile,email" onlogin="checkLoginState();">
</fb:login-button>
logout 1
<fb:login-button scope="user_events,public_profile,email" onlogin="logoutFromFB();">
</fb:login-button>
<button onclick="getFromFB()">Click me</button>

<div id="status">
</div>
<div id="resp"></div>
<table class="table"
							style="width: 75%; display: block; overflow-x: auto;">
							<tr>
								<th>Start time:</th>
								<c:forEach items="${facebookForecast}" var="entry">
									<th>${entry.start_time}
									</th>
								</c:forEach>
							</tr>
							<tr>
								<th>Name:</th>
								<c:forEach items="${facebookForecast}" var="entry">
									<th> ${entry.name}</th>
								</c:forEach>
							</tr>
							<tr>
								<th>City:</th>
								<c:forEach items="${facebookForecast}" var="entry">
									<th> ${entry.city}</th>
								</c:forEach>

							</tr>
							<tr>
								<th>Country: </th>
								<c:forEach items="${facebookForecast}" var="entry">
									<th>${entry.country}</th>
								</c:forEach>
							</tr>
							<tr>
								<th>latitude: </th>
								<c:forEach items="${facebookForecast}" var="entry">
									<th>${entry.latitude} </th>
								</c:forEach>
							</tr>
							<tr>
								<th>longitude: </th>
								<c:forEach items="${facebookForecast}" var="entry">
									<th>${entry.longitude}</th>
								</c:forEach>
							</tr>
							<tr>
								<th>street : </th>
								<c:forEach items="${facebookForecast}" var="entry">
									<th>${entry.street}</th>
								</c:forEach>
							</tr>
							<tr>
								<th>weather.time :</th>
								<c:forEach items="${facebookForecast}" var="entry">
									<th>${entry.weather.time}</th>
								</c:forEach>
							</tr>
							<tr>
								<th>weather.summary :</th>
								<c:forEach items="${facebookForecast}" var="entry">
									<th>${entry.weather.summary}</th>
								</c:forEach>
							</tr>
							<tr>
								<th>weather.tempC :</th>
								<c:forEach items="${facebookForecast}" var="entry">
									<th>${entry.weather.temperature}</th>
								</c:forEach>
							</tr>
						</table>
</body>
</html>