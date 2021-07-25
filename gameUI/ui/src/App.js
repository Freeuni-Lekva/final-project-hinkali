import PlayerBoard from "./components/PlayerBoard";
import PlayerHand from "./components/PlayerHand";
import UserInfo from "./components/UserInfo";
import './styles/App.css'

const gameState = {
    player: {
        name: 'me',
        lives: 2,
        board: [{type: 'close', cards:[{name: 'jerardo', rating: 15}, {name: 'extra', rating: 2}]}, {type: 'mid', cards:[{name: 'middle', rating: 4}]},
            {type: 'far', cards: [{name: 'katapult', rating: 8}]}],
        hand: [{name: 'cardInHandA', rating: 6}, {name: 'cardInHandB', rating: 5}],
        isPlayerTurn: false
    },
    opponent: {
        name: 'opponent',
        lives: 2,
        board: [{type: 'far', cards:[{name: 'bigu', rating: 10}]}, {type: 'mid', cards:[{name: 'smol', rating: 2}, {name: 'extrasmol', rating: 1}]},
            {type: 'close', cards: [{name: 'notpult', rating: 7}]}]
    }
}


function App() {

    return (
        <div className="App">
        <div className="userInfoWrapper">
            <UserInfo user={gameState.opponent}/>
            <UserInfo user={gameState.player}/>
        </div>
        <div className="gameWrapper">
            <div className="boardWrapper">
                <PlayerBoard board={gameState.opponent.board}/>
                <PlayerBoard board={gameState.player.board}/>
            </div>
            <div className="playerControlWrapper">
                <PlayerHand hand={gameState.player.hand}/>
            </div>
        </div>
    </div>
  );
}

export default App;
