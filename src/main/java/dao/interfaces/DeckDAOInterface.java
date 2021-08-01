package dao.interfaces;

import model.Deck;

import java.util.List;

public interface DeckDAOInterface {

    boolean addDeck(Deck deck);

    Deck getDeck(int deckId);

    boolean removeDeck(int deckId);

    boolean changeDeck(Deck deck);

    boolean addCardToDeck(int deckId, int cardId);

    boolean removeCardFromDeck(int deckId, int cardId);

    boolean chooseDeck(int userId, int deckId);

    boolean updateUserDeck(int userId, int deckId);

    int getUserDeckId(int userId);

    boolean removeUserDeck(int userId);

    List<Integer> getDeckCardIds(int deckId);

    List<Integer> getDecksContainingCard(int cardId);

    List<Integer> getUsersWithDeck(int deckId);
}
