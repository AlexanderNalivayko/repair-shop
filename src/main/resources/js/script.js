$(document).ready(function () {

        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != '') {
                window.location.replace('?lang=' + selectedOption);
            }
        });

    var password = document.getElementById("pass"),
        confirm_password = document.getElementById("confirm_pass"),
        passId = "pass",
        passConfirmId = "confirm_pass",
        successClass = 'border-success',
        dangerClass = 'border-danger';

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
    var invalidClassName = 'invalid',
        inputs = document.querySelectorAll('input, select, textarea');
    inputs.forEach(function (input) {
        input.addEventListener('invalid', function () {
            input.classList.add(invalidClassName)
        });
        input.addEventListener('input', function () {
            if (input.validity.valid) {
                input.classList.remove(invalidClassName)
            }
        })
    });


//    $('#free').hide();
//    $('#taken').hide();
//    $("#login").on("blur", function (e) {
//        $('#free').hide();
//        $('#taken').hide();
//        if ($('#login').val() == null || $('#login').val() === "") {
//        } else {
//            $.ajax({
//                type: "POST",
//                url: "http://localhost:8080/site/validate_username",
//                data: $('#signupform').serialize(),
//                dataType: "html",
//                cache: false,
//                success: function (valid) {
//                    if (valid === "free") {
//                        $('#free').show();
//                    } else if (valid === "taken") {
//                        $("#taken").show();
//                    } else {
//                    }
//                }
//            });
//        }
//    });
});
