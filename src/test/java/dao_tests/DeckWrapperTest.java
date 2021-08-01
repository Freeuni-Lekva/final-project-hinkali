package dao_tests;

import commons.beans.UserBean;
import dao.implementation.CardDAO;
import dao.implementation.DeckDAO;
import dao.implementation.DeckWrapperDAO;
import dao.implementation.UserDAO;
import dao.interfaces.CardDAOInterface;
import dao.interfaces.DeckDAOInterface;
import dao.interfaces.DeckWrapperInterface;
import dao.interfaces.UserDAOInterface;
import model.Card;
import model.Deck;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckWrapperTest {

    private final Card card1 = new Card("cardTest1", "cardTest1", 1);
    private final Card card2 = new Card("cardTest2", "cardTest2", 2);
    private final UserBean user1 = new UserBean("wrapperTest1", "", "", "wrapperTest1", null);
    private final Deck deck1 = new Deck("deckTest1", "deckTest1", null);
    private final Deck deck2 = new Deck("deckTest2", "deckTest2", null);
    private final DeckWrapperInterface wrapperDao = new DeckWrapperDAO();
    private final UserDAOInterface userDao = new UserDAO();

    @Before
    public void setUp() throws Exception {
        assertTrue(wrapperDao.addCard(card1));
        assertTrue(wrapperDao.addCard(card2));
        assertTrue(wrapperDao.addDeck(deck1));
        assertTrue(wrapperDao.addDeck(deck2));
        assertTrue(userDao.addUser(user1));
    }

    @After
    public void tearDown() throws Exception {
        assertTrue(wrapperDao.removeDeck(deck1.getDeckId()));
        assertTrue(wrapperDao.removeDeck(deck2.getDeckId()));
        assertTrue(wrapperDao.removeCard(card1.getCardId()));
        assertTrue(wrapperDao.removeCard(card2.getCardId()));
        assertTrue(userDao.removeUser(user1.getId()));
    }

    @Test
    public void testRemoveCard() {
        assertTrue(wrapperDao.addCardToDeck(deck1.getDeckId(), card1.getCardId()));
        assertTrue(wrapperDao.addCardToDeck(deck1.getDeckId(), card2.getCardId()));
        assertTrue(wrapperDao.addCardToDeck(deck2.getDeckId(), card1.getCardId()));

        assertTrue(wrapperDao.removeCard(card1.getCardId()));

        List<Integer> expectedCardIds = new ArrayList<>(Arrays.asList(card2.getCardId()));
        assertEquals(expectedCardIds, wrapperDao.getDeckCardIds(deck1.getDeckId()));

        assertEquals(Collections.emptyList(), wrapperDao.getDeckCardIds(deck2.getDeckId()));

        assertTrue(wrapperDao.removeCard(card2.getCardId()));
        assertEquals(Collections.emptyList(), wrapperDao.getDeckCardIds(deck1.getDeckId()));

        assertTrue(wrapperDao.addCard(card1));
        assertTrue(wrapperDao.addCard(card2));
    }

    @Test
    public void testRemoveDeck() {
        assertTrue(wrapperDao.addCardToDeck(deck1.getDeckId(), card1.getCardId()));
        assertTrue(wrapperDao.chooseDeck(user1.getId(), deck1.getDeckId()));

        assertTrue(wrapperDao.removeDeck(deck1.getDeckId()));

        assertNull(wrapperDao.getDeck(deck1.getDeckId()));
        assertEquals(-1, wrapperDao.getUserDeckId(user1.getId()));

        assertTrue(wrapperDao.addDeck(deck1));
    }

    @Test
    public void testUpdateUserDeck() {
        assertTrue(wrapperDao.updateUserDeck(user1.getId(), deck1.getDeckId()));
        assertTrue(wrapperDao.updateUserDeck(user1.getId(), deck2.getDeckId()));
        assertTrue(wrapperDao.updateUserDeck(user1.getId(), deck2.getDeckId()));
        assertFalse(wrapperDao.updateUserDeck(user1.getId(), -1));
    }
}
