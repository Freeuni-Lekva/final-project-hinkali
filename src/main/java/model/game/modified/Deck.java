package model.game.modified;

import dao.implementation.CardDAO;
import dao.implementation.DeckDAO;
import dao.interfaces.CardDAOInterface;
import dao.interfaces.DeckDAOInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Deck {
    private final List<Card> cards;

    public Deck(model.Deck oldDeckImpl) {
        List<model.Card> cards = oldDeckImpl.getCards();

        this.cards = cards.stream().map(oldCard -> new Card(oldCard.getCardId(), oldCard.getName(), oldCard.getPower(), oldCard.getRow()))
                .collect(Collectors.toList());
    }

    public Deck(int userId) {
        DeckDAOInterface deckDao = new DeckDAO();
        int deckId = deckDao.getUserDeckId(userId);
        List<Integer> cardIds = deckDao.getDeckCardIds(deckId);
        List<model.Card> cards = new ArrayList<>();
        CardDAOInterface cardDao = new CardDAO();
        for (Integer i : cardIds) {
            model.Card curr = cardDao.getCardById(i);
            cards.add(curr);
        }
        Collections.shuffle(cards);

        this.cards = cards.stream().map(oldCard -> new Card(oldCard.getCardId(), oldCard.getName(), oldCard.getPower(), oldCard.getRow()))
                .collect(Collectors.toList());
    }

    public Deck() {
        cards = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            cards.add(new Card(i, "dummy", i, i % 3));
        }
        shuffleDeck();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void shuffleDeck() {
        Random rand = new Random();
        for (int i = 0; i < cards.size(); i++) {
            int randInt = rand.nextInt(cards.size() - 1);
            Card currCard = cards.get(i);
            cards.set(i, cards.get(randInt));
            cards.set(randInt, currCard);
        }
    }

    public Card drawCard() {
        Card result = cards.get(cards.size() - 1);
        cards.remove(cards.get(cards.size() - 1));

        return result;
    }

    @Override
    public String toString() {
        return "GameLogic.Deck{" +
                "cards=" + cards.toString() +
                '}';
    }
}
