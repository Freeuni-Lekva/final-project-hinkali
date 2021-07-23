import './UserInfo.css'

const UserInfo = ({user}) => {
    return <div className="UserInfo">
        {user.name}
    </div>
}

export default UserInfo