package model.game;

import model.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subtable implements SubtableInterface{
    public static final int CARDS_IN_ROW = 3;
    List<Card> playerCards;
    Map<Integer, Integer> rowCounts;
    int points;

    public Subtable(){
        playerCards = new ArrayList<>();
        rowCounts = new HashMap<>();
        for(int i =0; i<3; i ++)
            rowCounts.put(i,CARDS_IN_ROW);
    }


    @Override
    public List<Card> getPlayerCards() {
        return playerCards;
    }

    @Override
    public boolean addCardForPlayer(Card c) {
        int currRow = c.getRow();
        if(rowCounts.get(currRow).equals(0))
            return false;
        int temp = rowCounts.get(currRow) - 1;
        rowCounts.put(currRow, temp);
        playerCards.add(c);
        computePoints(c);
        return true;
    }

    @Override
    public int getPlayerPoints() {
        return points;
    }

    private void computePoints(Card c){
        points += c.getPower();
    }
}
