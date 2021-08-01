import Card from "./Card";
import '../styles/Row.css'

const Row = ({row}) => {
    return <div className="Row">
        <div className="rowInfo">
            <div className="labelContainer">
                <label className="rowPoints">0</label>
            </div>
        </div>
        <ul className="cards">
            {row.cards.map(card => {
                return <li key={card.name} className="cardWrapper">
                    <Card card={card}/>
                </li>
            })}
        </ul>
    </div>
}

export default Row