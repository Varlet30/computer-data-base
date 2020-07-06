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
                    <h1>Add Computer</h1>
                    <form action="AddServlet" method="POST" name = "addComputer">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" name="computerName" id="computerName" value="${addComputer.name}" placeholder="Computer name">
                            </div>
                            <div class="form-group">
                                <label for="computerIntroduced">computerIntroduced date</label>
                                <input type="date" class="form-control" name="computerIntroduced" id="computerIntroduced" value="${addComputer.computerIntroduced}" placeholder="computerIntroduced date">
                            </div>
                            <div class="form-group">
                                <label for="computerDiscontinued">computerDiscontinued date</label>
                                <input type="date" class="form-control" name="computerDiscontinued" id="computerDiscontinued" value="${addComputer.computerDiscontinued}" placeholder="computerDiscontinued date">
                            </div>
                            <div class="form-group">
                                <label for="company">Company</label>
                                <select class="form-control" name="company" id="company" value="${addComputer.company.name}" >
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
                            <input type="submit" value="AddServlet" class="btn btn-primary">
                            or
                            <a id="cancel" href="ListServlet" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <spring:url value="js/jquery.min.js" var="jqueryMinJs" />
	<spring:url value="js/bootstrap.min.js" var="bootsrapJs" />
	<spring:url value="js/dashboard.js" var="dashboardJs" />
	
	<script src="${jqueryMinJs }"></script>
	<script src="${bootsrapJs }"></script>
	<script src="${dashboardJs }"></script>
</body>
</html>