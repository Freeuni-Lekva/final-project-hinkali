package model.game.modified;

import java.util.ArrayList;
import java.util.List;

public class CardRow {
    private final int type;
    private final List<Card> cards;

    public CardRow(int type) {
        this.type = type;
        cards = new ArrayList<>();
    }

    public void addCard(Card c){
        cards.add(c);
    }

    public List<Card> getCards(){
        return cards;
    }

    public int getRowSum(){
        int result = 0;
        for (Card c :
                cards) {
            result += c.getRating();
        }

        return result;
    }

    public int getType(){
        return type;
    }

    @Override
    public String toString() {
        return "GameLogic.CardRow{" +
                "type=" + type +
                ", cards=" + cards +
                '}';
    }
}
