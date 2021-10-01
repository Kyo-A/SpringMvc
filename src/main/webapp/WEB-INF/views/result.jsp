<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result page</title>
</head>
<body>
	<h1>List of persons</h1>
	<c:forEach items="${ personnes }" var="personne">
		<div>
			<c:out value="${ personne.prenom } ${ personne.nom }" />
		</div>
	</c:forEach>
</body>
</html>