<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="${sessionScope.bundle}"/>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<html>
<head>
    <title>
        <fmt:message key="login.title"/>
    </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body class="bg-color">
<div class="logreg-forms">
    <form class="form-signin shadow" method="post" action="${pageContext.request.contextPath}/site/login_page/login">
        <h1 class="h3 mb-3 font-weight-normal">
            <fmt:message key="login.title"/>
        </h1>
        <c:if test="${wrong_input == true}">
            <p class="text-warning">
                <fmt:message key="login.msg.wrong_input"/>
            </p>
        </c:if>
        <c:if test="${need_login == true}">
            <p class="text-warning">
                <fmt:message key="login.msg.need_login"/>
            </p>
        </c:if>
        <input type="text" name="login" class="form-control" placeholder="username" required="" autofocus="">
        <input type="password" name="password" class="form-control" placeholder="password" required="">
        <div class="row">
            <div class="col-sm-6">
                <a href="${pageContext.request.contextPath}/site/home_page" class="btn btn-danger btn-block">
                    <fmt:message key="login.btn.home"/>
                </a>
            </div>
            <div class="col-sm-6">
                <button class="btn btn-success btn-block" type="submit">
                    <fmt:message key="login.btn.signin"/>
                </button>
            </div>
        </div>
        <hr>
        <p>
            <fmt:message key="login.question"/>
        </p>
        <a href="${pageContext.request.contextPath}/site/sign_up_page" class="btn btn-primary btn-block">
            <fmt:message key="login.btn.signup"/>
        </a>
    </form>
</div>
</body>
</html>
