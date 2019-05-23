<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
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

<%--<jsp:include page="header.jsp"></jsp:include>--%>

<body>
<div id="logreg-forms">
    <form class="form-signin shadow">
        <h1 class="h3 mb-3 font-weight-normal">
            <fmt:message key="login.msg"/>
        </h1>

        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
        <div class="container">
            <div class="row">
                <div class="col-sm pl-0 pr-1">
                    <a href="/site" class="btn btn-danger btn-block" data-dismiss="modal">
                        <fmt:message key="login.btn.home"/>
                    </a>
                </div>
                <div class="col-sm pr-0 pl-1">
                    <button class="btn btn-success btn-block" type="submit">
                        <fmt:message key="login.btn.signin"/>
                    </button>
                </div>
            </div>
        </div>
        <hr>
        <p>
            <fmt:message key="login.question"/>
        </p>
        <button class="btn btn-primary btn-block" type="button" id="btn-signup">
            <fmt:message key="login.btn.signup"/>
        </button>
    </form>
</div>

<%--<jsp:include page="footer.jsp"></jsp:include>--%>

</body>
</html>
