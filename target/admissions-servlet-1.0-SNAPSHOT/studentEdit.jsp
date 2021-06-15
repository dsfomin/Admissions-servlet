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
        <fmt:message key="edit_system_student"/>
    </h1>
    <hr>
    <h2>
        <fmt:message key="edit_student" />
    </h2>

    <form action="${pageContext.request.contextPath}/app/editStudent"
          method="post">

        <!-- Add hidden form field to handle update -->
        <input type="hidden" name="id" value="${id}"/>

        <input type="text" name="login" value="${login}" class="form-control mb-4 col-4">

        <input type="text" name="email" value="${email}" class="form-control mb-4 col-4">

        <input type="hidden" name="password" value="${password}" class="form-control mb-4 col-4">

        <input type="text" name="city" value="${city}" class="form-control mb-4 col-4">

        <input type="text" name="district" value="${district}" class="form-control mb-4 col-4">

        <input type="text" name="school" value="${school}" class="form-control mb-4 col-4">

        <button class="btn btn-info col-2">
            <span>
                <fmt:message key="edit_student" />
            </span>
        </button>
    </form>

    <hr>

    <a href = "${pageContext.request.contextPath}/app/studentList">
        <span>
            <fmt:message key="back_to_list" />
        </span>
    </a>
</div>
</body>
</html>