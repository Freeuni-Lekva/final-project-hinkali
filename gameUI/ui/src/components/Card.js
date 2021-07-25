import '../styles/Card.css'
import sample from '../resources/sample.png'
import notFoundImg from '../resources/image-not-found.png'

const Card = ({card}) => {

    return <div className="Card">
        <div className="topInfo">
            <label className="ratingLabel">
                {card.rating}
            </label>
        </div>
        <img src={notFoundImg} alt="error" className="image"/>
    </div>
}

export default Card