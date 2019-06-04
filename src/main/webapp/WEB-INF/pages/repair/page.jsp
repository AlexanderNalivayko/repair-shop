<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="${sessionScope.bundle}"/>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

<html>
<head>
    <title>
        <fmt:message key="repair.title"/>
    </title>
</head>

<body>

<jsp:include page="../header.jsp"/>

<%--todo change bundles betven tabs and links--%>

<div class="container-fluid my-5">
    <div class="row justify-content-md-center">
        <div class="col-3">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link font-weight-bolder mt-1 text-success active"
                   id="v-pills-profile-tab" data-toggle="pill"
                   href="#v-pills-profile" role="tab" aria-controls="v-pills-profile" aria-selected="false">
                    <fmt:message key="repair.tab.my"/>
                </a>
                <a class="nav-link font-weight-bolder mb-1 text-success"
                   id="v-pills-home-tab" data-toggle="pill"
                   href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true">
                    <fmt:message key="repair.tab.creation"/>
                </a>
            </div>
        </div>
        <div class="col-7">
            <div class="tab-content" id="v-pills-tabContent">
                <div class="tab-pane fade show active" id="v-pills-profile"
                     role="tabpanel" aria-labelledby="v-pills-profile-tab">
                    <jsp:include page="my-requests.jsp"/>
                    <div class="row justify-content-sm-center">
                        <jsp:include page="../pagination.jsp"/>
                    </div>
                </div>
                <div class="tab-pane fade" id="v-pills-home"
                     role="tabpanel"
                     aria-labelledby="v-pills-home-tab">
                    <jsp:include page="new-request.jsp"/>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>

</body>
</html>