<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page isELIgnored="false"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapStyle" />
<spring:url value="/resources/css/font-awesome.css" var="fontAweSomeStyle" />
<spring:url value="/resources/css/main.css" var="mainCss" />

<link href="${bootstrapStyle}" rel="stylesheet" media="screen">
<link href="${fontAweSomeStyle}" rel="stylesheet" media="screen">
<link href="${mainCss}" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="ListServlet"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: <c:out value="${idComputer}"></c:out>
                    </div>
                    <h1>Edit Computer</h1>

                    <form action="EditServlet" method="POST" name = "editComputer">
                        <input type="hidden" value="${idComputer}" id="id" name="id"/> <!-- TODO: Change this value with the computer id -->
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" name="computerName" id="computerName" value="${updateComputer.name}" placeholder="Computer name">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" name="introduced" id="introduced" value="${updateComputer.introduced}" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" name="discontinued" id="discontinued" value="${updateComputer.discontinued}" placeholder="Discontinued date">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" name="company" id="company" value="${updateComputer.company.name}" >
                                  <c:forEach var="company" items="${listCompany}">
                                    <option value="${company.id}"><c:out value = "${company.name}"/></option>
                                  </c:forEach>
                                </select>
                            </div>            
                        </fieldset>
                        <div  class="form-group">
                        <c:out value ="${erreur}"></c:out>
                        </div>
                        <div class="actions pull-right">
                            <input type="submit" value="EditServlet" class="btn btn-primary">
                            or
                            <a id="cancel" href="ListServlet" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <spring:url value="/resources/js/jquery.min.js" var="jqueryMinJs" />
	<spring:url value="/resources/js/bootstrap.min.js" var="bootsrapJs" />
	<spring:url value="/resources/js/dashboard.js" var="dashboardJs" />
	
	<script src="${jqueryMinJs }"></script>
	<script src="${bootsrapJs }"></script>
	<script src="${dashboardJs }"></script>
</body>
</html>
