package model.game;

import model.Card;

import java.util.List;

public interface SubtableInterface {

    List<Card> getPlayerCards();

    boolean addCardForPlayer(Card c);

    int getPlayerPoints();
}
