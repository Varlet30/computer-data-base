<!DOCTYPE HTML PUBLIC  "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>

<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:message code=""/>
<html lang="en">
<head>
	<title>Login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<spring:url value="/resources/css/login.css" var="loginCss" />
	<spring:url value="/resources/images/batman-icon.png" var="batman" />
	<link href="${loginCss}" rel="stylesheet" media="screen">

</head>
<body>

<h2>Login to Computer Database</h2>
<form name='f' action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'>
<!-- /login?error=true -->
     <c:if test="${param.error == 'true'}">
         <div style="color:red;margin:10px 0px;">

                Login Failed<br />
                Reason :  ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

         </div>
    </c:if>

  <div class="container">
    <label for="username"><strong>Username</strong></label>
    <input id="username" type="text" placeholder="Enter Username" name="username" required>

    <label for="password"><strong>Password</strong></label>
    <input id="password" type="password" placeholder="Enter Password" name="password" required>

    <button id="login" type="submit">Login</button>
    <label>
      <input type="checkbox" checked="checked" name="remember"> Remember me
    </label>
  </div>
</form>

</body>
</html>