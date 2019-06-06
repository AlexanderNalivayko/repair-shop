<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="${sessionScope.bundle}"/>

<c:set var="url" value="${requestScope['javax.servlet.forward.request_uri']}"/>

<nav>
    <ul class="pagination">
        <c:if test="${numberOfPages > 1}">

            <!--For displaying previous link except for the 1st page -->
            <c:if test="${currentPage != 1}">
                <li class="page-item">
                    <a class="page-link text-muted" href="${url}?page=${currentPage - 1}">
                        <fmt:message key="action.previous"/>
                    </a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item disabled">
                            <a class="page-link text-success" href="${url}?page=${i}">${i}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link text-muted" href="${url}?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
                <%--<li class="page-item"><a class="page-link" href="${url}?page=${i}">${i}</a></li>--%>

            </c:forEach>

            <c:if test="${currentPage lt numberOfPages}">
                <li class="page-item">
                    <a class="page-link text-muted" href="${url}?page=${currentPage + 1}">
                        <fmt:message key="action.next"/>
                    </a>
                </li>
            </c:if>
        </c:if>
    </ul>
</nav>