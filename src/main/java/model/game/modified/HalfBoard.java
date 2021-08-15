package model.game.modified;

import java.util.ArrayList;
import java.util.List;

public class HalfBoard {
    private final List<CardRow> rows;

    public HalfBoard(){
        rows = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            rows.add(new CardRow(i));
        }
    }

    public void addCard(Card c){
        rows.get(c.getType()).addCard(c);
    }

    public List<CardRow> getRows(){
        return rows;
    }

    public int getHalfBoardSum(){
        int result = 0;
        for (CardRow row :
                rows) {
            result += row.getRowSum();
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (CardRow row :
                rows) {
            stringBuilder.append(row.toString()).append("\n");
        }

        return stringBuilder.toString();
    }
}
