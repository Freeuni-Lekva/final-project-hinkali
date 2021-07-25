import '../styles/Card.css'
import sample from '../resources/sample.png'

const Card = ({card}) => {

    return <div className="Card">
        <div className="topInfo">

        </div>
        <img src={sample} alt="picture goes here" className="image"/>
    </div>
}

export default Card