import PlayerBoard from "./components/PlayerBoard";
import PlayerHand from "./components/PlayerHand";
import UserInfo from "./components/UserInfo";
import './styles/App.css'


function App() {
    const gameState = {
        player: {
            name: 'me',
            lives: 2,
            board: [{type: 'close', cards:[{name: 'jerardo'}, {name: 'extra'}]}, {type: 'mid', cards:[{name: 'midldle'}]}, {type: 'far', cards: [{name: 'katapult'}]}],
            hand: [{name: 'cardInHandA'}, {name: 'cardInHandB'}],
            isPlayerTurn: false
        },
        opponent: {
            name: 'opponent',
            lives: 2,
            board: [{type: 'far', cards:[{name: 'bigu'}]}, {type: 'mid', cards:[{name: 'smol'}, {name: 'extrasmol'}]}, {type: 'close', cards: [{name: 'notpult'}]}]
        }
    }

  return (
    <div className="App">
        <div className="upperWrapper">
            <div className="userInfoWrapper">
                <UserInfo user={gameState.opponent}/>
                <UserInfo user={gameState.player}/>
            </div>
            <div className="boardWrapper">
                <PlayerBoard board={gameState.opponent.board}/>
                <PlayerBoard board={gameState.player.board}/>
            </div>
        </div>
        <div className="playerControlWrapper">
            <PlayerHand hand={gameState.player.hand}/>
        </div>
    </div>
  );
}

export default App;
