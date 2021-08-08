import '../styles/Deck.css'
import image from '../resources/deck.jpg'

const Deck = ({deck}) => {
    return <div className="Deck">
        <div className="imageContainer">
            <img className="image" src={image} alt="error"/>
        </div>
        <label className="cardsLeftCounter">
            Cards in deck: {deck.size}
        </label>
    </div>
}

export default Deck