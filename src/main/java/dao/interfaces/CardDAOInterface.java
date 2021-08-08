package dao.interfaces;

import model.Card;

import java.util.List;

public interface CardDAOInterface {

    boolean addCard(Card card);

    Card getCardById(int cardId);

    boolean removeCard(int cardId);

    boolean changeCard(Card card);

    List<Card> getCards(List<Integer> cardIds);
}
