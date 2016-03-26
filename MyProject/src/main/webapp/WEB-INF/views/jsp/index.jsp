<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<title>Weather</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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



s
</head>
<body>

	<!-- heading start -->
	<div class="row">
		<div class="col-sm-12" style="background-color: black;">
			<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="col-sm-4" align="left">
					<a class="navbar-brand" href="#"><img
						src="https://cdn4.iconfinder.com/data/icons/iconsimple-freebies/512/umbrella.png"
						alt="Mountain View" style="width: 50px; height: 50px;"></a>
					<h4 style="color: white; padding-top: 10px">Ime na saita</h4>
				</div>

				<div class="col-sm-6" align="right" style="padding-top: 10px;">
					<form class="form-inline" role="form" action="search">
						<input type="text" placeholder="search city" class="form-control"
							id="email" name="search">
						<button type="submit" class="btn btn-default">Search</button>
					</form>
				</div>
				<div class="col-sm-2" align="right" style="padding-top: 10px;">
					<button type="submit" class="btn btn-default" align="right">change
						language</button>
					<button type="submit" class="btn btn-default" align="right">F/C</button>
				</div>

			</div>
			</nav>
		</div>
	</div>
	<!-- heading end -->
	<!-- heading start -->
	<div class="row">
		<div class="col-sm-12" style="background-color: white;">
			<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#">Plan your trip!</a></li>
					<li><a href="#">World map?</a></li>
					<li><a href="#">Shukarii</a></li>
				</ul>
			</div>
			</nav>
		</div>
	</div>

	<!-- heading end -->
	<div class="row">
		<div class="col-sm-8" style="background-color: lavender;">
			<div class="container">
				<h2>Grad kojto tarsish</h2>
				<h5>Posledno update time za toq grad</h5>
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#home">${city}</a></li>
					<li><a data-toggle="tab" href="#menu1">24 hours</a></li>
					<li><a data-toggle="tab" href="#menu2">Weekend</a></li>
					<li><a data-toggle="tab" href="#menu3">5-days</a></li>
				</ul>
				<div class="tab-content">
					<div id="home" class="tab-pane fade in active">
						<table class="table" style="width: 75%;">
							<tr>
								<!-- trqbwa da napravim kolekciq ot gif za razli4noto vreme -->
								<th rowspan="4"
									background="http://www.auplod.com/u/ulopad78451.gif"
									width="240" height="160"><img alt="" src="" />
									<h3 style="color: white;">Max Temp: ${list[0].tempHighCel}
										&#8451</h3></th>
								<c:forEach items="${list}" var="entry">
									<th><img src="${entry.icon_url}"> ${entry.weekday}</th>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${list}" var="entry">
									<th>${entry.day}.${entry.month}.${entry.year}</th>
								</c:forEach>

							</tr>
							<tr>
								<c:forEach items="${list}" var="entry">
									<th>${entry.conditions}</th>
								</c:forEach>

							</tr>
							<tr>
								<c:forEach items="${list}" var="entry">
									<th>
										<p>Max : ${entry.tempHighCel} &#8451 Min :
											${entry.tempLowCel} &#8451
									</th>
								</c:forEach>
							</tr>
							<tr>
								<th>Вятър:</th>
								<c:forEach items="${list}" var="entry">
									<th>Wind Speed: ${entry.maxwind_kph} Wind Direction:
										${entry.maxwind_dir}</th>
								</c:forEach>
							</tr>
							<tr>
								<th>Вероятност за валежи:</th>
								<c:forEach items="${list}" var="entry">
									<th>${entry.pop}%</th>
								</c:forEach>
							</tr>
							<tr>
								<th>Количество валежи:</th>
								<c:forEach items="${list}" var="entry">
									<th>${entry.qpf_alldayMM}mm</th>
								</c:forEach>
							</tr>
							<tr>
								<th>Влажност:</th>
								<c:forEach items="${list}" var="entry">
									<th>${entry.avehumidity}%</th>
								</c:forEach>
							</tr>
						</table>
					</div>
					<div id="menu1" class="tab-pane fade" style="overflow: auto;">
						<table class="table"
							style="width: 75%; display:block; overflow-x:auto;">
							<tr>
								<!-- trqbwa da napravim kolekciq ot gif za razli4noto vreme -->
								<th rowspan="1" width="240" height="160"><img alt="" src="" />
									<h3 style="color: black;">Прогноза за
										${list24hours[0].day}.${list24hours[0].month}.${list24hours[0].year}</h3></th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.hour}:00<img src="${entry.icon_url}">
									</th>
								</c:forEach>
							</tr>
							<tr>
								<th>Температура:</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.tempC}</th>
								</c:forEach>
							</tr>
							<tr>
								<th>Усеща се:</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.feelsLikeC}</th>
								</c:forEach>

							</tr>
							<tr>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.conditions}</th>
								</c:forEach>
							</tr>
							<tr>
								<th>Вятър:</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>Wind Speed: ${entry.windKPH} Wind Direction:
										${entry.dir}</th>
								</c:forEach>
							</tr>
							<tr>
								<th>Вероятност за валежи:</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.pop}%</th>
								</c:forEach>
							</tr>
							<tr>
								<th>Количество валежи:</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.qpfMM}mm</th>
								</c:forEach>
							</tr>
							<tr>
								<th>Атмосферно налягане:</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.mslphPa}hPa</th>
								</c:forEach>
							</tr>
							<tr>
								<th>Влажност:</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.humidity}%</th>
								</c:forEach>
							</tr>
							<tr>
								<th>Облачност:</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.sky}%</th>
								</c:forEach>
							</tr>
						</table>
					</div>
					<div id="menu2" class="tab-pane fade">tablica sus weekend
						info</div>
					<div id="menu3" class="tab-pane fade">tablica sus 5 dni info
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-4" style="background-color: lavenderblush;">
			<h2>Last 3 cities visited</h2>
			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapse1">Collapsible Group 1</a>
						</h4>
					</div>
					<div id="collapse1" class="panel-collapse collapse in">
						<div class="panel-body">1</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapse2">Collapsible Group 2</a>
						</h4>
					</div>
					<div id="collapse2" class="panel-collapse collapse">
						<div class="panel-body">2</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapse3">Collapsible Group 3</a>
						</h4>
					</div>
					<div id="collapse3" class="panel-collapse collapse">
						<div class="panel-body">3</div>
					</div>
				</div>
			</div>

		</div>

	</div>


</body>
</html>