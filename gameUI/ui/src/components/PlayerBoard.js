import Row from "./Row";
import '../styles/PlayerBoard.css'
import {useEffect, useState} from "react";

const PlayerBoard = ({board, isPlayer, total}) => {
    const [info, setInfo] = useState([]);

    // on mount
    useEffect(() => {
        const rows = isPlayer ? board.rows : board.rows.reverse();
        setInfo(prev => rows)
    }, [board]);


    const rowToComponent = (row) => {
        return <li key={row.type} className="rowWrapper">
            <Row row={row} key={row.type}/>
        </li>
    }

    return <div className="PlayerBoard">
        <div className="points">
            <label className="pointsLabel">
                {total}
            </label>
        </div>
        <ul className="rowList">
            {info.map(row => rowToComponent(row))}
        </ul>
    </div>
}

export default PlayerBoard