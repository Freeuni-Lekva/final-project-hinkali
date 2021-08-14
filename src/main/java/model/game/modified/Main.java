package model.game.modified;

public class Main {
    public static void main(String[] args) {
        Player testPlayerA = new Player(0, new Deck());
        Player testPlayerB = new Player(1, new Deck());
        GameModified modified = new GameModified(0, testPlayerA, testPlayerB);

        modified.passRound();
        modified.passRound();
        modified.passRound();
        modified.passRound();

        System.out.println(modified.toString());
    }
}
