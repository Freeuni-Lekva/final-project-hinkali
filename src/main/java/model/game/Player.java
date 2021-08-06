package model.game;

import model.Card;

import java.util.List;

public class Player implements PlayerInterface {
    public final int STARTER_CARDS = 5;
    int playerId;
    int points;
    List<Card> shuffledDeck;
    List<Card> heldCards;
    boolean ownTurn;
    boolean activeInRound;


    public Player(int playerId, List<Card> shuffledDeck, boolean ownTurn){
        this.playerId = playerId;
        this.shuffledDeck = shuffledDeck;
        this.ownTurn = ownTurn;
        this.points = 0;
        this.activeInRound = true;
    }

    @Override
    public int getPoint() {
        return points;
    }

    @Override
    public void setPoint(int point) {
        this.points = points;
    }

    @Override
    public void setShuffledDeck(List<Card> cards) {
        this.shuffledDeck = shuffledDeck;
    }

    @Override
    public List<Card> getHeldCards() {
        return heldCards;
    }

    @Override
    public void setPlayerCards() {
        for(int i = 0; i<STARTER_CARDS; i++){
            heldCards.add(shuffledDeck.get(0));
            shuffledDeck.remove(0);
        }
    }

    @Override
    public void addCardToPlayer(Card card) {
        heldCards.add(card);
    }

    @Override
    public boolean hasPlayableCards() {
        return !heldCards.isEmpty();
    }

    @Override
    public void endTurn() {
        ownTurn = false;
    }

    @Override
    public void startTurn() {
        ownTurn = true;
    }

    @Override
    public void endRound() {
        activeInRound = false;
    }

    @Override
    public boolean isTurn(){
        return ownTurn;
    }

    @Override
    public boolean hasEndedRound(){
        return activeInRound;
    }

}
