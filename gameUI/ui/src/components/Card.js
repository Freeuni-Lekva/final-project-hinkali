import '../styles/Card.css'
import notFoundImg from '../resources/image-not-found.png'

const Card = ({card}) => {

    return <div className="Card">
        <div className="info">
            <label className="ratingLabel">
                {card.rating}
            </label>
        </div>
        <img src={notFoundImg} alt="error" className="image"/>
    </div>
}

export default Card