import Row from "./Row";
import '../styles/PlayerBoard.css'

const PlayerBoard = ({board}) => {

    return <div className="PlayerBoard">
        {board.map(row => <Row row={row} key={row.type}/>)}
    </div>
}

export default PlayerBoard