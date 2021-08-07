package model.game;

import model.Card;

import java.util.List;

public interface TableInterface {

    SubTable getSubTableForPlayer(int playerId);

    int getPlayerPoints(int playerId);

    void setPlayerPoints(int playerId, int points);

    boolean setCardForPlayer(int playerId, Card c);

}
