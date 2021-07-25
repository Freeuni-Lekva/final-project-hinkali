import '../styles/UserInfo.css'
import userAvatar from './resources/user-svgrepo-com.svg'

const UserInfo = ({user}) => {
    return <div className="UserInfo">
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
}

export default UserInfo