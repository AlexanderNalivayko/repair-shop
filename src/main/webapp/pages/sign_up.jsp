<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="${sessionScope.bundle}"/>


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>
        <fmt:message key="sign_up.title"/>
    </title>
    <script
            src="${pageContext.request.contextPath}/js/jquery-3.4.1.js">

    </script>
</head>

<body class="bg-color">
<div class="container-fluid">
    <div class="row justify-content-md-center my-5">
        <div class="col-md-6 card shadow">
            <div class="card-body">
                <h2 class="text-muted"><fmt:message key="sign_up.title"/></h2>
                <form action="${pageContext.request.contextPath}/site/sign_up_page/sign_up" class="form-signup"
                      id="signupform">
                    <label for="login" class="mb-0 text-muted">
                        <fmt:message key="sign_up.username"/>
                    </label>
                    <div id="msg"></div>
                    <input type="text" name="login" id="login" class="form-control px-2" required autofocus="">

                    <label for="pass" class="mb-0 text-muted">
                        <fmt:message key="sign_up.pass"/>
                    </label>
                    <input type="password" name="pass" id="pass" class="form-control px-2" required autofocus="">

                    <label for="confirm_pass" class="mb-0 text-muted">
                        <fmt:message key="sign_up.pass_repeat"/>
                    </label>
                    <input type="password" id="confirm_pass" class="form-control px-2" required autofocus="">

                    <label for="first_name" class="mb-0 text-muted">
                        <fmt:message key="sign_up.first_name"/>
                    </label>
                    <input type="text" name="first_name" id="first_name" class="form-control px-2" autofocus="">

                    <label for="last_name" class="mb-0 text-muted">
                        <fmt:message key="sign_up.last_name"/>
                    </label>
                    <input type="text" name="last_name" id="last_name" class="form-control px-2" autofocus="">

                    <label for="email" class="mb-0 text-muted">
                        <fmt:message key="sign_up.email"/>
                    </label>
                    <input type="email" name="email" id="email" class="form-control px-2" autofocus="">

                    <label for="phone" class="mb-0 text-muted">
                        <fmt:message key="sign_up.phone"/>
                    </label>
                    <input type="tel" maxlength="20" name="phone" id="phone" class="form-control px-2" autofocus="">

                    <div class="container my-4">
                        <div class="row">
                            <div class="col-sm pl-0 pr-1">
                                <a href="${pageContext.request.contextPath}/site/home_page"
                                   class="btn btn-danger btn-block">
                                    <fmt:message key="sign_up.btn.home"/>
                                </a>
                            </div>
                            <div class="col-sm pr-0 pl-1">
                                <button class="btn btn-success btn-block" type="submit">
                                    <fmt:message key="sign_up.btn.create"/>
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    var password = document.getElementById("pass"),
        confirm_password = document.getElementById("confirm_pass");

    var passId = "pass";
    var passConfirmId = "confirm_pass";
    var successClass = 'border-success';
    var dangerClass = 'border-danger';

    function validatePassword() {
        if (password.value !== confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
            document.getElementById(passConfirmId).classList.remove(successClass);
            document.getElementById(passId).classList.remove(successClass);
            document.getElementById(passConfirmId).classList.add(dangerClass);
            document.getElementById(passId).classList.add(dangerClass);
        } else {
            confirm_password.setCustomValidity('');
            document.getElementById(passConfirmId).classList.remove(dangerClass);
            document.getElementById(passId).classList.remove(dangerClass);
            document.getElementById(passConfirmId).classList.add(successClass);
            document.getElementById(passId).classList.add(successClass);
        }
    }

    confirm_password.onblur = validatePassword;

    var invalidClassName = 'invalid';
    var inputs = document.querySelectorAll('input, select, textarea');
    inputs.forEach(function (input) {
        input.addEventListener('invalid', function () {
            input.classList.add(invalidClassName)
        })
        input.addEventListener('input', function () {
            if (input.validity.valid) {
                input.classList.remove(invalidClassName)
            }
        })
    })
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $("#login").on("blur", function (e) {
            $('#msg').hide();
            if ($('#login').val() == null || $('#login').val() === "") {
            } else {
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/site/validate_username",
                    data: $('#signupform').serialize(),
                    dataType: "html",
                    cache: false,
                    success: function (msg) {
                        $('#msg').show();
                        $("#msg").html(msg);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        $('#msg').show();
                        $("#msg").html(textStatus + " " + errorThrown);
                    }
                });
            }
        });
    });
</script>

</body>
</html>
