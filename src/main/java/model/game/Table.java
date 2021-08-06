package model.game;

public class Table implements TableInterface {
    Subtable player1;
    Subtable player2;

    public Table(){
        player1 = new Subtable();
        player2 = new Subtable();
    }

    @Override
    public Subtable getSubtableForPlayer1() {
        return player1;
    }

    @Override
    public Subtable getSubtableForPlayer2() {
        return player2;
    }
}
