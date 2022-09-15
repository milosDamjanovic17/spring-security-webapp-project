<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"%>    
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>luv2code Company Home Page</title>
</head>
<body>

	<h2>luv2code Company Home Page ASDF</h2>
	<hr>
	
	<hr>
	
	<!-- display username and role -->
	
	<p>
		User: <security:authentication property="principal.username"/> <!-- displays userID -->
		<br><br>
		Role(s) <security:authentication property="principal.authorities"/> <!-- displays list of roles list assigned to user -->
	</p>
	
	<hr>
	
	<!-- Add a security parametere where only assigned Roles can see the hyperlink -->
	<security:authorize access="hasRole('MANAGER')">	
		<!-- Add a link to point to /leaders .. this is for the managers -->	
		<p>
			<a href="${pageContext.request.contextPath }/leaders">Leadership Meeting</a>
			(Only for Manager peeps)
		</p>
	</security:authorize>
	<hr>
	
	<!-- Add a security parameter where only assigned Roles can see the hyperlink, in this case role("ADMIN") -->
		<!-- Add a link to point to /systems ... this is for ADMINS -->
	<security:authorize access="hasRole('ADMIN')">	
		<p>
			<a href="${pageContext.request.contextPath }/systems">IT SYSTEMS Meeting</a>
			(Only for Admin peeps)
		</p>
	</security:authorize>
	<hr>
	
	<p>
	Welcome to the luv2code company home page!
	</p>

	<!-- ADDING LOGOUT BUTTON -->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	
		
		
		<input type="submit" value="Logout"/>
	
	</form:form>
	
	
	
	

</body>
</html>