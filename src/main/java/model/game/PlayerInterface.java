package model.game;

import model.Card;

import java.util.List;

public interface PlayerInterface {

    int getPoint();

    void setPoint(int point);

    void setShuffledDeck(List<Card> cards);

    List<Card> getHeldCards();

    void setPlayerCards();

    void addCardToPlayer(Card card);

    boolean hasPlayableCards();

    void endTurn();

    void startTurn();

    void endRound();

    boolean isTurn();

    boolean hasEndedRound();

}
