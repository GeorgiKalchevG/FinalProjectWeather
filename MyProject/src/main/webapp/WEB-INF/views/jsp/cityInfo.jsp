<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="https://cdn.plot.ly/plotly-latest.min.js"></script> 
<c:set var="whatUnit" scope="session" value="${units}" />
<div class="row">
	<div class="col-sm-12"style="background-color: azure; border: solid; border-width: 1px;">
		<c:if test="${not empty user}">
			<c:if test="${not empty user.locations}">
				<div class="roll">
		
					<ul class="list-group" id="favLocations" onload="loadFavs();" style="margin: 10px;"><spring:message code="favourites.list" />
					
					</ul>
				</div>
			</c:if>
		</c:if>
	</div>
</div>
<div class="row">
	<div class="main-container">
		<div class="col-sm-8"
			style="background-color: azure; border: solid; border-width: 1px;">
			<div class="container-fluid">

				<h2>
					<span id="location-holder">${city}</span>
					<c:if test="${not empty user}">

						<c:if test="${not user.locations.contains(city)}">
							<span id="favIt" class="glyphicon glyphicon-heart"></span>
						</c:if>
					</c:if>
				</h2>
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#home">${city}</a></li>
					<li><a data-toggle="tab" href="#menu1"><spring:message
								code="menu.24hours" /></a></li>
					<li><a data-toggle="tab" href="#menu2"><spring:message
								code="menu.weekend" /></a></li>
					<li><a data-toggle="tab" href="#menu3"><spring:message
								code="menu.5days" /></a></li>
				</ul>
				<div class="tab-content">
					<div id="home" class="tab-pane fade in active">
						<div class="table-wrapper">
							<div class="main-info">
								<table class="table" style="width: 100%;">
							
									<tr>
										<!-- "${backGroundGIF}" -->
										<th rowspan="4" <%-- background=" --%>
											style="background-repeat: no-repeat;"><img alt="" src="<c:url value="${backGroundGIF}" />" />
											<h5>
												<c:set var="whatUnit" scope="session" value="${units}" />
												<spring:message code="weather.temp" />
												: ${ whatUnit =='true' ? list24hours[0].tempC : list24hours[0].tempFH}
												${unitTemp}
												<spring:message code="weather.feels_like" />
												: ${ whatUnit =='true' ? list24hours[0].feelsLikeC  : list24hours[0].feelsLikeFH}
												${unitTemp} ${list24hours[0].conditions}
											</h5></th>
										<c:forEach items="${list3days}" var="entry">
											<th><img src="${entry.icon_url}"> ${entry.weekday}</th>
										</c:forEach>
									</tr>
									<tr>
										<c:forEach items="${list3days}" var="entry">
											<th>${entry.day}.${entry.month}.${entry.year}</th>
										</c:forEach>

									</tr>
									<tr>
										<c:forEach items="${list3days}" var="entry">
											<th>${entry.conditions}</th>
										</c:forEach>

									</tr>
									<tr>
										<c:forEach items="${list3days}" var="entry">
											<th>
												<p>
													<spring:message code="weather.max_temp2" />
													: ${ whatUnit =='true' ? entry.tempHighCel  : entry.tempHighFahr}
													${unitTemp}
													<spring:message code="weather.min_temp2" />
													: ${ whatUnit =='true' ? entry.tempLowCel : entry.tempLowFahr}
													${unitTemp}
													<!-- &#8451 the special symbol -->
											</th>
										</c:forEach>
									</tr>
								</table>
							</div>
							<div id="open-additional-info" class="open-additional-info" style="text-align: center;">
								<spring:message code="table.moreInfo" />
							</div>
							<div class="additional-info">
								<table class="table" style="width: 100%;">
									<tr>
										<th><spring:message code="weather.wind" />:</th>
										<c:forEach items="${list3days}" var="entry">
											<th><spring:message code="weather.windspeed" />: ${ whatUnit =='true' ? entry.maxwind_kph  : entry.maxwind_mph}
												${unitSpeed} <spring:message code="weather.winddirection" />:
												${entry.maxwind_dir}</th>
										</c:forEach>
									</tr>
									<tr>
										<th><spring:message code="weather.rain_chance" />:</th>
										<c:forEach items="${list3days}" var="entry">
											<th>${entry.pop}%</th>
										</c:forEach>
									</tr>
									<tr>
										<th><spring:message code="weather.quantity_of_rain" />:</th>
										<c:forEach items="${list3days}" var="entry">
											<th>${ whatUnit =='true' ? entry.qpf_alldayMM  : entry.qpf_alldayIn}
												${unitMM}</th>
										</c:forEach>
									</tr>
									<tr>
										<th><spring:message code="weather.humidity" />:</th>
										<c:forEach items="${list3days}" var="entry">
											<th>${entry.avehumidity}%</th>
										</c:forEach>
									</tr>
								</table>
							</div>
						</div>

					</div>
					<div id="menu1" class="tab-pane fade" style="overflow: auto;">
						<table class="table"
							style="width: 100%; display: block; overflow-x: auto;">
							<tr>
								<!-- trqbwa da napravim kolekciq ot gif za razli4noto vreme -->
								<th rowspan="1" width="240" height="160"><img alt="" src="" />
									<h3 style="color: black;">
										<spring:message code="weather.forcast_for" />
										${list24hours[0].day}.${list24hours[0].month}.${list24hours[0].year}
									</h3></th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.hour}:00<img src="${entry.icon_url}">
									</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.temp" /> :</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${ whatUnit =='true' ? entry.tempC  : entry.tempFH}
										${unitTemp}</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.feels_like" /> :</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${ whatUnit =='true' ? entry.feelsLikeC  : entry.feelsLikeFH}
										${unitTemp}</th>
								</c:forEach>

							</tr>
							<tr>
								<th><spring:message code="weather.sky" /> :</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.conditions}</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.wind" /> :</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.windKPH}${unitSpeed}</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.rain_chance" /> :</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.pop}%</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.quantity_of_rain" /> :</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${ whatUnit =='true' ? entry.qpfMM  : entry.qpfIN}
										${unitMM}</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.pressure" /> :</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.mslphPa}hPa</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.humidity" /> :</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.humidity}%</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.cloudy" /> :</th>
								<c:forEach items="${list24hours}" var="entry">
									<th>${entry.sky}%</th>
								</c:forEach>
							</tr>
						</table>
					</div>
					<div id="menu2" class="tab-pane fade">
						<table class="table"
							style="width: 100%; display: block; overflow-x: auto;">
							<tr>
								<!-- trqbwa da napravim kolekciq ot gif za razli4noto vreme -->
								<th rowspan="4" width="240" height="160"
									style="background-repeat: no-repeat;"><img alt="" src="" />
									<h3 style="color: white;"></h3></th>
								<c:forEach items="${listweekenddays}" var="entry">
									<th><img src="${entry.icon_url}"> ${entry.weekday}</th>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${listweekenddays}" var="entry">
									<th>${entry.day}.${entry.month}.${entry.year}</th>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${listweekenddays}" var="entry">
									<th>${entry.conditions}</th>
								</c:forEach>

							</tr>
							<tr>
								<c:forEach items="${listweekenddays}" var="entry">
									<th>
										<p>
											<spring:message code="weather.max_temp2" />
											: ${ whatUnit =='true' ? entry.tempHighCel  : entry.tempHighFahr}
											${unitTemp}
											<spring:message code="weather.min_temp2" />
											: ${ whatUnit =='true' ? entry.tempLowCel : entry.tempLowFahr}
											${unitTemp}
									</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.wind" /></th>
								<c:forEach items="${listweekenddays}" var="entry">
									<th>${ whatUnit =='true' ? entry.maxwind_kph  : entry.maxwind_mph}</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.rain_chance" /> :</th>
								<c:forEach items="${listweekenddays}" var="entry">
									<th>${entry.pop}%</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.quantity_of_rain" /> :</th>
								<c:forEach items="${listweekenddays}" var="entry">
									<th>${ whatUnit =='true' ? entry.qpf_alldayMM  : entry.qpf_alldayIn}
										${unitMM}</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.humidity" /> :</th>
								<c:forEach items="${listweekenddays}" var="entry">
									<th>${entry.avehumidity}%</th>
								</c:forEach>
							</tr>
						</table>
					</div>
					<div id="menu3" class="tab-pane fade">
						<table class="table"
							style="width: 100%; display: block; overflow-x: auto;">
							<tr>
								<!-- trqbwa da napravim kolekciq ot gif za razli4noto vreme -->
								<th rowspan="4" width="240" height="160"
									style="background-repeat: no-repeat;"><img alt="" src="" />
									<h3 style="color: white;"></h3></th>
								<c:forEach items="${list5days}" var="entry">
									<th><img src="${entry.icon_url}"> ${entry.weekday}</th>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${list5days}" var="entry">
									<th>${entry.day}.${entry.month}.${entry.year}</th>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${list5days}" var="entry">
									<th>${entry.conditions}</th>
								</c:forEach>

							</tr>
							<tr>
								<c:forEach items="${list5days}" var="entry">
									<th>
										<p>${ whatUnit =='true' ? entry.tempHighCel  : entry.tempHighFahr}
											${unitTemp}
									</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.wind" /></th>
								<c:forEach items="${list5days}" var="entry">
									<th>${ whatUnit =='true' ? entry.maxwind_kph  : entry.maxwind_mph}
										${unitSpeed}</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.rain_chance" /> :</th>
								<c:forEach items="${list5days}" var="entry">
									<th>${entry.pop}%</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.quantity_of_rain" /> :</th>
								<c:forEach items="${list5days}" var="entry">
									<th>${ whatUnit =='true' ? entry.qpf_alldayMM  : entry.qpf_alldayIn}
										${unitMM}</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="weather.humidity" /> :</th>
								<c:forEach items="${list5days}" var="entry">
									<th>${entry.avehumidity}%</th>
								</c:forEach>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
</div>
		<div class="col-sm-4"
			style="background-color: azure; border: solid; border-width: 1px;">
			<h2>
				<spring:message code="sidemenu.lastvisited" />
			</h2>
			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapse1" id="lastSearched1">
							${queueforCities[0].countryName}/${queueforCities[0].cityName}</a>
							<span class="glyphicon glyphicon-search lastVisited" data-city="${queueforCities[0].cityName}" data-country="${queueforCities[0].countryName}"></span>
							
						</h4>
					</div>
					<div id="collapse1" class="panel-collapse collapse in">
						<div class="panel-body">
							<spring:message code="weather.max_temp2" />
							: ${ whatUnit =='true' ? queueforCities[0].tempHighCel  : queueforCities[0].tempHighFahr}
							${unitTemp}
							<spring:message code="weather.min_temp2" />
							: ${ whatUnit =='true' ? queueforCities[0].tempLowCel : queueforCities[0].tempLowFahr}
							${unitTemp}. ${queueforCities[0].conditions}
							
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapse2"></a>
							<c:if test="${not empty queueforCities[1].countryName}">
									<a data-toggle="collapse" data-parent="#accordion" id="lastSearched2"
										href="#collapse2">${queueforCities[1].countryName}/${queueforCities[1].cityName}</a>
								
										<span class="glyphicon glyphicon-search lastVisited" data-city="${queueforCities[1].cityName}" data-country="${queueforCities[1].countryName}"></span>
												</c:if>
						</h4>
					</div>
					<div id="collapse2" class="panel-collapse collapse">
						<div class="panel-body">
							<spring:message code="weather.max_temp2" />
							: ${ whatUnit =='true' ? queueforCities[1].tempHighCel  : queueforCities[1].tempHighFahr}
							${unitTemp}
							<spring:message code="weather.min_temp2" />
							: ${ whatUnit =='true' ? queueforCities[1].tempLowCel : queueforCities[1].tempLowFahr}
							${unitTemp}. ${queueforCities[1].conditions}
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"	href="#collapse3"></a>
							<c:if test="${not empty queueforCities[2].countryName}">
									<a data-toggle="collapse" data-parent="#accordion" id="lastSearched3"
										href="#collapse3">${queueforCities[2].countryName} /
										${queueforCities[2].cityName}</a>
									
										<span class="glyphicon glyphicon-search lastVisited" data-city="${queueforCities[2].cityName}" data-country="${queueforCities[2].countryName}"></span>
											</c:if>
						</h4>
					</div>
					<div id="collapse3" class="panel-collapse collapse">
						<div class="panel-body">
							<spring:message code="weather.max_temp2" />
							: ${ whatUnit =='true' ? queueforCities[2].tempHighCel  : queueforCities[2].tempHighFahr}
							${unitTemp}
							<spring:message code="weather.min_temp2" />
							: ${ whatUnit =='true' ? queueforCities[2].tempLowCel : queueforCities[2].tempLowFahr}
							${unitTemp}. ${queueforCities[2].conditions}
						</div>
					
				</div>

			</div>

		</div>


	</div>



</div>
<div class="row">
		<div class="col-sm-8">
		 <div id="myDiv" style="width:100%; height:100%; align: center;"></div>
	</div>
</div> 

	<script>
$(window).on('load',buildGraph());	
	
function buildGraph(){
	var h=[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23];
	var t=[];
	var hu=[];
	var c=[];

	<c:forEach items="${list24hours}" var="listItem">

		t.push("<c:out value="${listItem.tempC}" />");
		hu.push("<c:out value="${listItem.humidity}" />");
		c.push("<c:out value="${listItem.sky}" />");
		
	</c:forEach>
		
	
	
	console.log(h);
	console.log(t);
	console.log(c);
	console.log(hu);
	
	
	
	var trace1 = {
			  x: h,
			  y: hu,
			  mode: 'markers',
			  name: '&#9926;',
			  marker: {
				    
				    size: 8
				  },
			};

	var trace2 = {
	  x: h,
	  y: t,
	  mode: 'lines',
	  name: '&#8451;',
	};
	
	var trace3 = {
	  x: h,
	  y: c,
	  mode: 'lines+markers',
	  name:  '&#9729;',
	  marker: {
		    color: 'rgb(166, 166, 166)',
		    size: 5
		  },
	  
	};

			var data = [ trace1, trace2, trace3 ];

			var layout = {
			  title:''
			
			};

			Plotly.newPlot('myDiv', data, layout);
}
	
	
		$('.open-additional-info').on('click', function() {
			$('.additional-info').toggleClass('opened');
		});

		$('#favIt').on('click', function() {
			var loc = $('#location-holder').text();
			console.log("from remove js" + loc);
			$.ajax({
				url : "addFavourite",
				type : 'POST',
				data : {
					location : loc,

				},
				success : function() {
					$('span#favIt').hide();

				}
			});
			location.reload(true);
		});

		

		
		
		$('.list-group').on(
				'click',
				'.remove',
				function() {
					
		var cityData = $(this).data("city");
		var loc = $('.list-item[data-city="' + cityData + '"]').text();
		console.log(cityData);
		console.log('removing');
		console.log(loc);
		$.ajax({
			url : "removeFavorite",
			type : 'POST',
			data : {
				location : loc,

						},
						success : function() {
							$('span#myLocation ').hide();
							location.reload(true);
						}
					});

				});
		
		
			
		$('.panel-group').on('click','.lastVisited', function(event) {
			event.preventDefault();
			console.log("click");
			$.ajax({
			
				url : "search",
				type : 'POST',
				data : {
					city : $(this).attr('data-city'),
					country : $(this).attr('data-country'),
					fromAjax : 'aaa'
				},
				success : function(response) {
					$('.cities').removeClass('opened');
					$(".city-weather").text('');
					$(".city-weather").append(response);
					buildGraph();
				},
				fail : function() {

				}
			});

		});
		
		
		
		$('.list-group').on(
				'click',
				'.list-item',
				function() {
					
		var cityData = $(this).data("city");
		var loc = $('.list-item[data-city="' + cityData + '"]').text();
		console.log(cityData);
		console.log('showing city info');
		console.log(loc);
		$.ajax({
			url : "search",
			type : 'POST',
			
			data : {
				city : loc.split(", ")[0],
				country : loc.split(", ")[1],
				fromAjax : 'aaa'

						},
						success : function(response) {
							$('.cities').removeClass('opened');
							$(".city-weather").text('');
							$(".city-weather").append(response);
						}
					});

				});
		
		
		
		
		
		
		
	
		var favorites = '${user.locations}'.replace("[", "").replace("]", "");
		var favsArray = favorites.split(", ");
		function loadFavs(favs) {
			this.favorites = favs;
			console.log(favorites);
			$('#favLocations').text('');
			var countryAndCity = "";

			for (i = 0; i < favorites.length; i++) {
				(function(i) {

					console.log(favorites[i]);

					countryAndCity = favorites[i];
					var kelvin = 272.15;
					var countryAndCitySpit = countryAndCity.split("/");
					console.log(countryAndCitySpit);
					var country = countryAndCitySpit[0];
					var city = countryAndCitySpit[1];
					country = country.replace(" ", "+");
					city = city.replace(" ", "+");
					console.log(city);
					var url = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=9c4edfe458aa40db973185430160704&q="
							+ city
							+ ","
							+ country
							+ "&num_of_days=1&fx=no&format=json";
					console.log(url);
					$
							.ajax({
								url : url,
								type : 'POST',
								data : {},
								success : function(response) {

									var units = $
									{
										units
									}
									;
									
								countryAndCity = response.data.request[0].query;
								list = '<li style="display:inline; margin-left:5px;cursor: pointer;" id="myLocation" class="list-item" data-city="'+i+'" ><span id="remove" data-city="'+i+'" class="glyphicon glyphicon-remove remove"></span>'
										+ countryAndCity
										+ ', '
										+ (units === true ? response.data.current_condition[0].temp_F
												+ '&#8457;'
												: response.data.current_condition[0].temp_C
														+ '&#8451;' + '</li>')
								$('#favLocations').append(list);
								console.log(countryAndCity);
								i++;
								console.log(i);
							},
							fail : function() {
							}
						});
			}(i));

			}
		}
		loadFavs(favsArray);		
	</script>
