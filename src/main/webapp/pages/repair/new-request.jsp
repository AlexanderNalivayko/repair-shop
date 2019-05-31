<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="${sessionScope.bundle}"/>

<div class="card shadow p-4">
<h2 class="text-muted">
    <fmt:message key="repair.new_request"/>
</h2>
    <form action="${pageContext.request.contextPath}/site/repair_page/create" class="form-signup" method="post">
        <label for="prod-type" class="mb-0 text-muted font-weight-bolder">
            <fmt:message key="repair.item_type"/>
        </label>
        <p class="text-muted my-0"><fmt:message key="repair.item_type.help"/></p>
        <input type="text" name="prod-type" id="prod-type" class="form-control px-2" required>

        <label for="brand" class="mb-0 text-muted font-weight-bolder">
            <fmt:message key="repair.brand"/>
        </label>
        <p class="text-muted my-0"><fmt:message key="repair.brand.help"/></p>
        <input type="text" name="brand" id="brand" class="form-control px-2" required>

        <label for="name" class="mb-0 text-muted font-weight-bolder">
            <fmt:message key="repair.name"/>
        </label>
        <p class="text-muted my-0"><fmt:message key="repair.name.help"/></p>
        <input type="text" name="name" id="name" class="form-control px-2" required>

        <label for="description" class="mb-0 text-muted font-weight-bolder">
            <fmt:message key="repair.description"/>
        </label>
        <p class="text-muted my-0"><fmt:message key="repair.description.help"/></p>
        <textarea name="description" id="description" class="form-control px-2" rows="3" required></textarea>
        <button class="btn btn-success btn-block my-2" type="submit">
            <fmt:message key="repair.btn.create"/>
        </button>
    </form>
</div>
