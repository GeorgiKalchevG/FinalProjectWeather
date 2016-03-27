<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<link rel="stylesheet" type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" />

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
 
<div class="row">
	<div class="col-sm-12">
		<h1><spring:message code="planner.greatemessage"/> </h1>

				
				
				<form class="form-inline" role="form" action="search" method="post">
				<div class="col-xs-4">
					<label for="from"><spring:message  code="planner.start"/>: </label> <input type="text" id="from" name="from" />
				</div>
				<div class="col-xs-4">
					<label for="to"><spring:message  code="planner.end"/>: </label><input type="text" id="to" name="to" >
				</div>
				<div class="col-xs-4">
					<label for="select"><spring:message  code="planner.select"/>: </label><input id="search-city1" type="text" placeholder="search city" class="form-control1" id="email" name="city"></div>
				
					</form>
					<div class="cities1">
						<ul class="search-result1"></ul>
					</div>
		 	
	</div>
</div>
<style>
.row {
	width: 75%;
	margin: 0 auto;
}

.navbar-inverse {
	margin-bottom: 0;
}

.container-fluid.main-nav {
	line-height: 75px;
	height: 75px;
}

.cities1 {
	height: 0px;
	overflow: scroll;
	position: absolute;
	top: 55px;
	left: 399px;
	text-align: left;
	z-index: 2;
	background-color: white;
	line-height: 30px;
	transition-property: all;
	transition-duration: .5s;
	transition-timing-function: cubic-bezier(0, 1, 0.5, 1);
}

.cities1.opened1{
	overflow: hidden;
	height: 150px;
	transition-property: all;
	transition-duration: .5s;
	transition-timing-function: cubic-bezier(0, 1, 0.5, 1);
}

.search-result1 {
	height: 150px;
	overflow-y: scroll;
	list-style-type: none;
	padding: 5px;
}

.search-result1 li {
	border-bottom: 1px solid rgb(200, 200, 200);
}

.search-result1 li:hover {
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
    var dateToday = new Date();
    var dates = $("#from, #to").datepicker({
       
        changeMonth: true,
        numberOfMonths: 1,
        minDate: dateToday,
        onSelect: function(selectedDate) {
            var option = this.id == "from" ? "minDate" : "maxDate",
                instance = $(this).data("datepicker"),
                date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
            dates.not(this).datepicker("option", option, date);
        }
    });
    
    

	/* $('.open-additional-info').on('click', function() {
		$('.additional-info').toggleClass('opened');
		console.log('ds');
	}); */

	$('#search-city1')
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
											$('.search-result1').text('');
											for ( var city in cities) {
												var name = cities[city].name;
												// Create a variable to contain the array
												var names = name.split(", ");

												var list = '<li class="searched-city1" city="' + names[0] + '" country="'+ names[1] +'">'
														+ cities[city].name
														+ '</li>';
												$('.search-result1')
														.append(list);
											}
											$('.cities1').addClass('opened1');

										},
										fail : function() {

										}
									});
						}
					});

	$('.search-result1','.from','.to').on('click', '.searched-city1','.from','.to', function() {
		$.ajax({
			url : "plan",
			type : 'POST',
			data : {
				form : $(this).attr('from'),
				to : $(this).attr('to'),
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
</script>