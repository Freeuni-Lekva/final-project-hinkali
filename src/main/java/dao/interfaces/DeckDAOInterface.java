package dao.interfaces;

import model.Deck;

public interface DeckDAOInterface {

    boolean addDeck(Deck deck);

    Deck getDeck(int deckId);

    boolean removeDeck(int deckId);

    boolean changeDeck(Deck deck);

    boolean addCardToDeck(int deckId, int cardId);

    boolean chooseDeck(int userId, int deckId);
}
