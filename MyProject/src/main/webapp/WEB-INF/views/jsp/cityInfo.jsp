<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row">
		<div class="main-container">
			<div class="col-sm-8" style="background-color: lavender;">
				<div class="container">
					<h2>${city}</h2>
					<h5>Posledno update time za toq grad</h5>
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#home">${city}</a></li>
						<li><a data-toggle="tab" href="#menu1">24 hours</a></li>
						<li><a data-toggle="tab" href="#menu2">Weekend</a></li>
						<li><a data-toggle="tab" href="#menu3">5-days</a></li>
					</ul>
					<div class="tab-content">
						<div id="home" class="tab-pane fade in active">
							<div class="table-wrapper">
								<div class="main-info">
									<table class="table" style="width: 75%;">
										<tr>
											<!-- trqbwa da napravim kolekciq ot gif za razli4noto vreme -->
											<th rowspan="4"
												background="http://www.auplod.com/u/ulopad78451.gif"
												width="240" height="160"><img alt="" src="" />
												<h3 style="color: white;">Max Temp:
												<c:set var="ff" scope="session" value="${blqblq}" />
												
												${ ff =='true' ?  list[0].tempHighCel : list[0].tempHighFahr}
												<!---	${list[0].tempHighCel} &#8451---></h3></th>
											<c:forEach items="${list}" var="entry">
												<th><img src="${entry.icon_url}">
													${entry.weekday}</th>
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
									</table>
								</div>
								<div id="open-additional-info" class="open-additional-info">
									Open info</div>
								<div class="additional-info">
									<table class="table" style="width: 75%;">
										<tr>
											<th><spring:message code="weather.wind"/></th>
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
							</div>

						</div>
						<div id="menu1" class="tab-pane fade" style="overflow: auto;">
							<table class="table"
								style="width: 75%; display: block; overflow-x: auto;">
								<tr>
									<!-- trqbwa da napravim kolekciq ot gif za razli4noto vreme -->
									<th rowspan="1" width="240" height="160"><img alt=""
										src="" />
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
									href="#collapse1">${queueforCities[0].cityName}</a>
							</h4>
						</div>
						<div id="collapse1" class="panel-collapse collapse in">
							<div class="panel-body">${queueforCities[0].tempHighCel}</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse2">${queueforCities[1].cityName}</a>
							</h4>
						</div>
						<div id="collapse2" class="panel-collapse collapse">
							<div class="panel-body">${queueforCities[1].tempHighCel}</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse3">${queueforCities[2].cityName}</a>
							</h4>
						</div>
						<div id="collapse3" class="panel-collapse collapse">
							<div class="panel-body">${queueforCities[2].tempHighCel}</div>
						</div>
					</div>
				</div>

			</div>

		</div>
		

		</div>
		
<script>
	$('.open-additional-info').on('click', function() {
		$('.additional-info').toggleClass('opened');
	});
</script>
	