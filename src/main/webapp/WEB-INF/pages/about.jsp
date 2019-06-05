<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <fmt:message key="about.title"/>
    </title>
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="container-fluid">
    <div class="row justify-content-md-center my-5">
        <div class="col-md-10">
            <h2 class="text-muted">
                <fmt:message key="about.text"/>
            </h2>
        </div>
    </div>
    <div class="row justify-content-md-center bg-success pt-2">
        <div class="col-md-10">
            <h3 class="text-white">
                <fmt:message key="about.feedback"/>
            </h3>
            <form action="${pageContext.request.contextPath}/site/manager/delete_feedback" method="post">
                <c:forEach var="feedbackMsg" items="${requestScope.records}">
                    <div class="card my-2 shadow">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-8">
                                    <p class="text-muted">
                                            ${feedbackMsg.user.username} |
                                            ${feedbackMsg.creationTime}
                                    </p>
                                </div>
                                <div class="col-md-4">
                                    <c:if test="${sessionScope.user.userRole eq 'MANAGER'}">
                                        <button class="btn btn-danger float-right" name="delete-btn"
                                                value="${feedbackMsg.id}" type="submit">
                                            <fmt:message key="about.delete"/>
                                        </button>
                                    </c:if>
                                </div>
                            </div>
                            <hr>
                            <p class="text-muted">${feedbackMsg.text}</p>
                        </div>
                    </div>
                </c:forEach>
            </form>
            <br/>
            <div class="row justify-content-sm-center">
                <jsp:include page="pagination.jsp"/>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row justify-content-md-center mb-5 pt-4">
        <div class="col-md-10 mb-5">
            <form action="${pageContext.request.contextPath}/site/about_page/leave_feedback" method="post"
                  class="form-signup">
                <label for="feedback_msg" class="text-muted">
                    <fmt:message key="about.leave.feedback"/>
                </label>
                <c:if test="${sessionScope.user == null}">
                    <p class="text-danger">
                        <fmt:message key="about.msg.registration"/>
                    </p>
                </c:if>
                <textarea name="feedback_msg" class="form-control" id="feedback_msg" rows="3" required></textarea>
                <button class="btn btn-success float-right"
                        type="submit" ${sessionScope.user == null ? "disabled" : ""}>
                    <fmt:message key="about.send"/>
                </button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>