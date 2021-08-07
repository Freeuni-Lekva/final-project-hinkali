package dao_tests;

import commons.beans.UserBean;
import dao.implementation.CardDAO;
import dao.implementation.DeckDAO;
import dao.implementation.UserDAO;
import dao.interfaces.CardDAOInterface;
import dao.interfaces.DeckDAOInterface;
import dao.interfaces.UserDAOInterface;
import model.Card;
import model.Deck;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DeckDAOTest {

    private Deck deck1, deck2, deck3;
    private final Card card1 = new Card("cardTest1", "cardTest1", 1);
    private final Card card2 = new Card("cardTest2", "cardTest2", 2);
    private final Card card3 = new Card("cardTest3", "cardTest3", 3);
    private final UserBean user = new UserBean("deckTest123", "deckTest123", "deckTest123", "deckTest123", null);
    private final DeckDAOInterface deckDao = new DeckDAO();
    private final CardDAOInterface cardDao = new CardDAO();
    private final UserDAOInterface userDao = new UserDAO();

    @Before
    public void setUp() throws Exception {
        assertTrue(cardDao.addCard(card1));
        assertTrue(cardDao.addCard(card2));
        assertTrue(cardDao.addCard(card3));

        List<Card> cards1 = new ArrayList<>(Arrays.asList(card1, card2));

        deck1 = new Deck("deckTest1", "deckTest1", cards1);
        deck2 = new Deck("deckTest2", "deckTest2", null);
        deck3 = new Deck("deckTest3", "deckTest3", null);

        assertTrue(deckDao.addDeck(deck1));
        assertTrue(deckDao.addDeck(deck2));
        assertTrue(deckDao.addDeck(deck3));
    }

    @After
    public void tearDown() throws Exception {
//        assertTrue(deckDao.removeUserDeck(user.getId()));
        assertTrue(deckDao.removeDeck(deck1.getDeckId()));
        assertTrue(deckDao.removeDeck(deck2.getDeckId()));
        assertTrue(deckDao.removeDeck(deck3.getDeckId()));

        assertTrue(cardDao.removeCard(card1.getCardId()));
        assertTrue(cardDao.removeCard(card2.getCardId()));
        assertTrue(cardDao.removeCard(card3.getCardId()));
    }

    @Test
    public void testAddDeck() {
        assertFalse(deckDao.addDeck(deck1));
        assertFalse(deckDao.addDeck(deck2));
        assertFalse(deckDao.addDeck(deck3));

        Deck deck = new Deck("deckTest0", "deckTest0", null);
        assertTrue(deckDao.addDeck(deck));
        assertTrue(deckDao.removeDeck(deck.getDeckId()));
    }

    @Test
    public void testGetDeck() {
        assertEquals(deck1, deckDao.getDeck(deck1.getDeckId()));
        assertEquals(deck2, deckDao.getDeck(deck2.getDeckId()));
        assertEquals(deck3, deckDao.getDeck(deck3.getDeckId()));

        assertNull(deckDao.getDeck(-1));
    }

    @Test
    public void testRemoveDeck() {
        assertTrue(deckDao.removeDeck(deck1.getDeckId()));
        assertTrue(deckDao.removeDeck(deck2.getDeckId()));
        assertTrue(deckDao.removeDeck(deck3.getDeckId()));

        assertTrue(deckDao.addDeck(deck1));
        assertTrue(deckDao.addDeck(deck2));
        assertTrue(deckDao.addDeck(deck3));
    }

    @Test
    public void testChangeDeck() {
        Deck deck = new Deck(deck1.getDeckId(), "deckTest0", "deckTest0", null);
        assertTrue(deckDao.changeDeck(deck));

        deck.setName(deck2.getName());
        assertFalse(deckDao.changeDeck(deck));

        assertEquals("deckTest0", deckDao.getDeck(deck.getDeckId()).getName());
        assertNull(deckDao.getDeck(deck.getDeckId()).getCards());
    }

    @Test
    public void testAddCardToDeck() {
        assertTrue(deckDao.addCardToDeck(deck1.getDeckId(), card1.getCardId()));
        assertFalse(deckDao.addCardToDeck(deck1.getDeckId(), card1.getCardId()));
        assertTrue(deckDao.getDeckCardIds(deck1.getDeckId()).contains(card1.getCardId()));
        assertTrue(deckDao.removeCardFromDeck(deck1.getDeckId(), card1.getCardId()));
    }

    @Test
    public void testRemoveCardFromDeck() {
        assertTrue(deckDao.addCardToDeck(deck1.getDeckId(), card1.getCardId()));
        assertTrue(deckDao.removeCardFromDeck(deck1.getDeckId(), card1.getCardId()));
    }

    @Test
    public void testChooseDeck() {
        assertTrue(userDao.addUser(user));
        assertTrue(deckDao.chooseDeck(user.getId(), deck1.getDeckId()));
        assertFalse(deckDao.chooseDeck(user.getId(), deck2.getDeckId()));

        assertTrue(deckDao.removeUserDeck(user.getId()));
        assertTrue(userDao.removeUser(user.getId()));
    }

    @Test
    public void testUpdateDeck() {
        assertTrue(userDao.addUser(user));
        assertTrue(deckDao.chooseDeck(user.getId(), deck1.getDeckId()));

        assertTrue(deckDao.updateUserDeck(user.getId(), deck2.getDeckId()));
        assertEquals(deck2.getDeckId(), deckDao.getUserDeckId(user.getId()));

        assertTrue(deckDao.removeUserDeck(user.getId()));
        assertTrue(userDao.removeUser(user.getId()));
    }

    @Test
    public void testGetUserDeckId() {
        assertTrue(userDao.addUser(user));
        assertTrue(deckDao.chooseDeck(user.getId(), deck1.getDeckId()));

        assertEquals(deck1.getDeckId(), deckDao.getUserDeckId(user.getId()));

        assertTrue(deckDao.removeUserDeck(user.getId()));
        assertTrue(userDao.removeUser(user.getId()));
    }

    @Test
    public void testRemoveUserDeck() {
        assertTrue(userDao.addUser(user));
        assertTrue(deckDao.chooseDeck(user.getId(), deck1.getDeckId()));

        assertTrue(deckDao.removeUserDeck(user.getId()));
        assertTrue(deckDao.chooseDeck(user.getId(), deck2.getDeckId()));
        assertTrue(deckDao.removeUserDeck(user.getId()));

        assertTrue(userDao.removeUser(user.getId()));
    }

    @Test
    public void testDeckCards() {
        assertTrue(deckDao.addCardToDeck(deck1.getDeckId(), card1.getCardId()));
        assertTrue(deckDao.addCardToDeck(deck1.getDeckId(), card2.getCardId()));
        List<Integer> cardIds = new ArrayList<>(Arrays.asList(card1.getCardId(), card2.getCardId()));
        assertEquals(cardIds, deckDao.getDeckCardIds(deck1.getDeckId()));

        Card card = new Card("cardTest420", "cardTest420", 1);
        assertTrue(cardDao.addCard(card));
        assertTrue(deckDao.addCardToDeck(deck1.getDeckId(), card.getCardId()));

        assertNotEquals(cardIds, deckDao.getDeckCardIds(deck1.getDeckId()));

        assertTrue(deckDao.removeCardFromDeck(deck1.getDeckId(), card1.getCardId()));
        assertTrue(deckDao.removeCardFromDeck(deck1.getDeckId(), card2.getCardId()));
        assertTrue(deckDao.removeCardFromDeck(deck1.getDeckId(), card.getCardId()));
        assertTrue(cardDao.removeCard(card.getCardId()));
    }

    @Test
    public void testGetDecksContainingCard() {
        assertTrue(deckDao.addCardToDeck(deck1.getDeckId(), card1.getCardId()));
        assertTrue(deckDao.addCardToDeck(deck1.getDeckId(), card2.getCardId()));
        assertTrue(deckDao.addCardToDeck(deck2.getDeckId(), card1.getCardId()));

        List<Integer> expectedDeckIds = new ArrayList<>(Arrays.asList(deck1.getDeckId(), deck2.getDeckId()));
        assertEquals(expectedDeckIds, deckDao.getDecksContainingCard(card1.getCardId()));

        assertTrue(deckDao.removeCardFromDeck(deck1.getDeckId(), card1.getCardId()));
        assertTrue(deckDao.removeCardFromDeck(deck1.getDeckId(), card2.getCardId()));
        assertTrue(deckDao.removeCardFromDeck(deck2.getDeckId(), card1.getCardId()));
    }

    @Test
    public void testGetUsersWithDeck() {
        assertTrue(userDao.addUser(user));
        assertTrue(deckDao.chooseDeck(user.getId(), deck1.getDeckId()));
        List<Integer> expectedUserIds = new ArrayList<>(Arrays.asList(user.getId()));

        assertEquals(expectedUserIds, deckDao.getUsersWithDeck(deck1.getDeckId()));

        assertTrue(deckDao.removeUserDeck(user.getId()));
        assertTrue(userDao.removeUser(user.getId()));
    }

    @Test
    public void testGetAllDecks() {
        List<Deck> decks = deckDao.getAllDecks();
        assertTrue(decks.size() >= 3);
        assertTrue(decks.contains(deck1));
        assertTrue(decks.contains(deck2));
        assertTrue(decks.contains(deck3));
    }
}
