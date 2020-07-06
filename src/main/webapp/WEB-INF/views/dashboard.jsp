<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page isELIgnored="false"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
            <h1 id="homeTitle">
                 <c:out value = "${totalComputer} Computer"/> 
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="ListServlet" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="AddServlet">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="ListServlet" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                   <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
							<a href ="ListServlet?colonne=computerName&tri=${tri+1}">Computer name</a>
                        </th>
                        <th>
                            <a href ="ListServlet?colonne=computerIntroduced&tri=${tri+1}">Introduced date</a>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            <a href ="ListServlet?colonne=computerDiscontinued&tri=${tri+1}">Discontinued date</a>
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            <a href ="ListServlet?colonne=companyName&tri=${tri+1}">Company name</a>
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                    
                    <c:forEach var="computer" items="${listComputer}">
			     <tr>
			      <td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value="${computer.id}">
                  </td>
					<td>
					<a href ="EditServlet?id=${computer.id}" onclick=""><c:out value="${computer.name}"></c:out></a>
					</td>
			         <td> <c:out value = "${computer.introduced}"/><p> </td>
			         <td> <c:out value = "${computer.discontinued}"/><p> </td>
			         <td> <c:out value = "${computer.company.name}"/><p> </td>
			         
			      </tr>
				</c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
                <c:if test="${page>1}">
                <li>
                    <a href="ListServlet?page=${page-1}&lenPage=${lenPage}" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
              </c:if>
              <c:forEach var="i" begin="1" end="5">
	             
              <li>
              	<a name="${page+i-1}" id="page" href="ListServlet?page=${page+i-1}&lenPage=${lenPage}"><c:out value="${page+i-1}"></c:out></a>
              </li>
     		 </c:forEach>
              <c:if test="${page<maxPage-4}">
              <li>
                <a href="ListServlet?page=${page+5}&lenPage=${lenPage}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            </c:if>
        </ul>
 

        <div class="btn-group btn-group-sm pull-right" role="group" >
            <button type="button" class="btn btn-default"><a href = "ListServlet?lenPage=${lenPage=10}">10</a></button>
            <button type="button" class="btn btn-default"><a href = "ListServlet?lenPage=${lenPage=50}">50</a></button>
            <button type="button" class="btn btn-default"><a href = "ListServlet?lenPage=${lenPage=100}">100</a></button>
        </div>

    </footer>
	<spring:url value="/resources/js/jquery.min.js" var="jqueryMinJs" />
	<spring:url value="/resources/js/bootstrap.min.js" var="bootsrapJs" />
	<spring:url value="/resources/js/dashboard.js" var="dashboardJs" />

	<script src="${jqueryMinJs }"></script>
	<script src="${bootsrapJs }"></script>
	<script src="${dashboardJs }"></script>

</body>
</html>
