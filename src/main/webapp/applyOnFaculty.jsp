<!DOCTYPE html>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container">

    <form action="${pageContext.request.contextPath}/app/applyStudentOnFaculty"
          method="post">

        <!-- Add hidden form field to handle update -->
        <input type="hidden" name="facultyid" value="${facultyid}"/>

        <label for="title">Title</label>
        <input type="text" id="title" value="${title}" class="form-control mb-4 col-4" disabled required>

        <label for="totalPlaces">Total places</label>
        <input type="text" id="totalPlaces" value="${totalPlaces}" class="form-control mb-4 col-4" disabled required>

        <label for="title">Budget Places</label>
        <input type="text" value="${budgetPlaces}" class="form-control mb-4 col-4" disabled required>

        <label for="title">Contract Places</label>
        <input type="text" value="${contractPlaces}" class="form-control mb-4 col-4" disabled required>

        <label for="title" >${firstSubject}</label>
        <input type="text" name="firstGrade" class="form-control mb-4 col-4" required>

        <label for="title">${secondSubject}</label>
        <input type="text" name="secondGrade" class="form-control mb-4 col-4" required>

        <label for="title">${thirdSubject}</label>
        <input type="text" name="thirdGrade" class="form-control mb-4 col-4" required>

        <button type="submit" class="btn btn-info col-2">
            <fmt:message key="apply"/>
        </button>
    </form>

    <hr>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>