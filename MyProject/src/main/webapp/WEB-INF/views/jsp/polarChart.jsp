<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="js/Chart.js" var="chart" />
<script type="text/javascript" src="${chart}"></script>
<c:import url="planner.jsp"></c:import>
<div class="row">
	<div class="col-sm-3">
		<div id="text-descr" ></div>
	</div>
	<div class="col-sm-5">
			  <div id="myCarousel" class="carousel slide" data-ride="carousel">
					  <!-- Indicators -->
					  <ol class="carousel-indicators">
					    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					    <li data-target="#myCarousel" data-slide-to="1"></li>
					    <li data-target="#myCarousel" data-slide-to="2"></li>
					    <li data-target="#myCarousel" data-slide-to="3"></li>
					    <li data-target="#myCarousel" data-slide-to="4"></li>
					  </ol>
					
					  <!-- Wrapper for slides -->
					  <div class="carousel-inner" role="listbox">
					    <div class="item active">
					     <div id="output1"></div>
					    </div>
					
					    <div class="item">
					      <div id="output2"></div>
					    </div>
					
					    <div class="item">
					      <div id="output3"></div>
					    </div>
					
					    <div class="item">
					      <div id="output4"></div>
					    </div>
					    <div class="item">
					      <div id="output5"></div>
					    </div>
					  </div>
					
					  <!-- Left and right controls -->
					  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
					    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					    <span class="sr-only">Previous</span>
					  </a>
					  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
					    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					    <span class="sr-only">Next</span>
					  </a>
					</div>
			</div>
	<div class="col-sm-3">
		<div id="info" ></div>
	</div>


	</div>
<div class="row" ">
	
	<div class="col-sm-6">
		
		<div id="canvas-holder"
			style="width: 100%; ">
			<div id="period"></div>
			<canvas id="chart-area" width="210px" height="120px"></canvas>
		</div>
	</div>
	<div class="col-sm-6">
	<h1>Variation in temperature in &deg;C and precipitation in cm</h1>
	
	<div class="row">	
<div class="col-sm-6" >	
		<ul class="legend">
		    <li><span class="lowest"></span> Lowest Measured</li>
		    <li><span class="average"></span> Average</li>
		    <li><span class="highest"></span> Highest Measured</li>
		
		</ul>
	</div>
</div>
		<canvas id="canvas" height="120px" width="210px"></canvas>

	</div>
</div>
<style>
.legend { list-style: none; }
.legend li { float: left; margin-right: 10px; }
.legend span { border: 1px solid #ccc; float: left; width: 12px; height: 12px; margin: 2px; }
/* your colors */
.legend .lowest { background-color: rgba(63, 191, 191, 0.5) ; }

.legend .average { background-color: rgba(63, 127, 191, 0.5); }
.legend .highest { background-color: rgba(255, 119, 20, 0.5); }

</style>
<script>
	var data = ${plan};
	var forecast = ["Warm","Partly Cloudy","Precipitation", "Rain", "Cool","Sunny","Cloudy","Fog","Windy","Thunderstorms","Hail","Ground Snow","Tornado","Sweltering","Humid","Freezing", "Hot","Snow"];
	var title = '<h1>'+data.title+'</h1>';
	var clouds = '<h3>For the selected period <br/>the weather in '.concat("${location}").concat(' will be ') + data.cloudCover + '</h4>';
	$('#text-descr').append("<h4>There is a ");
	for (i = 0; i < forecast.length; i++) {
		console.log(data.array[i]);
		if (data.array[i] > 33) {
			$('#text-descr').append('<h4>'+data.array[i] + '% chance the forecast be ' + forecast[i] + '</h4>');
			console.log(data.array[i] + '% chance the forecast be' + forecast[i] + '<br/>');
		}

	}
	
	$('#text-descr').append('</h3>');
	$('#period').append(title);
	$('#info').append(clouds);
	console.log(data.array);
	console.log(data.title);
	console.log(data.cloudCover);
	var polarData = [ {
		value : data.array[0],
		color : "#0033cc",
		highlight : "#1a53ff",
		label : "Warm"
	}, {
		value : data.array[1],
		color : "#46BFBD",
		highlight : "#5AD3D1",
		label : "Partly Cloudy"
	}, {
		value : data.array[2],
		color : "#46BFBD",
		highlight : "#5AD3D1",
		label : "Precipitation"
	}, {
		value : data.array[3],
		color : "#46BFBD",
		highlight : "#5AD3D1",
		label : "Rain"
	}, {
		value : data.array[4],
		color : "#4d79ff",
		highlight : "#99b3ff",
		label : "Cool"
	}, {
		value : data.array[5],
		color : "#949FB1",
		highlight : "#A8B3C5",
		label : "Sunny"
	}, {
		value : data.array[6],
		color : "#ffcc00",
		highlight : "#ffdb4d",
		label : "Cloudy"
	}, {
		value : data.array[7],
		color : "#b3b3b3",
		highlight : "#b4b6af",
		label : "Fog"
	}, {
		value : data.array[8],
		color : "#ffff80",
		highlight : "#ffffb3",
		label : "Windy"
	}, {
		value : data.array[9],
		color : "#4D5360",
		highlight : "#616774",
		label : "Thunderstorms"
	}, {
		value : data.array[10],
		color : "#4d4d00",
		highlight : "#666600",
		label : "Hail"
	}, {
		value : data.array[11],
		color : "#1B1E17",
		highlight : "#C8C7CF",
		label : "Ground Snow"
	}, {
		value : data.array[12],
		color : "#919AB4",
		highlight : "#B4BDD3",
		label : "Tornado"
	}, {
		value : data.array[13],
		color : "#f2f2f2",
		highlight : "#ffffff",
		label : "Sweltering"
	}, {
		value : data.array[14],
		color : "#a6a6a6",
		highlight : "#bfbfbf",
		label : "Humid"
	}, {
		value : data.array[15],
		color : "#ff8533",
		highlight : "#ffa366",
		label : "Freezing"
	}, {
		value : data.array[16],
		color : "#94b8b8",
		highlight : "#b3cccc",
		label : "Hot"
	}, {
		value : data.array[17],
		color : "#ccf5ff",
		highlight : "#e6faff",
		label : "Snow"
	}

	];

	window.onload = function() {
		var ctx = document.getElementById("chart-area").getContext("2d");
		window.myPolarArea = new Chart(ctx).PolarArea(polarData, {
			segmentStrokeWidth : 2,
			responsive : true
		});
		var ctxa = document.getElementById("canvas").getContext("2d");
		window.myBar = new Chart(ctxa).Bar(barChartData, {
			responsive : true,
			scaleBeginAtZero : true
			
		});
	};
	var randomScalingFactor = function() {
		return Math.round(Math.random() * 100)
	};

	var barChartData = {
		labels : [ "Temp High", "Temp Low", "Precipitation", "Dewpoint High","Dewpoint Low" ],
		datasets : [
				{
					fillColor : "rgba(63, 191, 191, 0.5)", 			
					strokeColor : "rgba(63, 191, 191,0.8)",
					highlightFill : "rgba(63, 191, 191,0.75)",
					highlightStroke : "rgba(63, 191, 191,1)",
					data : [data.variation[0], data.variation[1],data.variation[2], data.variation[3],data.variation[4] ]
				},
				{
					fillColor : "rgba(63, 127, 191, 0.5)",
					strokeColor : "rgba(63, 127, 191,0.8)",
					highlightFill : "rgba(63, 127, 191,0.75)",
					highlightStroke : "rgba(63, 127, 191,1)",
					data : [data.variation[5], data.variation[6],data.variation[7], data.variation[8],data.variation[9] ]
				},
				{
					fillColor : "rgba(255, 119, 20, 0.5)",
					strokeColor : "rgba(255, 119, 20,0.8)",
					highlightFill : "rgba(255, 119, 20,0.75)",
					highlightStroke : "rgba(255, 119, 20,1)",
					data : [data.variation[10], data.variation[11],data.variation[12], data.variation[13],data.variation[14] ]
				}

		]

	}
	$(document).ready(function() {
		  //Declare variables
		  var $searchButton = $('#searchButton');
		  //add a colon to the beginning of your AppId string
		  var appId = ':eDV3WWvSbtWEm5AI9qhWiXiNg17HgTe+bqs5jQSVTus';

		  //Function to get images
		  function getImage() {
		    //base64 encode the AppId
		    var azureKey = btoa(appId);
		    //get the value from the search box
		    var str = "${location}";
			var res = str.split(", ");
		    var $searchQuery = res[0];
		   
		    //Create the search string
		    var myUrl = 'https://api.datamarket.azure.com/Bing/Search/v1/Composite?Sources=%27image%27&$top=5&$format=json&Query=%27' + $searchQuery + '%27&Query=%27vacation%27&ImageFilters=%27Aspect:Wide%27';
		    //Make post request to bing
		    console.log(myUrl);
		    $.ajax({
		      method: 'post',
		      url: myUrl,
		      //Set headers to authorize search with Bing
		      headers: {
		        'Authorization': 'Basic ' + azureKey
		      },
		      success: function(data) {
		        //Insert random image in dom
		       
		        
		        var imgLink1 = '<img width="780" height="460" src="' + data.d.results[0].Image[0].MediaUrl + '" />';
		        var imgLink2 = '<img width="780" height="460" src="' + data.d.results[0].Image[1].MediaUrl + '" />';
		        var imgLink3 = '<img width="780" height="460" src="' + data.d.results[0].Image[2].MediaUrl + '" />';
		        var imgLink4 = '<img width="780" height="460" src="' + data.d.results[0].Image[3].MediaUrl + '" />';
		        var imgLink5 = '<img width="780" height="460" src="' + data.d.results[0].Image[4].MediaUrl + '" />';
		        $('#output1').html(imgLink1);
		        $('#output2').html(imgLink2);
		        $('#output3').html(imgLink3);
		        $('#output4').html(imgLink4);
		        $('#output5').html(imgLink5);
		      },
		      failure: function(err) {
		        console.error(err);
		      }
		    });
		  };
		  //Trigger function when button is clicked
		 $(window).load(function(e) {
		   /*  e.preventDefault(); */
		    getImage();
		  });
		});
</script>