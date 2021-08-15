import PlayerBoard from "./components/PlayerBoard";
import PlayerHand from "./components/PlayerHand";
import UserInfo from "./components/UserInfo";
import "./styles/App.css";
import {useEffect, useState} from "react";
import {
    ENDPOINT_GAME_OVER,
    ENDPOINT_GAME_STATE,
    ENDPOINT_PASS_ROUND,
    ENDPOINT_PLAY_CARD,
    ENDPOINT_PLAYER_INFO
} from "./resources/endpoints";
import axios from "axios";

const usersTemplate = {
    player: {
        username: '',
        name: '',
        surname: ''
    },
    opponent: {
        username: '',
        name: '',
        surname: ''
    }
}

function App() {
    const [needsUpdate, setNeedsUpdate] = useState(false);
    const [users, setUsers] = useState(usersTemplate);
    const [gameState, setGameState] = useState();
    const [action, setAction] = useState();

    useEffect(() => {
        setTimeout(() => {
            axios.get(ENDPOINT_PLAYER_INFO, {withCredentials: true})
                .then(r => r.data)
                .then(r => setUsers(prev => r))
                .catch(e => console.error(e))
        }, 1_000)

        axios.get(ENDPOINT_GAME_STATE, {withCredentials: true})
            .then(r => r.data)
            .then(r => {
                setGameState(prev => r)
            })
            .catch(e => console.error(e))
            .finally(() => setNeedsUpdate(false))
    }, []);

    useEffect(() => {
        if (!needsUpdate) return
        axios.get(ENDPOINT_GAME_STATE, {withCredentials: true})
            .then(r => r.data)
            .then(r => {
                setGameState(prev => r)
            })
            .catch(e => console.error(e))
            .finally(() => setNeedsUpdate(false))
        console.log(gameState);
        console.log(users);
        if(gameState.player.livesLeft ===0 || gameState.opponent.livesLeft ===0){
            //awaitResponse(2000)
            if(gameState.player.livesLeft > gameState.opponent.livesLeft){
                window.location.href = ENDPOINT_GAME_OVER+ `?winner=${users.player.id}&loser=${users.opponent.id}&draw=-1`
            } else if(gameState.player.livesLeft === users.opponent.livesLeft){
                window.location.href = ENDPOINT_GAME_OVER+ `?winner=-1&loser=-1&draw=${users.player.id}`
            } else{
                window.location.href = ENDPOINT_GAME_OVER+ `?winner=${users.opponent.id}&loser=${users.player.id}&draw=-1`
            }
        }

    }, [needsUpdate]);


    useEffect(() => {
        if (!action) return
        console.log(action.action);
        switch (action.action) {
            case 'passRound':
                axios.get(ENDPOINT_PASS_ROUND, {withCredentials: true})
                    .then(r => console.log(r))
                    .catch(e => console.error(e))
                    .finally(() => setNeedsUpdate(prev => true))
                break
            case 'playCard':
                axios.get(ENDPOINT_PLAY_CARD(action.card.id), {withCredentials: true})
                    .then(r => console.log(r))
                    .catch(e => console.error(e))
                    .finally(() => setNeedsUpdate(prev => true))
        }
    }, [action]);

    setInterval(() => {
        if (users.player.username === '') {
            axios.get(ENDPOINT_PLAYER_INFO, {withCredentials: true})
                .then(r => r.data)
                .then(r => setUsers(prev => r))
                .catch(e => console.error(e))
        }
        setNeedsUpdate(prev => true)
    }, 5_000);

    return !!gameState ? (
        <div className="App">
            <div className="userInfoWrapper">
                <UserInfo user={gameState.opponent} actual={users.opponent}/>
                <UserInfo user={gameState.player} actual={users.player}/>
            </div>
            <div className="gameWrapper">
                <div className="boardWrapper">
                    <PlayerBoard board={gameState.opponent.board} isPlayer={false} total={gameState.opponent.total}/>
                    <PlayerBoard board={gameState.player.board} isPlayer={true} total={gameState.player.total}/>
                </div>
                <div className="playerControlWrapper">
                    <PlayerHand
                        hand={gameState.player.hand}
                        setAction={setAction}
                        isPlayerTurn={gameState.player.isPlayerTurn}
                    />
                </div>
            </div>
        </div>
    ) : <div/>;
}

export default App;
