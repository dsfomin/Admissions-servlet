<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>


<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@600;900&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/4b9ba14b0f.js" crossorigin="anonymous"></script>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/error.css"/>--%>
    <title>Error page</title>
    <style type="text/css">
    body {
    background-color: #95c2de;
    }

    .mainbox {
    background-color: #95c2de;
    margin: auto;
    height: 600px;
    width: 600px;
    position: relative;
    }

    .err {
    color: #ffffff;
    font-family: 'Nunito Sans', sans-serif;
    font-size: 11rem;
    position:absolute;
    left: 20%;
    top: 8%;
    }

    .far {
    position: absolute;
    font-size: 8.5rem;
    left: 42%;
    top: 15%;
    color: #ffffff;
    }

    .err2 {
    color: #ffffff;
    font-family: 'Nunito Sans', sans-serif;
    font-size: 11rem;
    position:absolute;
    left: 68%;
    top: 8%;
    }

    .msg {
    text-align: center;
    font-family: 'Nunito Sans', sans-serif;
    font-size: 1.6rem;
    position:absolute;
    left: 16%;
    top: 45%;
    width: 75%;
    }

    a {
    text-decoration: none;
    color: white;
    }

    a:hover {
    text-decoration: underline;
    }
    </style>
</head>
<body>
<div class="mainbox">
    <div class="err">4</div>
    <i class="far fa-question-circle fa-spin"></i>
    <div class="err2">4</div>
    <div class="msg">
        <h2>
            Error Page<br/>
            <i style="color:red;">Error ${exception}</i>
        </h2>
        <a href="${pageContext.request.contextPath}/app/home">Home</a>
    </div>
</div>
</body>
</html>