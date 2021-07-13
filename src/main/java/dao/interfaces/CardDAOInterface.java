package dao.interfaces;

import model.Card;

public interface CardDAOInterface {

    boolean addCard(Card card);

    Card getCardById(int cardId);

    boolean removeCard(int cardId);

    boolean changeCard(Card card);
}
