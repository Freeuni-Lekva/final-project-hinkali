let sec = 0;

function pad ( val ) { return val > 9 ? val : "0" + val; }
setInterval( function(){
    $("#seconds").html(pad(++sec%60));
    $("#minutes").html(pad(parseInt(sec/60,10)));
}, 1000);

function redirectHome(){
    window.location.replace("/home")
}

window.onload = function (){
    document.getElementById("returnBtnId").addEventListener("click", redirectHome);
};

// temporary non-socket implementation
function sendJoinRequest(){
    fetch("/test_matchmaking", {
        method: "POST",
        body: "JOIN"
    }).then(r => r.json()).then(r => {
        console.log("here");
        if (r.hasOwnProperty('gameId')){
            console.log("here");
            window.location.replace("/play?gameId=" + r.gameId);
        }
    }).then(r => console.log(r));
}

setInterval(sendJoinRequest, 3000);

