package model.game;

import model.Card;

import java.util.List;

public interface PlayerInterface {

    int getID();

    int getPoint();

    void setPoint(int point);

    //void setShuffledDeck(List<Card> cards);

    List<Card> getHeldCards();

    void setPlayerDeck();

    void addCardToPlayer(Card card);

    boolean hasPlayableCards();

    void endTurn();

    void startTurn();

    void startRound();

    void endRound();

    void clearTable();

    boolean isTurn();

    boolean hasEndedRound();

    boolean setCardOnTable(Card c);

}
