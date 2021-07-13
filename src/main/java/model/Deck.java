package model;

import java.util.List;
import java.util.Set;

public class Deck {

    public static final String DECK_ATTR = "deck";
    public static final int NO_ID = -1;

    private final int deckId;
    private String name;
    private String image;
    private List<Card> cards;
//    private Set<Card> cards

    public Deck(int deckId, String name, String image, List<Card> cards) {
        this.deckId = deckId;
        this.name = name;
        this.image = image;
        this.cards = cards;
    }

    public Deck(String name, String image, List<Card> cards) { this(NO_ID, name, image, cards); }

    public int getDeckId() {
        return deckId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Card> getCards() { return cards; }

    public void setCards(List<Card> cards) { this.cards = cards; }
}
