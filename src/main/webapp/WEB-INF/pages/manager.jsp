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
        <fmt:message key="manager.title"/>
    </title>
</head>

<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid">
    <div class="row justify-content-md-center my-5">
        <div class="col-md-5 mb-5">
            <h2 class="text-muted">
                <fmt:message key="manager.new_requests"/>
            </h2>
            <c:if test="${fn:length(requestScope.records) lt 1}">
                <p class="text-muted">
                    <fmt:message key="repair.no_requests"/>
                </p>
            </c:if>
            <c:forEach var="request" items="${requestScope.records}">
                <div class="card my-2 shadow">
                    <div class="card-body">
                        <p class="text-muted">
                            <fmt:message key="repair.status"/>:
                                ${request.repairRequestStatus}
                        </p>
                        <p class="text-muted">
                            <fmt:message key="repair.time"/>:
                                ${request.creationTime}
                        </p>
                        <p class="text-muted">
                            <fmt:message key="repair.item_type"/>:
                                ${request.item.itemType}
                        </p>
                        <p class="text-muted">
                            <fmt:message key="repair.brand"/>:
                                ${request.item.brand}
                        </p>
                        <p class="text-muted">
                            <fmt:message key="repair.name"/>:
                                ${request.item.name}
                        </p>
                        <p class="text-muted">
                            <fmt:message key="repair.description"/>:
                                ${request.description}
                        </p>
                        <form action="${pageContext.request.contextPath}/site/manager/updateReview" method="post">
                            <input type="hidden" name="repairId" value="${request.id}">
                            <div class="row align-items-center">
                                <div class="col-sm-2">
                                    <label for="price" class="control-label text-muted">
                                        <fmt:message key="repair.price"/>:
                                    </label>
                                </div>
                                <div class="col-sm-10">
                                    <input type="number" name="price" id="price" class="form-control">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-6">
                                    <button class="btn btn-danger btn-block" name="btn" type="submit" value="decline">
                                        <fmt:message key="manager.btn.decline"/>
                                    </button>
                                </div>

                                <div class="col-sm-6">
                                    <button class="btn btn-success btn-block" name="btn" type="submit" value="approve">
                                        <fmt:message key="manager.btn.approve"/>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </c:forEach>
            <div class="row justify-content-sm-center">
                <jsp:include page="pagination.jsp"/>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>