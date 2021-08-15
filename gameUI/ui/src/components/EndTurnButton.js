import "../styles/EndTurnButton.css";

const EndTurnButton = ({ setAction, isPlayerTurn }) => {
  if (isPlayerTurn) {
    return (
      <div
        className="button_container"
        onClick={() => setAction({ action: "passRound" })}
      >
        <div className="button_su">
          <span className="su_button_circle"></span>
          <a className="button_su_inner">
            <span className="button_text_container">Pass Round</span>
          </a>
        </div>
      </div>
    );
  } else {
    return (
      <div className="button_container">
        <div className="button_su">
          <span className="su_button_circle"></span>
          <a className="button_su_inner" style={{ backgroundColor: "grey" }}>
            <span className="button_text_container">Pass Round</span>
          </a>
        </div>
      </div>
    );
  }
};

export default EndTurnButton;
