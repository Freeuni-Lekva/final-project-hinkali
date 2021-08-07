package model.game;

import model.Card;

import java.util.List;

public interface SubTableInterface {

    List<Card> getPlayerCards();

    void addCardForPlayer(Card c);

    int getPlayerPoints();

    void setPlayerPoints(int points);

    int getPlayerId();

    boolean isValidMove(Card c);
}
