package model.game;

import model.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubTable implements SubTableInterface{
    public static final int CARDS_IN_ROW = 3;
    List<Card> playerCards;
    Map<Integer, Integer> rowCounts;
    int points;
    int playerId;

    public SubTable(int playerId){
        this.playerId = playerId;
        playerCards = new ArrayList<>();
        rowCounts = new HashMap<>();
        for(int i =0; i<3; i ++)
            rowCounts.put(i,CARDS_IN_ROW);
    }

    @Override
    public int getPlayerId(){
        return playerId;
    }

    @Override
    public List<Card> getPlayerCards() {
        return playerCards;
    }

    @Override
    public void addCardForPlayer(Card c) {
        int currRow = c.getRow();
        int temp = rowCounts.get(currRow) - 1;
        rowCounts.put(currRow, temp);
        playerCards.add(c);
    }

    @Override
    public boolean isValidMove(Card c){
        int currRow = c.getRow();
        return !(rowCounts.get(currRow).equals(0));
    }

    @Override
    public int getPlayerPoints() {
        return points;
    }

    @Override
    public void setPlayerPoints(int points) {this.points = points; }

    private void computePoints(Card c){
        points += c.getPower();
    }
}
