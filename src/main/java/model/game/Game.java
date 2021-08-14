package model.game;

import model.Card;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private int gameId;
    private Player p1;
    private Player p2;
    private Table table;
    private int roundsLeft;
    private int p1WinsNum;
    private int p2WinsNum;

    public Game (int gameId, int player1Id, int player2Id ){
        this.gameId = gameId;
        Random rd = new Random();
        boolean startingPlayer = rd.nextBoolean();
        table = new Table(player1Id,player2Id);
        p1 = new Player(player1Id, startingPlayer, table);
        p2 = new Player(player2Id, !startingPlayer, table);
        roundsLeft = 3;
        p1WinsNum = 0;
        p2WinsNum = 0;
    }

    public int getPlayerWonRounds(int id){
        if(p1.getID() == id)
            return p1WinsNum;
        if(p2.getID() == id)
            return p2WinsNum;
        return -1;
    }

    public int getPlayerScore(int id) {
        if (p1.getID() == id)
            return p1.getPoint();
        if (p2.getID() == id)
            return p2.getPoint();
        return -1;
    }
    public int numRoundsLeft(){return roundsLeft;}

    public List<Card> getPlayerHeldCards(int id) {
        if (p1.getID() == id)
            return p1.getHeldCards();
        if (p2.getID() == id)
            return p2.getHeldCards();
        return null;
    }
        public void playCard(int cardId){
        Player p = getActivePlayer();
        Card c = p.findCardInHand(cardId);
        if(c != null)
            p.setCardOnTable(c);
        p.endRound();
        switchActivePlayer();
        newRound();
    }

    public void skipRound(){
        getActivePlayer().endRound();
        switchActivePlayer();
        newRound();
    }

    private void newRound(){
        if(p1.hasEndedRound() && p2.hasEndedRound() && roundsLeft > 0){
            if(p1.getPoint() > p2.getPoint())
                p1WinsNum++;
            else
                p2WinsNum++;
            initiateNewRound();
            roundsLeft--;
        }
    }

    private Player getActivePlayer(){
        if(p1.isTurn())
            return p1;
        return p2;
    }

    private void switchActivePlayer(){
        if(p1.isTurn()){
            p1.endTurn();
            p2.startTurn();
        }else{
            p2.endTurn();
            p1.startTurn();
        }
    }
    private void initiateNewRound(){
        p1.startRound();
        p2.startRound();
        p1.setPoint(0);
        p2.setPoint(0);
        p1.clearTable();
        p2.clearTable();
    }

//    public int play(){
//        while(roundsLeft != 0){
//            initiateNewRound();
//            playRound();
//        }
//        if(p1WinsNum>p2WinsNum)
//            return p1.getID();
//        return p2.getID();
//    }
//
//    public void playRound(){
//        while(true){
//            if(p1.hasEndedRound() && p2.hasEndedRound()){
//                if(p1.getPoint()> p2.getPoint())
//                    p1WinsNum++;
//                else
//                    p2WinsNum++;
//                roundsLeft --;
//                break;
//            }
//            if(p1.isTurn()){
//                servePlayer(p1);
//                p1.endTurn();
//                p2.startTurn();
//            }
//
//            if(p2.isTurn()){
//                servePlayer(p2);
//                p2.endTurn();
//                p1.startTurn();
//            }
//        }
//    }
//
//    private void servePlayer(Player p){
//        while(true) {
//            Card playedCard = null; // = whatever the method will be;
//            if (p.setCardOnTable(playedCard)) {
//                p.endRound();
//                return;
//            }else{
//                //not valid card
//            }
//        }
//    }
}
