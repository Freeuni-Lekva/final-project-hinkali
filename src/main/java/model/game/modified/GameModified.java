package model.game.modified;

public class GameModified {
    private final int gameId;
    private final Player p1;
    private final Player p2;

    private int turnOfPlayer;
    private GameState state;

    public GameModified(int gameId, Player p1, Player p2) {
        this.gameId = gameId;
        this.p1 = p1;
        this.p2 = p2;
        p1.drawCards();
        p2.drawCards();
        this.turnOfPlayer = p1.getId();
        this.state = GameState.IN_PROGRESS;
    }

    public int getGameId() {
        return gameId;
    }

    public int getTurnOfPlayer() {
        return turnOfPlayer;
    }

    public void playCard(int cardId) {
        Player toPlay = p1.getId() == turnOfPlayer ? p1 : p2;
        Card toBeUsed = toPlay.useCardById(cardId);
        Player next = p1.getId() == turnOfPlayer ? p2 : p1;

        updateGameState(toPlay, next);
    }

    public void passRound() {
        Player toPlay = p1.getId() == turnOfPlayer ? p1 : p2;
        toPlay.passRound();
        Player next = p1.getId() == turnOfPlayer ? p2 : p1;

        updateGameState(toPlay, next);
    }

    public Player getPlayerById(int id) {
        return p1.getId() == id ? p1 : p2;
    }

    public Player getOpponentByPlayerId(int id) {
        return p1.getId() == id ? p2 : p1;
    }

    private boolean isGameOver() {
        return p1.getLivesLeft() == 0 || p2.getLivesLeft() == 0;
    }

    private void updateGameState(Player toPlay, Player next) {
        if (isRoundOver()) {
            int playerAPoints = p1.getBoard().getHalfBoardSum();
            int playerBPoints = p2.getBoard().getHalfBoardSum();
            int delta = playerAPoints - playerBPoints;
            if (delta > 0) {
                // player 1 wins
                p2.loseLife();
            } else if (delta == 0) {
                // draw
                p1.loseLife();
                p2.loseLife();
            } else {
                // player 2 wins
                p1.loseLife();
            }
            turnOfPlayer = toPlay.getId() == p1.getId() ? p2.getId() : p1.getId();

            p1.initNextRound();
            p2.initNextRound();

            if (isGameOver()) {
                turnOfPlayer = -1;
                this.state = GameState.GAME_END;
                return;
            }

            this.state = GameState.ROUND_END;
            return;
        }

        if (!next.hasPlayerPassed()) {
            turnOfPlayer = toPlay.getId() == p1.getId() ? p2.getId() : p1.getId();
        }

        this.state = GameState.IN_PROGRESS;
    }

    private boolean isRoundOver() {
        return p1.hasPlayerPassed() && p2.hasPlayerPassed();
    }

    @Override
    public String toString() {
        return "GameModified{" +
                "gameId=" + gameId +
                ", p1=" + p1 +
                ", p2=" + p2 +
                ", turnOfPlayer=" + turnOfPlayer +
                ", state=" + state +
                '}';
    }
}
