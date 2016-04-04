<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div id="logIn">
	<div class="popupBoxWrapper">
		<a href="javascript:void(0)" onclick="toggle_visibility('logIn');" class="close" ><img  alt="Close" src="http://vignette4.wikia.nocookie.net/dynastywarriors/images/a/a5/X.png/revision/latest?cb=20131129190405" height="20px" width="20px"></a>
		<div class="popupBoxContent">
			<div class="row">
				<div class="col-sm-8" >
					<img alt="sky" src="http://www.statisticsviews.com/common/images/thumbnails/source/14096373997a.jpg" style="max-width:100%;max-height:100%;">
				</div>
				
				<div class="col-sm-4" >
					<h3>Log In</h3>
					<form action="logIn" method="post" id="userform">
						<p >
							<input type="text" name="username" id="username" placeholder="User Name" />
						</p>
						<p>
							<input type="password" name="pass" id="pass" placeholder="Password"  />
						</p>
						
						
						<p>
						<a href="javascript:void(0)" onclick="toggle_visibility('logIn');"><input type="submit" value="Log In" class="btn btn-primary"/></a>
						</p>
					</form>
				</div>
			</div>
			
		</div>
	</div>
</div>

<div id="register">
	<div class="popupBoxWrapper">
		<a href="javascript:void(0)" onclick="toggle_visibility('register');"class="close" ><img  alt="Close" src="http://vignette4.wikia.nocookie.net/dynastywarriors/images/a/a5/X.png/revision/latest?cb=20131129190405" height="20px" width="20px"></a>
			<div class="popupBoxContent">
				<h3>Register</h3>
				<form action="register" method="post" id="userform">
				
					<p><input type="text"	name="username" id="username" placeholder="username"/></p>
			
               	   	<p><input type="password" placeholder=" password" name="pass1" id="pass1" onkeyup="checkPass(); return false;"></p>
           
                	<p><input type="password" placeholder="confirm password" name="pass2" id="pass2" onkeyup="checkPass(); return false;"></p>
                	
                	<p><select name = "language" form="userform">
                		<option value="EN">English</option>
                		<option value="BU">Български</option>
                	</select></p>
					<p><img alt="sky" src="http://icons.wxug.com/i/c/a/partlycloudy.gif" ><input type="radio" name = "icon" value="a">
					<img alt="sky" src="http://icons.wxug.com/i/c/b/partlycloudy.gif" ><input type="radio" name = "icon" value="b"></p>
					<p><img alt="sky" src="http://icons.wxug.com/i/c/c/partlycloudy.gif" ><input type="radio" name = "icon" value="c">
					<img alt="sky" src="http://icons.wxug.com/i/c/d/partlycloudy.gif" ><input type="radio" name = "icon" value="d"></p>
					<p><img alt="sky" src="http://icons.wxug.com/i/c/e/partlycloudy.gif" ><input type="radio" name = "icon" value="e">
					<img alt="sky" src="http://icons.wxug.com/i/c/f/partlycloudy.gif" ><input type="radio" name = "icon" value="f"></p>
					<p><img alt="sky" src="http://icons.wxug.com/i/c/g/partlycloudy.gif" ><input type="radio" name = "icon" value="g">
					<img alt="sky" src="http://icons.wxug.com/i/c/h/partlycloudy.gif" ><input type="radio" name = "icon" value="h"></p>
					<p><img alt="sky" src="http://icons.wxug.com/i/c/i/partlycloudy.gif" ><input type="radio" name = "icon" value="i">
					<img alt="sky" src="http://icons.wxug.com/i/c/k/partlycloudy.gif" ><input type="radio" name = "icon" value="k"></p>
					
					<input  type="submit" value="Register"  class="btn btn-primary"/>
				</form>
			</div>
		</div>
	</div>
	
	
<script type="text/javascript" >

	function toggle_visibility(id) {
    var e = document.getElementById(id);
    if(e.style.display == 'block')
       e.style.display = 'none';
    else
       e.style.display = 'block';
}
</script>	
	