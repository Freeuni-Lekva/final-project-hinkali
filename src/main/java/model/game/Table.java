package model.game;

import model.Card;

public class Table implements TableInterface {
    SubTable player1;
    SubTable player2;

    public Table(int player1Id, int player2Id){
        player1 = new SubTable(player1Id);
        player2 = new SubTable(player2Id);
    }

    @Override
    public SubTable getSubTableForPlayer(int playerId) {
        if(player1.getPlayerId() == playerId)
         return player1;
        return player2;
    }

    @Override
    public int getPlayerPoints(int playerId) {
        return getSubTableForPlayer(playerId).getPlayerPoints();
    }

    @Override
    public void setPlayerPoints(int playerId, int points) {
        getSubTableForPlayer(playerId).setPlayerPoints(points);

    }

    @Override
    public boolean setCardForPlayer(int playerId, Card c) {
        boolean isValidMove = getSubTableForPlayer(playerId).isValidMove(c);
        if(isValidMove){
           //do some special powers
            return true;
        }
        return false;
    }


}
