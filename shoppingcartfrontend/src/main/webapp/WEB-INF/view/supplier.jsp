<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <html>
<head>
<title>Supplier page</title>
</head>
<body>
<h1>Add a Supplier</h1>
<c:url var="addAction" value ="/supplier/add"></c:url>
<form:form action="${addAction}" commandName="supplier"></form:form>
<table></table>
</body>
</html>