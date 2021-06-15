<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<form action="${pageContext.request.contextPath}/app/addFaculty" method="post">
    <div class="form-column">
        <div class="col-md-4 mb-3">
            <label for="validationServer01">Title</label>
            <input type="text" class="form-control is-valid" name="title" id="validationServer01" placeholder="" value="Math" required>
            <div class="valid-feedback">
                Looks good!
            </div>
        </div>
        <div class="col-md-4 mb-3">
            <label for="validationServer02" >Total Places</label>
            <input type="text" class="form-control is-valid" name="totalPlaces" id="validationServer02" placeholder="" value="40" required>
            <div class="valid-feedback">
                Looks good!
            </div>
        </div>
        <div class="col-md-4 mb-3">
            <label for="validationServerUsername" >Budget places</label>
            <div class="input-group">
                <input type="text" class="form-control is-invalid" name="budgetPlaces" id="validationServerUsername" aria-describedby="inputGroupPrepend3" value="22" required>
                <div class="invalid-feedback">
                    Please choose a username.
                </div>
            </div>
        </div>
        <div class="col-md-6 mb-3">
            <label for="validationServer03" >Contract Places</label>
            <input type="text" class="form-control is-invalid" name="contractPlaces" id="validationServer03" placeholder="" value="18" required>
            <div class="invalid-feedback">
                Please provide a positive number.
            </div>
        </div>
        <div class="col-md-6 mb-3">
            <label for="validationServer03" >First Subject</label>
            <input type="text" class="form-control is-invalid" name="firstSubject" id="firstSubject" placeholder="" required>
            <div class="invalid-feedback">
                Please provide a valid title.
            </div>
        </div>
        <div class="col-md-6 mb-3">
            <label for="validationServer03" >Second Subject</label>
            <input type="text" class="form-control is-invalid" name="secondSubject" id="secondSubject" placeholder="" required>
            <div class="invalid-feedback">
                Please provide a valid title.
            </div>
        </div>
        <div class="col-md-6 mb-3">
            <label for="validationServer03" >Third Subject</label>
            <input type="text" class="form-control is-invalid" name="thirdSubject" id="thirdSubject" placeholder="" required>
            <div class="invalid-feedback">
                Please provide a valid title.
            </div>
        </div>
    </div>
    <button class="btn btn-primary" type="submit">
        <fmt:message key="submit"/>
    </button>
</form>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>