import PlayerBoard from "./components/PlayerBoard";
import PlayerHand from "./components/PlayerHand";
import UserInfo from "./components/UserInfo";
import "./styles/App.css";
import { useEffect, useState } from "react";

const gameStateExample = {
  player: {
    name: "me",
    lives: 2,
    board: [
      {
        type: "close",
        cards: [
          { name: "jerardo", rating: 15 },
          { name: "extra", rating: 2 },
        ],
      },
      { type: "mid", cards: [{ name: "middle", rating: 4 }] },
      { type: "far", cards: [{ name: "katapult", rating: 8 }] },
    ],
    hand: [
      { name: "cardInHandA", rating: 6 },
      { name: "cardInHandB", rating: 5 },
    ],
    isPlayerTurn: true,
    deck: { size: 10 },
    isPlayer: true,
    livesLeft: 2,
  },
  opponent: {
    name: "opponent",
    lives: 2,
    board: [
      { type: "far", cards: [{ name: "bigu", rating: 10 }] },
      {
        type: "mid",
        cards: [
          { name: "smol", rating: 2 },
          { name: "extrasmol", rating: 1 },
        ],
      },
      { type: "close", cards: [] },
    ],
    deck: { size: 11 },
    isPlayer: false,
    livesLeft: 2,
  },
};

function App() {
  const [gameState, setGameState] = useState(gameStateExample);
  const [action, setAction] = useState();

  // TODO on first load fetch userinfo
  useEffect(() => {}, []);

  useEffect(() => {
    // TODO send fetch request to do an action
    console.log(action);
  }, [action]);

  // TODO fetch data every few seconds
  setInterval(() => void 0, 1_200);

  return (
    <div className="App">
      <div className="userInfoWrapper">
        <UserInfo user={gameState.opponent} />
        <UserInfo user={gameState.player} />
      </div>
      <div className="gameWrapper">
        <div className="boardWrapper">
          <PlayerBoard board={gameState.opponent.board} />
          <PlayerBoard board={gameState.player.board} />
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
