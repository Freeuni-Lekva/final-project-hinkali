$(function(){
    $("#form").on("submit", function (event) {
        event.preventDefault();
        let usernameL = $("#userField").val();
        let nameL = $("#nameField").val();
        let surnameL = $("#surnameField").val();
        let passwordL = $("#passwordField").val();
        let dateL = $("#dateField").val();
        $.post("register", {
            username: usernameL,
            name: nameL,
            surname: surnameL,
            password: passwordL,
            birthday: dateL
        }, function (resp) {
            if (resp === "success") {
                window.location = "index.jsp";
            } else {
                let msg = $("#info");
                msg.html("Registration failed, username already in usage!");
                msg.css('color', 'red');
                $("#userField").css('border-color', 'red');
            }
        });
    });
});