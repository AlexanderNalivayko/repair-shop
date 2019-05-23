<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

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
<form action="/signup/" class="form-signup">
    <div class="social-login">
        <button class="btn facebook-btn social-btn" type="button"><span><i class="fab fa-facebook-f"></i> Sign up with Facebook</span>
        </button>
    </div>
    <div class="social-login">
        <button class="btn google-btn social-btn" type="button"><span><i class="fab fa-google-plus-g"></i> Sign up with Google+</span>
        </button>
    </div>

    <p style="text-align:center">OR</p>

    <input type="text" id="user-name" class="form-control" placeholder="Full name" required="" autofocus="">
    <input type="email" id="user-email" class="form-control" placeholder="Email address" required autofocus="">
    <input type="password" id="user-pass" class="form-control" placeholder="Password" required autofocus="">
    <input type="password" id="user-repeatpass" class="form-control" placeholder="Repeat Password" required
           autofocus="">

    <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-user-plus"></i> Sign Up</button>
    <a href="#" id="cancel_signup"><i class="fas fa-angle-left"></i> Back</a>
</form>
</body>
</html>
