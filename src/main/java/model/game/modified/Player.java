package model.game.modified;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private final int id;
    private final Deck deck;
    private final List<Card> hand;

    private HalfBoard board;
    private boolean hasPassed;
    private int livesLeft;

    public Player(int id, Deck deck) {
        this.id = id;
        this.deck = deck;
        this.hand = new ArrayList<>();
        this.hasPassed = false;
        this.livesLeft = 2;
        this.board = new HalfBoard();
    }

    public int getId() {
        return id;
    }

    public Deck getDeck(){
        return deck;
    }

    public HalfBoard getBoard() {
        return board;
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public String showHand() {
        return hand.toString();
    }

    public void drawCards() {
        for (int i = 0; i <= 5; i++) {
            if (deck.isEmpty()) return;
            hand.add(deck.drawCard());
        }
    }

    public Card useCard(int index) {
        assert index > 0 && index < hand.size();
        Card result = hand.get(index);
        hand.remove(index);
        board.addCard(result);

        return result;
    }

    public Card useCardById(int id) {
        for (Card c : hand) {
            if (c.getId() == id) {
                hand.remove(c);
                board.addCard(c);

                return c;
            }
        }

        return null;
    }

    public void passRound() {
        hasPassed = true;
    }

    public void initNextRound() {
        board = new HalfBoard();
        hasPassed = false;
    }

    // having an empty hand counts as having passed the round
    public boolean hasPlayerPassed() {
        return hasPassed || hand.isEmpty();
    }

    public void loseLife() {
        livesLeft--;
    }

    public List<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "GameLogic.Player{" +
                "name='" + id + '\'' +
                ", hasPassed=" + hasPassed +
                ", livesLeft=" + livesLeft +
                '}';
    }
}
