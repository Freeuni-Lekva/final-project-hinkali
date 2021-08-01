import '../styles/EndTurnButton.css'


const EndTurnButton = ({isPlayerTurn}) => {

    return <div className="button_container">
        <div className="button_su">
        <span className="su_button_circle">
        </span>
            <a className="button_su_inner">
          <span className="button_text_container">
            Pass Turn
          </span>
            </a>
        </div>
    </div>
}

export default EndTurnButton