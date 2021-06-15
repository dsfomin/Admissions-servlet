<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<html lang="${sessionScope.lang}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>

<h1>
    <fmt:message key="enter_system"/>
</h1><br/>
<form method="get" action="${pageContext.request.contextPath}/app/login">

    <input type="text" name="name"><br/>
    <input type="password" name="pass"><br/><br/>
    <input class="button" type="submit" value="Войти">

</form>
<br/>
<span style="color: red; font-size: 20px;">${error}</span>
<a href="${pageContext.request.contextPath}/">
    <fmt:message key="home"/>
</a>
</body>
</html>