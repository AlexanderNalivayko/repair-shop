<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="${sessionScope.bundle}"/>

<div class="card shadow mb-2 p-2">
    <h2 class="text-muted"><fmt:message key="repair.tab.my"/></h2>
</div>

<c:forEach var="request" items="${requestScope.repairRequests}">
    <div class="card my-2 shadow">
        <div class="card-body">
            <c:if test="${request.review.status != null}">
                <p class="text-muted">
                    <fmt:message key="repair.review.status"/>:
                        ${request.review.status}
                </p>
                <p class="text-muted">
                    <fmt:message key="repair.price"/>:
                        ${request.review.cost}
                </p>
            </c:if>
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
                    ${request.item.productType}
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
        </div>
    </div>
</c:forEach>


