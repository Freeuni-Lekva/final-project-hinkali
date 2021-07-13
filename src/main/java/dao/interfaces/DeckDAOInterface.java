package dao.interfaces;

import model.Card;
import model.Deck;

import java.util.List;

public interface DeckDAOInterface {

    boolean addDeck(Deck deck);

    Deck getDeck(int deckId);

    boolean removeDeck(int deckId);

    boolean changeDeck(Deck deck);

    boolean addCardToDeck(int deckId, int cardId);

    boolean chooseDeck(int userId, int deckId);

    boolean updateUserDeck(int userId, int deckId);

    List<Integer> getDeckCardIds(int deckId);
}
