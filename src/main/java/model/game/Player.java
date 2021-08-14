package model.game;

import dao.implementation.CardDAO;
import dao.implementation.DeckDAO;
import dao.interfaces.CardDAOInterface;
import dao.interfaces.DeckDAOInterface;
import model.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements PlayerInterface {
    public final int STARTER_CARDS = 5;
    int playerId;
    List<Card> shuffledDeck;
    List<Card> heldCards;
    boolean ownTurn;
    boolean activeInRound;
    Table table;


    public Player(int playerId, boolean ownTurn, Table table){
        this.playerId = playerId;
        this.ownTurn = ownTurn;
        this.activeInRound = true;
        setPlayerDeck();
        this.table = table;
    }

    @Override
    public int getID(){
        return playerId;
    }

    @Override
    public int getPoint() {
        return table.getPlayerPoints(playerId);
    }

    @Override
    public void setPoint(int point) {
        table.getSubTableForPlayer(playerId).setPlayerPoints(point);
    }


    @Override
    public List<Card> getHeldCards() {
        return heldCards;
    }

    @Override
    public void setPlayerDeck() {
        DeckDAOInterface deckDao = new DeckDAO();
        int deckId = deckDao.getUserDeckId(playerId);
        List<Integer> cardIds = deckDao.getDeckCardIds(deckId);
        List<Card> cards = new ArrayList<>();
        CardDAOInterface cardDao = new CardDAO();
        for(Integer i : cardIds){
            Card curr = cardDao.getCardById(i);
            cards.add(curr);
        }
        Collections.shuffle(cards);
        shuffledDeck = cards;
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
    public void startRound() {
        activeInRound = true;
    }

    @Override
    public void endRound() { activeInRound = false; }

    @Override
    public boolean isTurn(){
        return ownTurn;
    }

    @Override
    public boolean hasEndedRound(){
        return activeInRound;
    }

    @Override
    public void clearTable() {
        table.clearSubTable(playerId);
    }

    @Override
    public boolean setCardOnTable(Card c){
       boolean successfulMove = table.setCardForPlayer(playerId, c);
       if(successfulMove) {
           heldCards.remove(c);
           return true;
       }
       return false;
    }

    @Override
    public Card findCardInHand(int id) {
        for(int i = 0; i < heldCards.size(); i++){
            if(heldCards.get(i).getCardId() == id){
                return heldCards.get(i);
            }
        }
        return null;
    }
}
