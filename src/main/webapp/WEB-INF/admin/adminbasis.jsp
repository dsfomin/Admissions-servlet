<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<html lang="${sessionScope.lang}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body style="background: #95c2de;">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark navbar-default navbar-fixed-top">
    <a class="navbar-brand"  href="${pageContext.request.contextPath}/app/home" ><span><fmt:message key="nav"/></span></a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/facultyList?currentPage=1&recordsPerPage=5&sortBy=facultyid&order=asc"><span><fmt:message key="faculties"/></span></a>
            </li>
            <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/app/studentList?currentPage=1&recordsPerPage=5&sortBy=studentid&order=asc"><span><fmt:message key="students"/></span></a>
            </li>
        </ul>
    </div>
    <p class="h6 text-white mb-0 mr-1">Hello <a href="${pageContext.request.contextPath}/app/userProfile?id=${id}">${login}</a>!</p>
    <a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/app/logout"> <fmt:message key="sign_out"/></a>
    <a class="nav-link align-middle text-justify" href="?sessionLocale=en">
        <p class="mb-0 text-uppercase font-weight-bold ">EN</p>
    </a>
    <a class="nav-link align-middle text-justify" href="?sessionLocale=ua">
        <p class="mb-0 text-uppercase font-weight-bold">UA</p>
    </a>
</nav>

</body>
</html>