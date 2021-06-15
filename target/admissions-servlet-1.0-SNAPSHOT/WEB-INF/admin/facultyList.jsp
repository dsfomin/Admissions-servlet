<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<html lang="${sessionScope.lang}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Faculty List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark navbar-default navbar-fixed-top">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/app/home"><span><fmt:message key="nav"/></span></a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/app/facultyList?currentPage=1&recordsPerPage=5&sortBy=facultyid&order=asc"><span><fmt:message
                        key="faculties"/></span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/app/studentList?currentPage=1&recordsPerPage=5&sortBy=studentid&order=asc"><span><fmt:message
                        key="students"/></span></a>
            </li>
        </ul>
    </div>
    <p class="h6 text-white mb-0 mr-1">Hello <a href="${pageContext.request.contextPath}/app/userProfile?id=${id}">${login}</a>!</p>
    <a class="nav-link" href="?currentPage=1&recordsPerPage=5&sortBy=facultyid&order=asc&sessionLocale=en">
        <p class="mb-0 text-uppercase font-weight-bold ">EN</p>
    </a>
    <a class="nav-link" href="?currentPage=1&recordsPerPage=5&sortBy=facultyid&order=asc&sessionLocale=ua">
        <p class="mb-0 text-uppercase font-weight-bold">UA</p>
    </a>
</nav>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">
            #
            <a href="${pageContext.request.contextPath}/app/facultyList?currentPage=${currentPage}&recordsPerPage=${total}&sortBy=facultyid&order=desc" >↓</a>
            <a href="${pageContext.request.contextPath}/app/facultyList?currentPage=${currentPage}&recordsPerPage=${total}&sortBy=facultyid&order=asc" >↑</a>

        </th>
        <th scope="col">
                Title
            <a href="${pageContext.request.contextPath}/app/facultyList?currentPage=${currentPage}&recordsPerPage=${total}&sortBy=title&order=desc" >↓</a>
            <a href="${pageContext.request.contextPath}/app/facultyList?currentPage=${currentPage}&recordsPerPage=${total}&sortBy=title&order=asc" >↑</a>

        </th>
        <th scope="col">
                Total Places
            <a href="${pageContext.request.contextPath}/app/facultyList?currentPage=${currentPage}&recordsPerPage=${total}&sortBy=totalPlaces&order=desc" >↓</a>
            <a href="${pageContext.request.contextPath}/app/facultyList?currentPage=${currentPage}&recordsPerPage=${total}&sortBy=totalPlaces&order=asc" >↑</a>

        </th>
        <th scope="col">
                Budget Places
            <a href="${pageContext.request.contextPath}/app/facultyList?currentPage=${currentPage}&recordsPerPage=${total}&sortBy=budgetPlaces&order=desc" >↓</a>
            <a href="${pageContext.request.contextPath}/app/facultyList?currentPage=${currentPage}&recordsPerPage=${total}&sortBy=budgetPlaces&order=asc">↑</a>

        </th>
        <th scope="col">
            Contract Places
            <a href="${pageContext.request.contextPath}/app/facultyList?currentPage=${currentPage}&recordsPerPage=${total}&sortBy=contractPlaces&order=desc">↓</a>
            <a href="${pageContext.request.contextPath}/app/facultyList?currentPage=${currentPage}&recordsPerPage=${total}&sortBy=contractPlaces&order=asc">↑</a>

        </th>
        <th scope="col">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="faculty" items="${faculties}">
    <tr>
        <th scope="row"><span>${faculty.facultyId}</span></th>
        <td>${faculty.title}</td>
        <td>${faculty.totalPlaces}</td>
        <td>${faculty.budgetPlaces}</td>
        <td>${faculty.contractPlaces}</td>
        <td>
                <a href="${pageContext.request.contextPath}/app/showEditFacultyPage?facultyid=${faculty.facultyId}"
                   class="btn btn-info"> <fmt:message key="update"/></a>
                <a href="${pageContext.request.contextPath}/app/deleteFaculty?facultyid=${faculty.facultyId}"
                   class="btn btn-danger"><fmt:message key="delete"/></a>
                <a href="${pageContext.request.contextPath}/app/showStudentsOnFaculty?facultyid=${faculty.facultyId}"
                   class="btn btn-outline-success">
                    <fmt:message key="students"/>
                </a>

        </td>
        </c:forEach>
    </tr>

    </tbody>

</table>
<nav aria-label="Navigation for countries">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/app/facultyList?currentPage=${currentPage-1}&recordsPerPage=${total}&sortBy=${sortBy}&order=${order}">
                    <fmt:message key="previousPage"/>
                </a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/app/facultyList?currentPage=${i}&recordsPerPage=${total}&sortBy=${sortBy}&order=${order}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/app/facultyList?currentPage=${currentPage+1}&recordsPerPage=${total}&sortBy=${sortBy}&order=${order}"><fmt:message
                    key="nextPage"/></a>
            </li>
        </c:if>
    </ul>
</nav>
<form action="${pageContext.request.contextPath}/app/addFacultyPage" method="get">
    <button type="submit" class="btn btn-primary">
        <fmt:message key="add_faculty"/>
    </button>
</form>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
