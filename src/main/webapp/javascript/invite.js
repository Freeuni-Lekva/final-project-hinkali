// urls
const generateJoinUrl = (username) =>{
    return `/gameInvite/invite?username=${username}`;
}
const generateAcceptUrl = (username) =>{
    return `/gameInvite/accept?username=${username}`;
}
const awaitUrl = '/gameInvite/await'
const cancelUrl = '/gameInvite/cancel'
const fetchInvitesUrl = "/gameInvite/fetchInvites"

// redirection
const redirectToGame = (gameId) => {
    window.location = `/game?id=${gameId}`
}

const redirectToHome = () => {
    window.location = '/home'
}


let waitingInterval
// functions
const sendJoinRequest = () => {
    const username = document.getElementById('inviteInputID').value
    const url = generateJoinUrl(username)
    const labelB = document.getElementById('statusLabelID')
    labelB.innerHTML = 'Waiting...'
    labelB.style.visibility = 'visible'
    document.getElementById('btnId').disabled = true
    fetch(url).then(r => r.json()).then(r => {
        if (r === 'error'){
            const label = document.getElementById('statusLabelID')
            label.innerHTML = 'Error'
            label.style.visibility = 'visible'
            return
        }
        waitingInterval = setInterval(awaitResponse, 1_500)
        setTimeout(() => {
            clearInterval(waitingInterval)
            sendCancelRequest()
            const label = document.getElementById('statusLabelID')
            label.innerHTML = 'User declined'
            label.style.visibility = 'visible'
            document.getElementById('btnId').disabled = false
        }, 30_000)
    }).catch(e => {
        console.error(e)
        document.getElementById('btnId').disabled = false
    })
}

const awaitResponse = () => {
    fetch(awaitUrl).then(r => r.json()).then(r => {
        console.log(r)
        switch (r.state){
            case 'success':
                clearInterval(waitingInterval)
                document.getElementById('btnId').disabled = false
                redirectToGame(r.gameId)
                break
            case 'failed':
                const label = document.getElementById('statusLabelID')
                clearInterval(waitingInterval)
                document.getElementById('btnId').disabled = false
                label.innerHTML = 'User declined'
                label.style.visibility = 'visible'
                break
            default:
                const labelB = document.getElementById('statusLabelID')
                labelB.innerHTML = 'Waiting...'
                labelB.style.visibility = 'visible'
                break
        }
    }).catch(e => console.error(e))
}

const sendCancelRequest = () => {
    fetch(cancelUrl).then(r => r.json()).then(r => console.log(r)).catch(e => console.error(e))
}




