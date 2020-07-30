<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page isELIgnored="false"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title><spring:message code="cdb.title"/></title>
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
        <div id="justify">
            <a class="navbar-brand" href="dashboard"> <spring:message code="cdb.title"/> </a>
        	<div id="center" >
				<a href="${pageContext.request.contextPath}/addUser"
					style="color: white"><em class="fa fa-user-plus"
					style="font-size: 24px; color: #de4e4e"></em> Add user</a> 
				<a href="${pageContext.request.contextPath}/logout"
					style="color: white"><em class="fa fa-sign-out"
					style="font-size: 24px; color: #de4e4e" aria-hidden="true"></em>Logout</a>
			</div>	
        	<div class="dropdown ">
	            <button class="btn btn-danger dropdown-toggle" type="button" id="dropdownMenuButton"
	               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><spring:message code="app.lang.title"/></button>
	            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
	            	  <a class="dropdown-item" href="?lang=fr"><spring:message code="app.lang.french"/></a>
	               	  <a class="dropdown-item" href="?lang=en"><spring:message code="app.lang.english"/></a> 
	          </div>
        	</div>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                 <c:out value = "${totalComputer}"/> <spring:message code="ComputerFound"/>
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer"><spring:message code="Add"/></a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="Edit"/></a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="dashboard" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered" aria-describedby="edit">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th id="edit" class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                   <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <em class="fa fa-trash-o fa-lg"></em>
                                    </a>
                            </span>
                        </th>
                        <th id="Computer">
							<a href ="?column=name&tri=${tri+1}&lengthPage=${lengthPage}"><spring:message code="Computer"/></a>
                        </th>
                        <th id="Introduced">
                            <a href ="?column=introduced&tri=${tri+1}&lengthPage=${lengthPage}"><spring:message code="Introduced"/></a>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th id="Discontinued">
                            <a href ="?column=discontinued&tri=${tri+1}&lengthPage=${lengthPage}"><spring:message code="Discontinued"/></a>
                        </th>
                        <!-- Table header for Company -->
                        <th id="Company">
                            <a href ="?column=company.name&tri=${tri+1}&lengthPage=${lengthPage}"><spring:message code="Company"/></a>
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                    
                    <c:forEach var="computerDTO" items="${listComput}">
			     <tr>
			      <td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value="${computerDTO.id}">
                  </td>
					<td>
					<a href ="editComputer?id=${computerDTO.id}" onclick=""><c:out value="${computerDTO.name}"></c:out></a>
					</td>
			         <td> <c:out value = "${computerDTO.introduced}"/><p> </td>
			         <td> <c:out value = "${computerDTO.discontinued}"/><p> </td>
			         <td> <c:out value = "${computerDTO.companyDTO.name}"/><p> </td>
			         
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
                    <a href="?page=${page-1}&lengthPage=${lengthPage}&column=${column}&tri=${tri}" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
              </c:if>
              <c:forEach var="i" begin="1" end="5">
	             
              <li><a name="${page+i-1}" href="?page=${page+i-1}&lengthPage=${lengthPage}&column=${column}&tri=${tri}"><c:out value="${page+i-1}"></c:out></a></li>
     		 </c:forEach>
              <c:if test="${page<maxPage-4}">
              <li>
                <a href="?page=${page+5}&lengthPage=${lengthPage}&column=${column}&tri=${tri}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            </c:if>
        </ul>
 

        <div class="btn-group btn-group-sm pull-right" role="group" >
            <button name="LenPage10" type="button" class="btn btn-default"><a href = "?lengthPage=${lengthPage=10}&column=${column}&tri=${tri}">10</a></button>
            <button name="LenPage50" type="button" class="btn btn-default"><a href = "?lengthPage=${lengthPage=50}&column=${column}&tri=${tri}">50</a></button>
            <button name="LenPage100" type="button" class="btn btn-default"><a href = "?lengthPage=${lengthPage=100}&column=${column}&tri=${tri}">100</a></button>
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