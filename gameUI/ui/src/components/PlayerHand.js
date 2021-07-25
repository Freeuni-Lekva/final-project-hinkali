import Card from "./Card";
import '../styles/PlayerHand.css'

const PlayerHand = ({hand}) => {

    return <div className="PlayerHand">
            <ul className="cards">
                {hand.map(card => {
                    return <li key={card.name} className="cardWrapper">
                        <Card card={card}/>
                    </li>
                })}
            </ul>
        <div className="endTurnButton">

        </div>
    </div>
}

export default PlayerHand