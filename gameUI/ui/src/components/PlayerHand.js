import Card from "./Card";
import "../styles/PlayerHand.css";
import EndTurnButton from "./EndTurnButton";

const PlayerHand = ({ hand, setAction, isPlayerTurn }) => {
  return (
    <div className="PlayerHand">
      <ul className="cardsList">
        {hand.map((card) => {
          return (
            <li key={card.name} className="cardWrapper">
              <Card
                card={card}
                setAction={setAction}
                isPlayerTurn={isPlayerTurn}
              />
            </li>
          );
        })}
      </ul>
      <div className="endTurnButton">
        <EndTurnButton setAction={setAction} isPlayerTurn={isPlayerTurn} />
      </div>
    </div>
  );
};

export default PlayerHand;
