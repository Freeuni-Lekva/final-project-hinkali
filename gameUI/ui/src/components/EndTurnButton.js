const EndTurnButton = ({isPlayerTurn}) => {
    return <div className="EndTurnButton">
        <button onClick={() => console.log('klik')} className="endTurnButton" disabled={!isPlayerTurn}>
            End Turn
        </button>
    </div>
}

export default EndTurnButton