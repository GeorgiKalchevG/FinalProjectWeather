<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <script src="https://code.jquery.com/jquery-1.10.2.js"></script>

<div id="logIn">
	<div class="popupBoxWrapper">
		<a href="javascript:void(0)" onclick="toggle_visibility('logIn');" class="close" ><img  alt="Close" src="http://vignette4.wikia.nocookie.net/dynastywarriors/images/a/a5/X.png/revision/latest?cb=20131129190405" height="20px" width="20px"></a>
		<div class="popupBoxContent">
			<div class="row">
				<div class="col-sm-8" >
					<img alt="sky" src="http://www.statisticsviews.com/common/images/thumbnails/source/14096373997a.jpg" style="max-width:100%;max-height:100%;">
				</div>
				
				<div class="col-sm-4" >
					<h3><spring:message code="menu.login"></spring:message></h3>
					<form action="logIn" method="post" id="userform">
						<p >
							<input type="text" name="username" id="username" placeholder="Username" />
						</p>
						<p>
							<input type="password" name="pass" id="pass" placeholder="******"  />
						</p>
						
						
						<p>
						<!-- <a href="javascript:void(0)" onclick="toggle_visibility('logIn');"> --><input type="submit" value="<spring:message code="menu.login"/>" class="btn btn-primary"/><!-- </a> -->
						</p>
					</form>
					<spring:message code="menu.regOffer"></spring:message><a href="javascript:void(0)" onclick="toggle_visibility('register'); toggle_visibility('logIn');" ><spring:message code="menu.register"></spring:message></a>
				</div>
			</div>
			
		</div>
	</div>
</div>

<div id="register">
	<div class="popupBoxWrapper">
		<a href="javascript:void(0)" onclick="toggle_visibility('register');"class="close" ><img  alt="Close" src="http://vignette4.wikia.nocookie.net/dynastywarriors/images/a/a5/X.png/revision/latest?cb=20131129190405" height="20px" width="20px"></a>
			<div class="popupBoxContent">
				<h3><spring:message code="menu.register"/></h3>
				<form action="register" method="post" id="regform">
				
					<p><input type="text" autocomplete="off"	name="username" id="regUsername" placeholder="username"/><span id="check" class="glyphicon glyphicon-question-sign"></span> <spring:message code="register.username"></spring:message> </p>
			
               	   	<p><input type="password" disabled="disabled" autocomplete="off" placeholder="******" name="pass1" id="pass1" >	<spring:message code="register.pass"></spring:message></p>
           
                	<p><input type="password" disabled="disabled"  autocomplete="off" placeholder="******" name="pass2" id="pass2" > <spring:message code="register.passrep"></spring:message></p>
                </form>
               	<p>
               		<select name = "language" form="regform">
                		<option value="EN">English</option>
                		<option value="BU">Български</option>
                	</select>
               		<select name = "unit" form="regform">
                		<option value="C">&#8451;</option>
                		<option value="F">&#8457;</option>
               		</select>
               		<spring:message code="register.langAndUnit"></spring:message>
               	</p>
				<p><img alt="sky" src="http://icons.wxug.com/i/c/a/partlycloudy.gif" ><input type="radio" name = "icon" value="a" form="regform" checked="checked">
				<img alt="sky" src="http://icons.wxug.com/i/c/b/partlycloudy.gif" ><input type="radio" name = "icon" value="b" form="regform"></p>
				<p><img alt="sky" src="http://icons.wxug.com/i/c/c/partlycloudy.gif" ><input type="radio" name = "icon" value="c" form="regform">
				<img alt="sky" src="http://icons.wxug.com/i/c/d/partlycloudy.gif" ><input type="radio" name = "icon" value="d" form="regform"></p>
				<p><img alt="sky" src="http://icons.wxug.com/i/c/e/partlycloudy.gif" ><input type="radio" name = "icon" value="e" form="regform">
				<img alt="sky" src="http://icons.wxug.com/i/c/f/partlycloudy.gif" ><input type="radio" name = "icon" value="f" form="regform"></p>
				<p><img alt="sky" src="http://icons.wxug.com/i/c/g/partlycloudy.gif" ><input type="radio" name = "icon" value="g" form="regform">
				<img alt="sky" src="http://icons.wxug.com/i/c/h/partlycloudy.gif" ><input type="radio" name = "icon" value="h" form="regform"></p>
				<p><img alt="sky" src="http://icons.wxug.com/i/c/i/partlycloudy.gif" ><input type="radio" name = "icon" value="i" form="regform">
				<img alt="sky" src="http://icons.wxug.com/i/c/k/partlycloudy.gif" ><input type="radio" name = "icon" value="k" form="regform"><spring:message code="register.icon"></spring:message></p>
				
				<input form="regform"  type="submit" value="<spring:message code="menu.save"/>" id="regButton" class="btn btn-primary" disabled="disabled"/>
		</div>
	</div>
</div>
<div id="edit">
	<div class="popupBoxWrapper">
		<a href="javascript:void(0)" onclick="toggle_visibility('edit');"class="close" ><img  alt="Close" src="http://vignette4.wikia.nocookie.net/dynastywarriors/images/a/a5/X.png/revision/latest?cb=20131129190405" height="20px" width="20px"></a>
			<div class="popupBoxContent">
				<h3><spring:message code="menu.edit"/></h3>
				<form action="edit" method="post" id="editform">
				
					<p><input type="text" autocomplete="off"	name="oldPass" id="username" placeholder="Old password"/></p>
			
               	   	<p><input type="password" autocomplete="off" placeholder="New password" name="pass1" id="pass1" onkeyup="checkPass(); return false;"></p>
           
                	<p><input type="password"  autocomplete="off" placeholder="Confirm new password" name="pass2" id="pass2" onkeyup="checkPass(); return false;"></p>
                </form>
               	<p>
               		<select name = "language" form="editform">
                		<option value="EN">English</option>
                		<option value="BU">Български</option>
                	</select>
               		<select name = "unit" form="editform">
                		<option value="C">&#8451;</option>
                		<option value="F">&#8457;</option>
               		</select>
               	</p>
				<p><img alt="sky" src="http://icons.wxug.com/i/c/a/partlycloudy.gif" ><input type="radio" name = "icon" value="a" form="editform" checked="checked">
				<img alt="sky" src="http://icons.wxug.com/i/c/b/partlycloudy.gif" ><input type="radio" name = "icon" value="b" form="editform"></p>
				<p><img alt="sky" src="http://icons.wxug.com/i/c/c/partlycloudy.gif" ><input type="radio" name = "icon" value="c" form="editform">
				<img alt="sky" src="http://icons.wxug.com/i/c/d/partlycloudy.gif" ><input type="radio" name = "icon" value="d" form="editform"></p>
				<p><img alt="sky" src="http://icons.wxug.com/i/c/e/partlycloudy.gif" ><input type="radio" name = "icon" value="e" form="editform">
				<img alt="sky" src="http://icons.wxug.com/i/c/f/partlycloudy.gif" ><input type="radio" name = "icon" value="f" form="editform"></p>
				<p><img alt="sky" src="http://icons.wxug.com/i/c/g/partlycloudy.gif" ><input type="radio" name = "icon" value="g" form="editform">
				<img alt="sky" src="http://icons.wxug.com/i/c/h/partlycloudy.gif" ><input type="radio" name = "icon" value="h" form="editform"></p>
				<p><img alt="sky" src="http://icons.wxug.com/i/c/i/partlycloudy.gif" ><input type="radio" name = "icon" value="i" form="editform">
				<img alt="sky" src="http://icons.wxug.com/i/c/k/partlycloudy.gif" ><input type="radio" name = "icon" value="k" form="editform"></p>
				
				<input form="editform"  type="submit" value="<spring:message code="menu.save"/>"  class="btn btn-primary"/>
		</div>
	</div>
</div>
	
	
<script >

	function toggle_visibility(id) {
    var e = document.getElementById(id);
    if(e.style.display == 'block')
       e.style.display = 'none';
    else
       e.style.display = 'block';
}
</script>	
<script >
$('#check').on('click',
		function(){
		
		var username = $('#regUsername').val();
		console.log(username);
			$.ajax({
				url : "checkUsername",
				type : 'POST',
				data : {
					username : username
					
				},
				success : function(response) {
					console.log(response);
					if(response){
						 $( "#check" ).html( "<span class=\"glyphicon glyphicon-thumbs-up\"></span>");
						
						 $('#pass1').removeAttr('disabled');
						 $('#pass2').removeAttr('disabled');
						
					}else{
						 $( "#check" ).html( "<span class=\"glyphicon glyphicon-thumbs-down\"></span>");
						 $('#regButton').attr('disabled','disabled');
						 $('#pass1').attr('disabled','disabled');
						 $('#pass2').attr('disabled','disabled');
					}
				},
				fail : function() {
		
				}
			});

		});

$('#pass1,#pass2').on('keyup', function(){
	var pass1 = $('#pass1').val();
	var pass2 = $('#pass2').val();
	var regex = /^(?![0-9]{6})[0-9a-zA-Z]{6,}$/;
	console.log(pass1);
	console.log(pass2);
	console.log(regex.test(pass1));
	if(pass1===pass2&&regex.test(pass1)){
		 $('#regButton').removeAttr('disabled');
	}
	else{
		$('#regButton').attr('disabled','disabled');
	}
});
	
</script>	
	