package dao.interfaces;

import model.Card;
import model.Deck;

import java.util.List;

public interface DeckWrapperInterface extends CardDAOInterface, DeckDAOInterface {

    @Override
    boolean addCard(Card card);

    @Override
    Card getCardById(int cardId);

    @Override
    boolean removeCard(int cardId);

    @Override
    boolean changeCard(Card card);

    @Override
    List<Card> getCards(List<Integer> cardIds);

    @Override
    boolean addDeck(Deck deck);

    @Override
    Deck getDeck(int deckId);

    @Override
    boolean removeDeck(int deckId);

    @Override
    boolean changeDeck(Deck deck);

    @Override
    boolean addCardToDeck(int deckId, int cardId);

    @Override
    boolean removeCardFromDeck(int deckId, int cardId);

    @Override
    boolean chooseDeck(int userId, int deckId);

    @Override
    boolean updateUserDeck(int userId, int deckId);

    @Override
    int getUserDeckId(int userId);

    @Override
    boolean removeUserDeck(int userId);

    @Override
    List<Integer> getDeckCardIds(int deckId);
}
