<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang"/>

<meta charset="utf-16">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<header>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <a class="navbar-brand" href="app/home.jsp">FIX-SHOP</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item"><a class="nav-link" href="app/home.jsp"><fmt:message key="header.home"/></a>
                </li>
                <li class="nav-item"><a class="nav-link" href="app/home.jsp"><fmt:message key="header.about"/></a>
                </li>
                <li class="nav-item"><a class="nav-link" href="app/home.jsp"><fmt:message key="header.repairRequest"/></a>
                </li>
                <li class="nav-item"><a class="nav-link" href="app/home.jsp"><fmt:message key="header.user"/></a>
                </li>
            </ul>
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="header.language"/>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right bg-dark" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item text-muted muted" href="#">en</a>
                        <a class="dropdown-item text-muted muted" href="#">ua</a>
                        <a class="dropdown-item text-muted muted" href="#">ru</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</header>