

//let name = document.getElementById("nameInput").value;
//let joinJson = JSON.stringify({action: 'join', receiver: name });//aq unda gadavce minimum receiveris id da aseve shesadzlebela senderic i guess
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


setInterval(sendJoinRequest, 2000);
setTimeout(sendLeaveRequest, 30_000)
let sec = 30;

function pad(val) {
    return val > 9 ? val : "0" + val;
}

setInterval(function () {
    $("#seconds").html(pad(--sec % 60));
    $("#minutes").html(pad(parseInt(sec / 60, 10)));
}, 1000);

