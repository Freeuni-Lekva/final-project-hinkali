package commons.beans;

import java.util.Objects;

public class StatsBean {
    public static final int POINTS_ON_WIN = 3;
    public static final int POINTS_ON_DRAW = 1;
    public static final int POINTS_ON_LOSS = 0;

    private final int userid;
    private int gamesPlayed;
    private int wins;
    private int draws;
    private int losses;
    private int points;

    public StatsBean(int userid, int gamesPlayed, int wins, int draws, int losses, int points){
        this.userid = userid;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.points = points;
    }

    public StatsBean(int userid){
        this(userid, 0, 0, 0,0, 0);
    }

    public int getUserid() {
        return userid;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addWin(){
        this.gamesPlayed++;
        this.wins++;
        this.points += POINTS_ON_WIN;
    }

    public void addDraw(){
        this.gamesPlayed++;
        this.draws++;
        this.points += POINTS_ON_DRAW;
    }

    public void addLoss(){
        this.gamesPlayed++;
        this.losses++;
        this.points += POINTS_ON_WIN;
    }
}
