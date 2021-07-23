import Card from "./Card";
import './PlayerHand.css'

const PlayerHand = ({hand}) => {

    return <div className="PlayerHand">
        {hand.map(card => {
            return <Card card={card}/>
        })}
    </div>
}

export default PlayerHand