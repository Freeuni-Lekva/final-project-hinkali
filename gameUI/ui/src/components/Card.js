import '../styles/Card.css'
import notFoundImg from '../resources/image-not-found.png'

const colors = ['#78909c','#494ca2','darkred','#febf0a']
const pickColor = (rating) => {
    switch (true){
        case (rating <= 4):
            return colors[0]
        case (rating <= 8):
            return colors[1]
        case (rating <= 12):
            return colors[2]
        default:
            return colors[3]
    }
}

const Card = ({card}) => {
    let pickedColor = pickColor(card.rating);

    return <div className="Card" style={{backgroundColor: pickedColor}}>
        <div className="info">
            <label className="ratingLabel">
                {card.rating}
            </label>
        </div>
        <img src={notFoundImg} alt="error" className="image"/>
    </div>
}

export default Card