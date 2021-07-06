function redirectHome(){
    window.location.replace("/home")
}

function redirectToProfile(id){
    window.location.replace("/profile?userid=" + id);
}

window.onload = function (){
    document.getElementById("returnBtnId").addEventListener("click", redirectHome);
    let users = document.getElementsByClassName("user");
    Array.from(users).forEach(user => {
        user.addEventListener("click", () => {
            redirectToProfile(user.id);
        });
    });
};

