<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Person form</title>
</head>
<body>
	Bonjour ${ perso.nom }
	<h1>Person form</h1>
	<form:form modelAttribute="personne" action="personne" method="post">
		<form:label path="nom">nom</form:label>
		<form:input path="nom" />
		<form:errors path="nom" cssClass="error" />
		<form:label path="prenom">prenom</form:label>
		<form:input path="prenom" />
		<form:errors path="prenom" cssClass="error" />
		<input type="submit" value="Ajouter">
	</form:form>
	<a href="deconnect"> deconnexion </a>
</body>
</html>