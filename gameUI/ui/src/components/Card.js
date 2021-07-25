import '../styles/Card.css'

const Card = ({card}) => {

    return <div className="Card">
        <div className="topInfo">

        </div>
        <img alt="picture goes here"/>
        <div className="bottomInfo">
            <label>{card.name}</label>
        </div>
    </div>
}

export default Card