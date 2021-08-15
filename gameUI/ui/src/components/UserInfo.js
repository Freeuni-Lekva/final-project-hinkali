import '../styles/UserInfo.css'
import userAvatar from '../resources/user-svgrepo-com.svg'
import Deck from "./Deck";

const UserInfo = ({user, actual}) => {
    const deck = user.deck
    const livesLeft = user.livesLeft
    let html = null
    if (user.isPlayer) {
        html = <div className="UserInfo">
            <Deck deck={user.deck}/>
            <div className="livesLeft">
                <label> Lives left: {livesLeft} </label>
            </div>
            <div className="user">
                <img src={userAvatar} className="svg" alt="error"/>
                <div className="user_link_wrapper">
                    <label className="username_label">
                        {actual.username}
                    </label>
                    <br/>
                    <label className="name_label">
                        {actual.name + " " + actual.surname}
                    </label>
                </div>
            </div>
        </div>
    } else {
        html = <div className="UserInfo">
            <div className="user">
                <img src={userAvatar} className="svg" alt="error"/>
                <div className="user_link_wrapper">
                    <label className="username_label">
                        {actual.username}
                    </label>
                    <br/>
                    <label className="name_label">
                        {actual.name + " " + actual.surname}
                    </label>
                </div>
            </div>
            <div className="livesLeft">
                <label> Lives left: {livesLeft} </label>
            </div>
            <Deck deck={user.deck}/>
        </div>
    }

    return html;
}

export default UserInfo