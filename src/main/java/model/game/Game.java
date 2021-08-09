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

    public void init(int gameId, int player1Id, int player2Id ){
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

    public int play(){
        while(roundsLeft != 0){
            initiateNewRound();
            playRound();
        }
        if(p1WinsNum>p2WinsNum)
            return p1.getID();
        return p2.getID();
    }

    private void initiateNewRound(){
        p1.startRound();
        p2.startRound();
        p1.setPoint(0);
        p2.setPoint(0);
        p1.clearTable();
        p2.clearTable();
    }

    public void playRound(){
        while(true){
            if(p1.hasEndedRound() && p2.hasEndedRound()){
                if(p1.getPoint()> p2.getPoint())
                    p1WinsNum++;
                else
                    p2WinsNum++;
                roundsLeft --;
                break;
            }
            if(p1.isTurn()){
                servePlayer(p1);
                p1.endTurn();
                p2.startTurn();
            }

            if(p2.isTurn()){
                servePlayer(p2);
                p2.endTurn();
                p1.startTurn();
            }
        }
    }

    private void servePlayer(Player p){
        while(true) {
            Card playedCard = null; // = whatever the method will be;
            if (p.setCardOnTable(playedCard)) {
                p.endRound();
                return;
            }else{
                //not valid card
            }
        }
    }
}
