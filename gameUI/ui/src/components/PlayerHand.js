import Card from "./Card";
import '../styles/PlayerHand.css'
import EndTurnButton from "./EndTurnButton";

const PlayerHand = ({hand}) => {

    return <div className="PlayerHand">
            <ul className="cardsList">
                {hand.map(card => {
                    return <li key={card.name} className="cardWrapper">
                        <Card card={card}/>
                    </li>
                })}
            </ul>
        <div className="endTurnButton">
            <EndTurnButton isPlayerTurn={false}/>
        </div>
    </div>
}

export default PlayerHand