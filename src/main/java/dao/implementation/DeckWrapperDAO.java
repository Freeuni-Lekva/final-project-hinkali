package dao.implementation;

import dao.interfaces.CardDAOInterface;
import dao.interfaces.DeckDAOInterface;
import dao.interfaces.DeckWrapperInterface;
import model.Card;
import model.Deck;

import java.util.List;

public class DeckWrapperDAO implements DeckWrapperInterface {

    public static final String DECK_WRAPPER_ATTR = "deckWrapper";

    private final CardDAOInterface cardDao;
    private final DeckDAOInterface deckDao;

    public DeckWrapperDAO() {
        cardDao = new CardDAO();
        deckDao = new DeckDAO();
    }

    @Override
    public boolean addCard(Card card) { return cardDao.addCard(card); }

    @Override
    public Card getCardById(int cardId) { return cardDao.getCardById(cardId); }

    @Override
    public boolean removeCard(int cardId) {
        List<Integer> decks = deckDao.getDecksContainingCard(cardId);
        for (Integer deckId : decks)
            deckDao.removeCardFromDeck(deckId, cardId);
        return cardDao.removeCard(cardId);
    }

    @Override
    public boolean changeCard(Card card) { return cardDao.changeCard(card); }

    @Override
    public List<Card> getCards(List<Integer> cardIds) { return cardDao.getCards(cardIds); }

    @Override
    public boolean addDeck(Deck deck) { return deckDao.addDeck(deck); }

    @Override
    public Deck getDeck(int deckId) { return deckDao.getDeck(deckId); }

    @Override
    public boolean removeDeck(int deckId) {
        List<Integer> deckCards = deckDao.getDeckCardIds(deckId);
        for (Integer id : deckCards)
            deckDao.removeCardFromDeck(deckId, id);
        List<Integer> users = deckDao.getUsersWithDeck(deckId);
        for (Integer userId : users)
            deckDao.removeUserDeck(userId);
        return deckDao.removeDeck(deckId);
    }

    @Override
    public boolean changeDeck(Deck deck) { return deckDao.changeDeck(deck); }

    @Override
    public boolean addCardToDeck(int deckId, int cardId) { return deckDao.addCardToDeck(deckId, cardId); }

    @Override
    public boolean removeCardFromDeck(int deckId, int cardId) { return deckDao.removeCardFromDeck(deckId, cardId); }

    @Override
    @Deprecated
    public boolean chooseDeck(int userId, int deckId) { return deckDao.chooseDeck(userId, deckId); }

    @Override
    public boolean updateUserDeck(int userId, int deckId) {
        if (deckDao.getUserDeckId(userId) == -1)
            return deckDao.chooseDeck(userId, deckId);
        return deckDao.updateUserDeck(userId, deckId);
    }

    @Override
    public int getUserDeckId(int userId) { return deckDao.getUserDeckId(userId); }

    @Override
    public boolean removeUserDeck(int userId) { return deckDao.removeUserDeck(userId); }

    @Override
    public List<Integer> getDeckCardIds(int deckId) { return deckDao.getDeckCardIds(deckId); }

    @Override
    public List<Integer> getDecksContainingCard(int cardId) { return deckDao.getDecksContainingCard(cardId); }

    @Override
    public List<Integer> getUsersWithDeck(int deckId) { return deckDao.getUsersWithDeck(deckId); }

    @Override
    public List<Deck> getAllDecks() { return deckDao.getAllDecks(); }
}
