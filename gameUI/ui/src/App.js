import './App.css';
import PlayerBoard from "./components/PlayerBoard";
import PlayerHand from "./components/PlayerHand";
import EndTurnButton from "./components/EndTurnButton";
import UserInfo from "./components/UserInfo";

function App() {
    const player = {
        name: 'me',
        board: 'playerBoardGoesHere',
        hand: [{name: 'geraldo'}, {name: 'jerald'}],
        isPlayerTurn: false
    }

    const opponent = {
        name: 'not me',
        board: 'opponentBoardGoesHere'
    }

  return (
    <div className="App">
        <div className="userInfoWrapper">
            <UserInfo user={opponent}/>
            <UserInfo user={player}/>
        </div>
        <div className="boardWrapper">
            <PlayerBoard board={opponent.board}/>
            <PlayerBoard board={player.board}/>
        </div>
        <div className="playerControlWrapper">
            <PlayerHand hand={player.hand}/>
            <EndTurnButton isPlayerTurn={player.isPlayerTurn}/>
        </div>
    </div>
  );
}

export default App;
