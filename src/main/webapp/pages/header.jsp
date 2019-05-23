<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<header>
    <nav class="navbar navbar-expand-md navbar-dark bg-color rounded-20">
        <a class="navbar-brand font-weight-bolder" href="app/home.jsp">FIX-SHOP</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item"><a class="nav-link font-weight-bolder" href="app/home.jsp"><fmt:message
                        key="header.home"/></a>
                </li>
                <li class="nav-item"><a class="nav-link font-weight-bolder" href="app/home.jsp"><fmt:message
                        key="header.about"/></a>
                </li>
                <li class="nav-item"><a class="nav-link font-weight-bolder" href="app/home.jsp"><fmt:message
                        key="header.repairRequest"/></a>
                </li>
                <li class="nav-item dropdown font-weight-bolder">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="header.user"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" data-toggle="modal" href="#exampleModal"><fmt:message key="action.sign.in"/></a>
                        <a class="dropdown-item" href="#"><fmt:message key="action.sign.up"/></a>
                    </div>
                </li>
            </ul>
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item">
                    <form action="/language" method="get">
                        <select class="form-control form-control-sm" name="locale" onchange="this.form.submit();">
                            <option value="en" ${locale == "en" ? "selected" : ""}><fmt:message
                                    key="language.en"/></option>
                            <option value="ru" ${locale == "ru" ? "selected" : ""}><fmt:message
                                    key="language.ru"/></option>
                        </select>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
</header>

<jsp:include page="login-popup.jsp"></jsp:include>