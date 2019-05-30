<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="${sessionScope.bundle}"/>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

<head>
    <meta charset="UTF-8">
    <title>404</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>
                    <fmt:message key="error.oops"/>
                </h1>
                <h2>
                    <fmt:message key="error.msg404"/>
                </h2>
                <div class="error-actions">
                    <a href="${pageContext.request.contextPath}/site/home_page" class="btn btn-success btn-lg">
                        <fmt:message key="error.btn.home"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>