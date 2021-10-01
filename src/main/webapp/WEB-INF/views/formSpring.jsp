<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:radiobuttons items="${ genre }" path="sexe"/>
<form:select path="personnes" items="${ personnes }"multiple="true" />


</body>
</html>