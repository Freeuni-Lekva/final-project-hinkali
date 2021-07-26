import '../styles/UserInfo.css'
import userAvatar from '../resources/user-svgrepo-com.svg'

const UserInfo = ({user}) => {
    const deck = user.deck
    const livesLeft = user.livesLeft
    let html = null
    if (user.isPlayer){
        html = <div className="UserInfo">
            <div className="deck">
                <label>{deck.size}</label>
            </div>
            <div className="livesLeft">
                <label>{livesLeft}</label>
            </div>
                <div className="user">
                    <img src={userAvatar} className="svg" alt="error"/>
                    <div className="user_link_wrapper">
                        <label className="username_label">
                            {user.name}
                        </label>
                        <br/>
                        <label className="name_label">
                            full name
                        </label>
                    </div>
                </div>
            </div>
    }else{
        html = <div className="UserInfo">
            <div className="user">
                <img src={userAvatar} className="svg" alt="error"/>
                <div className="user_link_wrapper">
                    <label className="username_label">
                        {user.name}
                    </label>
                    <br/>
                    <label className="name_label">
                        full name
                    </label>
                </div>
            </div>
            <div className="deck">
                <label>{deck.size}</label>
            </div>
        </div>
    }

    return html;
}

export default UserInfo