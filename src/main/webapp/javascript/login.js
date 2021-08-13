/*const menu=
    document.querySelector('#mobile-menu')
const menuLinks = document.querySelector(
    '.navbar__menu')

menu.addEventListener('click', function(){
    menu.classList.toggle('is-active')
    menuLinks.classList.toggle('active')

})


 */


$(function(){
    $("#login--form").on("submit", function (event) {
        event.preventDefault();

        $.post("login",  function (resp) {
            if(resp==="failed"){
                window.location = "/login";
            }
            else if (resp === "success") {
                window.location = "/home";
            }else if(resp==="Pfailed") {
                let msg = $("#info");
                msg.html("Password is wrong, try again!");
                msg.css('color', 'red');
                $("#password").css('border-color', 'red');
            }
        });
    });
});




