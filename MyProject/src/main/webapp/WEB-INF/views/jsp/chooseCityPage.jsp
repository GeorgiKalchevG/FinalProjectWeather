<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>${city}</h1>
<h3> This city name is available in different countries. Please choose one:</h3>
<table class="table" style="width: 75%;">
<c:forEach items="${availableCities}" var="entry">
<tr>
<form action="searchInSpecificCountry" method="post">
<input name="country" type="hidden" value="${entry.countryName}">
<th><button type="submit" class="btn btn-default">${entry.countryName}</button>
</th>
</form>
</tr>
</c:forEach>
</table>
</body>
</html>