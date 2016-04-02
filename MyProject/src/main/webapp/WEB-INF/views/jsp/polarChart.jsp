<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="js/Chart.js" var="chart" />
<script type="text/javascript" src="${chart}"></script>
<c:import url="planner.jsp"></c:import>
<div class="row">
	<div class="col-sm-6">
	
			<div id="info"></div>
		<div id="canvas-holder" style="width: 100%; background-color: #893d54;">

			<div id="period"></div>
		
		

		<canvas id="chart-area" width="300" height="300"></canvas>
		</div>
	</div>
	<div class="col-sm-6">
		<div style="width: 50%">
			<canvas id="canvas" height="450" width="600"></canvas>
		</div>

	</div>
</div>
<script>
	var data = ${plan};
	var title = '<h1>'+data.title+'</h1>';
	var clouds = '<h1>'+data.cloudCover+'</h1>';
	$('#period').append(title);
	$('#info').append(clouds);
	console.log(data.array);
	console.log(data.title);
	console.log(data.cloudCover);
	var polarData = [ {
		value : data.array[0],
		color : "#0033cc",
		highlight : "#1a53ff",
		label : "Precipitation"
	}, {
		value : data.array[1],
		color : "#46BFBD",
		highlight : "#5AD3D1",
		label : "Cool"
	}, {
		value : data.array[2],
		color : "#4d79ff",
		highlight : "#99b3ff",
		label : "Rain"
	}, {
		value : data.array[3],
		color : "#949FB1",
		highlight : "#A8B3C5",
		label : "Partly Cloudy"
	}, {
		value : data.array[4],
		color : "#ffcc00",
		highlight : "#ffdb4d",
		label : "Warm"
	}, {
		value : data.array[5],
		color : "#b3b3b3",
		highlight : "#b4b6af",
		label : "Cloudy"
	}, {
		value : data.array[6],
		color : "#ffff80",
		highlight : "#ffffb3",
		label : "Sunny"
	}, {
		value : data.array[7],
		color : "#4D5360",
		highlight : "#616774",
		label : "Windy"
	}, {
		value : data.array[8],
		color : "#4d4d00",
		highlight : "#666600",
		label : "Fog"
	}, {
		value : data.array[9],
		color : "#1B1E17",
		highlight : "#C8C7CF",
		label : "Thunderstorms"
	}, {
		value : data.array[10],
		color : "#919AB4",
		highlight : "#B4BDD3",
		label : "Hail"
	}, {
		value : data.array[11],
		color : "#f2f2f2",
		highlight : "#ffffff",
		label : "Ground Snow"
	}, {
		value : data.array[12],
		color : "#a6a6a6",
		highlight : "#bfbfbf",
		label : "Tornado"
	}, {
		value : data.array[13],
		color : "#ff8533",
		highlight : "#ffa366",
		label : "Sweltering"
	}, {
		value : data.array[14],
		color : "#94b8b8",
		highlight : "#b3cccc",
		label : "Humid"
	}, {
		value : data.array[15],
		color : "#ccf5ff",
		highlight : "#e6faff",
		label : "Freezing"
	}, {
		value : data.array[16],
		color : "#ff8c1a",
		highlight : "#ffa64d",
		label : "Hot"
	}, {
		value : data.array[17],
		color : "#e6e6e6",
		highlight : "#f2f2f2",
		label : "Snow"
	}

	];

	window.onload = function() {
		var ctx = document.getElementById("chart-area").getContext("2d");
		window.myPolarArea = new Chart(ctx).PolarArea(polarData, {
			responsive : true
		});
	};
/* 	var randomScalingFactor = function(){ return Math.round(Math.random()*100)};

	var barChartData = {
		labels : ["January","February","March","April","May","June","July"],
		datasets : [
			{
				fillColor : "rgba(220,220,220,0.5)",
				strokeColor : "rgba(220,220,220,0.8)",
				highlightFill: "rgba(220,220,220,0.75)",
				highlightStroke: "rgba(220,220,220,1)",
				data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
			},
			{
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,0.8)",
				highlightFill : "rgba(151,187,205,0.75)",
				highlightStroke : "rgba(151,187,205,1)",
				data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
			}
		]

	}
	window.onload = function(){
		var ctxa = document.getElementById("canvas").getContext("2d");
		window.myBar = new Chart(ctxa).Bar(barChartData, {
			responsive : true
		});
	} */

</script>