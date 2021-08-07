package model.game;

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
    private List<Integer> wonRounds;

    public void init(int gameId, int player1Id, int player2Id ){
        this.gameId = gameId;
        Random rd = new Random();
        boolean startingPlayer = rd.nextBoolean();
        table = new Table(player1Id,player2Id);
        p1 = new Player(player1Id, startingPlayer, table);
        p2 = new Player(player2Id, !startingPlayer, table);
        roundsLeft = 3;
        wonRounds = new ArrayList<>();
    }

    public int play(){
        while(roundsLeft != 0){
            playRound();
        }
        int player1WonRounds = 0;
        int player2WonRounds = 0;
        for(int i =0; i < wonRounds.size(); i++){
            if(wonRounds.get(i) == p1.getID())
                player1WonRounds++;
            player2WonRounds++;
        }
        if(player1WonRounds>player2WonRounds)
            return p1.getID();
        return p2.getID();
    }

    public void playRound(){
        while(true){
            if(p1.hasEndedRound() && p2.hasEndedRound()){
                if(p1.getPoint()> p2.getPoint())
                    wonRounds.add(p1.getID());
                else
                    wonRounds.add(p2.getID());
                roundsLeft --;
                break;
            }
            //rest of the Round logic

        }
    }
}
