$(function(){
    $("#edit_form").on('submit', function (event) {
        event.preventDefault();
        let userIdL = $("#userId").val();
        let usernameL = $("#userField").val();
        let nameL = $("#nameField").val();
        let surnameL = $("#surnameField").val();
        let oldPasswordL = $("#oldPasswordField").val();
        let newPasswordL = $("#newPasswordField").val();
        let dateL = $("#dateField").val();
        $.post("edit-profile", {
            userId: userIdL,
            username: usernameL,
            name: nameL,
            surname: surnameL,
            oldPassword: oldPasswordL,
            newPassword: newPasswordL,
            birthday: dateL
        }, function (resp) {
            let msg = $("#info");
            if (resp == 3) {
                msg.html("Profile updated successfully! Redirecting...")
                msg.css('color', 'green');
                let url = "profile?id=" + userIdL;
                let delay = 1000;
                setTimeout(function(){ window.location = url; }, delay);
            } else {
                if (resp == 2) {
                    msg.html("Something went wrong...");
                } else if (resp == 1) {
                    msg.html("That username is already taken!");
                } else if (resp == 0) {
                    msg.html("Incorrect password!")
                }
                msg.css('color', 'red');
            }
        });
    });
});