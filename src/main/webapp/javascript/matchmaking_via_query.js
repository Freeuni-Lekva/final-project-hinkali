let joinJson = JSON.stringify({action: 'join'});
let leaveJson = JSON.stringify({action: 'leave'});

function sendJoinRequest() {
    fetch("/matchmaking", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: joinJson
    }).then(r => r.json()).then(r => {
        console.log(r);
        if (r.body === "created") {
            window.location = ("/play?gameId=" + r.gameId);
        }
    }).catch(e => console.log(e));
}

function sendLeaveRequest(redirect) {
    fetch("/matchmaking", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: leaveJson
    }).then(r => {
        console.log(r)
        if (!!redirect) {
            redirect()
        }
    }).catch(e => {
            console.log(e)
    });
}

function redirectHome() {
    sendLeaveRequest(() => window.location = "/home")
}

window.onload = function () {
    document.getElementById("returnBtnId").addEventListener("click", redirectHome);
};

setInterval(sendJoinRequest, 2000);

let sec = 0;

function pad(val) {
    return val > 9 ? val : "0" + val;
}

setInterval(function () {
    $("#seconds").html(pad(++sec % 60));
    $("#minutes").html(pad(parseInt(sec / 60, 10)));
}, 1000);

