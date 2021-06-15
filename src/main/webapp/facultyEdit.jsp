<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>
<div class="container">
    <h1>
        <fmt:message key="edit_system_faculty"/>
    </h1>
    <hr>
    <h2>
        <fmt:message key="edit_faculty"/>
    </h2>

    <form action="${pageContext.request.contextPath}/app/editFaculty"
          method="post">

        <!-- Add hidden form field to handle update -->
        <input type="hidden" name="id" value="${id}"/>

        <input type="text" name="title" value="${title}" class="form-control mb-4 col-4">

        <input type="text" name="totalPlaces" value="${totalPlaces}" class="form-control mb-4 col-4">

        <input type="text" name="budgetPlaces" value="${budgetPlaces}" class="form-control mb-4 col-4">

        <input type="text" name="contractPlaces" value="${contractPlaces}" class="form-control mb-4 col-4">

        <input type="text" name="firstSubject" value="${firstSubject}" class="form-control mb-4 col-4">

        <input type="text" name="secondSubject" value="${secondSubject}" class="form-control mb-4 col-4">

        <input type="text" name="thirdSubject" value="${thirdSubject}" class="form-control mb-4 col-4">

        <button class="btn btn-info col-2"> Update Faculty</button>
    </form>

    <hr>

    <a href = "${pageContext.request.contextPath}/app/facultyList">
        <span>
                    <fmt:message key="back_to_list" />
        </span>
    </a>
</div>
</body>
</html>