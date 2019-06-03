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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

<html>
<head>
    <title>
        <fmt:message key="site.name"/>
    </title>
</head>

<body class="h-100">

<jsp:include page="header.jsp"></jsp:include>

<div class="jumbotron mb-0 bg-white text-muted text-center">
    <h1 class="display-4"><fmt:message key="site.name"/>!</h1>
    <p class="lead"><fmt:message key="home.welcome"/></p>
    <hr class="my-4">
    <p><fmt:message key="home.welcome.hint"/></p>
    <p class="lead">
        <a class="btn btn-success btn-lg" href="${pageContext.request.contextPath}/site/sign_up_page"
           role="button"><fmt:message key="action.signup"/></a>
        <a class="btn btn-success btn-lg" href="${pageContext.request.contextPath}/site/about_page"
           role="button"><fmt:message key="header.about"/></a>
    </p>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>