import PlayerBoard from "./components/PlayerBoard";
import PlayerHand from "./components/PlayerHand";
import UserInfo from "./components/UserInfo";
import "./styles/App.css";
import {useEffect, useState} from "react";
import {ENDPOINT_GAME_STATE, ENDPOINT_PLAYER_INFO} from "./resources/endpoints";
import axios from "axios";

const gameStateExample = {
    player: {
        name: "me",
        lives: 2,
        board: [
            {
                type: "close",
                cards: [
                    {name: "jerardo", rating: 15},
                    {name: "extra", rating: 2},
                ],
            },
            {type: "mid", cards: [{name: "middle", rating: 4}]},
            {type: "far", cards: [{name: "katapult", rating: 8}]},
        ],
        hand: [
            {name: "cardInHandA", rating: 6},
            {name: "cardInHandB", rating: 5},
        ],
        isPlayerTurn: true,
        deck: {size: 10},
        isPlayer: true,
        livesLeft: 2,
    },
    opponent: {
        name: "opponent",
        lives: 2,
        board: [
            {type: "far", cards: [{name: "bigu", rating: 10}]},
            {
                type: "mid",
                cards: [
                    {name: "smol", rating: 2},
                    {name: "extrasmol", rating: 1},
                ],
            },
            {type: "close", cards: []},
        ],
        deck: {size: 11},
        isPlayer: false,
        livesLeft: 2,
    },
};

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
    const [gameState, setGameState] = useState(gameStateExample);
    const [action, setAction] = useState();

    useEffect(() => {
        axios.get(ENDPOINT_PLAYER_INFO, {withCredentials: true})
            .then(r => r.data)
            .then(r => setUsers(prev => r))
            .catch(e => console.error(e))
    }, []);

    useEffect(() => {
        if (!needsUpdate) return
        axios.get(ENDPOINT_GAME_STATE, {withCredentials: true})
            .then(r => r.data)
            .then(r => {
                console.log(r)
                //
            })
            .catch(e => console.error(e))
            .finally(() => setNeedsUpdate(false))
    }, [needsUpdate]);


    useEffect(() => {
        // TODO send fetch request to do an action
        console.log(action);
    }, [action]);

    setInterval(() => setNeedsUpdate(prev => true), 2_000);

    return (
        <div className="App">
            <div className="userInfoWrapper">
                <UserInfo user={gameState.opponent} actual={users.opponent}/>
                <UserInfo user={gameState.player} actual={users.player}/>
            </div>
            <div className="gameWrapper">
                <div className="boardWrapper">
                    <PlayerBoard board={gameState.opponent.board}/>
                    <PlayerBoard board={gameState.player.board}/>
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
    );
}

export default App;
