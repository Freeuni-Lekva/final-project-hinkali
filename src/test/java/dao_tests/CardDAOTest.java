package dao_tests;

import dao.implementation.CardDAO;
import dao.interfaces.CardDAOInterface;
import model.Card;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardDAOTest {

    private Card card1, card2, card3;
    private CardDAOInterface cardDao;

    @Before
    public void setUp() throws Exception {
        cardDao = new CardDAO();
        card1 = new Card("cardTest1", "cardTest1", 1, 0);
        card2 = new Card("cardTest2", "cardTest2", 2, 0);
        card3 = new Card("cardTest3", "cardTest3", 3, 0);
        assertTrue(cardDao.addCard(card1));
        assertTrue(cardDao.addCard(card2));
        assertTrue(cardDao.addCard(card3));
    }

    @After
    public void tearDown() throws Exception {
        assertTrue(cardDao.removeCard(card1.getCardId()));
        assertTrue(cardDao.removeCard(card2.getCardId()));
        assertTrue(cardDao.removeCard(card3.getCardId()));
    }

    @Test
    public void testAddCard() {
        assertFalse(cardDao.addCard(card1));
        assertFalse(cardDao.addCard(card2));
        assertFalse(cardDao.addCard(card3));

        Card card = new Card("cardTest0", "cardTest0", 0, 0);
        assertTrue(cardDao.addCard(card));
        assertTrue(cardDao.removeCard(card.getCardId()));
    }

    @Test
    public void testGetCardById() {
        assertEquals(card1, cardDao.getCardById(card1.getCardId()));
        assertEquals(card2, cardDao.getCardById(card2.getCardId()));
        assertEquals(card3, cardDao.getCardById(card3.getCardId()));

        assertNull(cardDao.getCardById(-1));
    }

    @Test
    public void testRemoveCard() {
        assertTrue(cardDao.removeCard(card1.getCardId()));
        assertTrue(cardDao.removeCard(card2.getCardId()));
        assertTrue(cardDao.removeCard(card3.getCardId()));

        assertTrue(cardDao.addCard(card1));
        assertTrue(cardDao.addCard(card2));
        assertTrue(cardDao.addCard(card3));
    }

    @Test
    public void testChangeCard() {
        Card card = new Card(card1.getCardId(), "cardTest0", "cardTest0", 0, 0);
        assertTrue(cardDao.changeCard(card));

        card.setName(card2.getName());
        assertFalse(cardDao.changeCard(card));

        assertEquals("cardTest0", cardDao.getCardById(card.getCardId()).getName());
    }

    @Test
    public void testGetCards() {
        List<Integer> idList = new ArrayList<>(Arrays.asList(card1.getCardId(), card2.getCardId()));
        List<Card> cards = cardDao.getCards(idList);
        assertTrue(cards.contains(card1));
        assertTrue(cards.contains(card2));
        assertFalse(cards.contains(card3));
    }
}
