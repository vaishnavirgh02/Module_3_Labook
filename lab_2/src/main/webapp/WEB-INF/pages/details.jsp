<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<body>

<a href="/">Go Home</a>

<h2>
Id is  <c:out value="${customer.id}"/>
Name is <c:out value="${customer.name}"/>

</h2>
</body>
</html>