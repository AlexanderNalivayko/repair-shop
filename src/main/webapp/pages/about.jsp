<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

<html>
<head>
    <title>
        fix-shop
    </title>
</head>

<body>

<jsp:include page="header.jsp"></jsp:include>

<div class="container-fluid my-5">
    <div class="row justify-content-md-center">
        <div class="col-md-10">
            <h2 class="text-muted">
                <fmt:message key="about.text"/>
            </h2>
        </div>
    </div>
    <div class="row justify-content-md-center">
        <div class="col-md-10">
            <hr>
            <h3 class="text-muted">
                <fmt:message key="about.feedback"/>
            </h3>
            <c:forEach var="feedbackMsg" items="${requestScope.feedback}">
                <div class="card my-1">
                    <div class="card-body">
                        <p class="text-muted">
                            <fmt:message key="about.user"/>
                                ${feedbackMsg.user.username}
                        </p>
                        <hr>
                        <p class="text-muted">${feedbackMsg.text}</p>
                    </div>
                </div>
            </c:forEach>
            <hr>
            <form action="${pageContext.request.contextPath}site/about_page/leave_feedback" method="post"
                  class="form-signup">
                <p class="text-muted">
                    <fmt:message key="about.leave.feedback"/>
                </p>
                <c:if test="${sessionScope.user == null}">
                    <p class="text-danger">
                        <fmt:message key="about.msg.registration"/>
                    </p>
                </c:if>
                <textarea name="feedback_msg" class="form-control" id="exampleFormControlTextarea5" rows="3"></textarea>
                <button class="btn btn-success float-right"
                        type="submit" ${sessionScope.user == null ? "disabled" : ""}>
                    <fmt:message key="about.send"/>
                </button>
            </form>
            <br/>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>