<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<!--

$(function() {
    $( '#from' ).datepicker({
        dateFormat: 'dd-mm-yy',
        minDate: '+5d',
        changeMonth: true,
        changeYear: true,
        altField: "#idTourDateDetailsHidden",
        altFormat: "yy-mm-dd"
    });
  });

//-->






<div class="row">
	<div class="col-sm-12">

		<h1>
			<spring:message code="planner.greatemessage" />
		</h1>

	</div>
</div>
<div class="row">
	<div class="col-sm-12">

		<form class="form-inline" role="form" action="plan" method="post"class="date">
			<div class="col-xs-3">
				<label for="from"><spring:message code="planner.start" />:
				</label> <input class="from" type="text" id="from" name="from"/>
			</div>
			<div class="col-xs-3">
				<label for="to"><spring:message code="planner.end" />: </label><input class="to" type="text" id="to" name="to">
			</div>
			<div class="col-xs-3">

				<label for="select"><spring:message code="planner.select" />:
				</label><input id="search-city1" type="text" placeholder="search city"class="form-control1" id="email" name="city">
			</div>
			<div class="col-xs-3">
			<label for="select">Submit your query </label>
				<input class="search-for-city" type="submit" id="searchButton" value="		GO!			">
			</div>
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

.cities1.opened1 {
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
console.log(dateToday);
/*  var dates =  $("#from").datepicker(
				{

					changeMonth : true,
					numberOfMonths : 1,
					minDate : dateToday
					onSelect : function(selectedDate) {
						var option = this.id == "from" ? "minDate": "maxDate", instance = $(this).data(
								"datepicker"), date = $.datepicker.parseDate(	instance.settings.dateFormat|| $.datepicker._defaults.dateFormat,
										selectedDate, instance.settings);
						dates.not(this).datepicker("option", option, date);
					}  
					
				});

var limitTo = "";
 */
 jQuery.noConflict(true);
 (function( $ ) {
$(function() {

    $( "#from" ).datepicker({
      minDate: dateToday,
      changeMonth: true,
      numberOfMonths: 1,
      onClose: function( selectedDate ) {
        $( "#to" ).datepicker( "option", "minDate", selectedDate);
        console.log(selectedDate);
        var max = new Date(selectedDate);
      
     	max.setDate(max.getDate()+29);
        console.log(max);
        $( "#to" ).datepicker( "option", "maxDate", max);
        console.log(selectedDate);
      }
    });
    $( "#to" ).datepicker({
      
      changeMonth: true,
      numberOfMonths: 1,
      onClose: function( selectedDate ) {
        $( "#from" ).datepicker( "option", "maxDate", selectedDate );
      }
    });

 });

 })(jQuery);
/* $('.from').on('change', function(){
	console.log($(".from").val())
	limitTo=$(".from").val();
	var selectedDate = new Date(limitTo);
	console.log(selectedDate.toString());
	
	
});
$(".to").datepicker(
		{

			changeMonth : true,
			numberOfMonths : 1,
			minDate : dateToday
		}); */

/* $("#to").datepicker(
		{

			changeMonth : true,
			numberOfMonths : 1,
			minDate : selectedDate
			 maxDate : +30 
		onSelect : function(selectedDate) {
				var option = this.id == "from" ? "minDate": "maxDate", instance = $(this).data(
						"datepicker"), date = $.datepicker
						.parseDate(	instance.settings.dateFormat|| $.datepicker._defaults.dateFormat,
								selectedDate, instance.settings);
				dates.not(this).datepicker("option", option, date);
			} 
		})
		 */
				/* console.log(dates); */ 

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
											$('.search-result1').append(
													list);
										}
										$('.cities1').addClass('opened1');

									},
									fail : function() {

									}
								});
					}
				});
$('.search-result1').on('click', '.searched-city1', function() {
	$('#search-city1').val($(this).text());
	$('.cities1').removeClass('opened1');
});
$(".search-for-city").on('click',function() {
			var names = $('#search-city1').val().split(", ");

			var dateFrom = $(".from").val();
			var dateTo = $(".to").val();
			if (names.length <= 0) {
				var city = '';
				var country = '';
			} else {
				var city = names[0];
				var country = names[1];
			}

			if (dateFrom.lenght <= 0 || dateTo.lenght <= 0
					|| city.lenght <= 0 || country.lenght <= 0) {
				console.log('molq popylnete vsicki poleta');
			}	
			
		});
</script>