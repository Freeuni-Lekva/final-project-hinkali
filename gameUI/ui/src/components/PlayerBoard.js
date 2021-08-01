import Row from "./Row";
import '../styles/PlayerBoard.css'

const PlayerBoard = ({board}) => {

    return <div className="PlayerBoard">
        <div className="points">
            <label className="pointsLabel">
                75
            </label>
        </div>
        <ul className="rowList">
            {board.map(row => {
                return <li key={row.type} className="rowWrapper">
                    <Row row={row} key={row.type}/>
                </li>
            })}
        </ul>
    </div>
}

export default PlayerBoard