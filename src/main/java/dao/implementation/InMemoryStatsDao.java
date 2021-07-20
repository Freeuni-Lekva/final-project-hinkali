package dao.implementation;

import commons.beans.StatsBean;
import dao.interfaces.StatsDaoInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryStatsDao implements StatsDaoInterface {
    private final List<StatsBean> statsList;

    public InMemoryStatsDao(){
        statsList = new ArrayList<>();
    }

    @Override
    public void addStatsForNewUser(int userId) {
        statsList.add(new StatsBean(userId));
    }

    @Override
    public boolean setStats(StatsBean stats) {
        int toReplace = -1;
        for (int i = 0; i < statsList.size(); i++) {
            if (statsList.get(i).getUserid() == toReplace){
                toReplace = i;
                break;
            }
        }
        if (toReplace == -1) return false;
        statsList.set(toReplace, stats);
        return true;
    }

    @Override
    public StatsBean getStatsById(int userId) {
        return statsList.stream().filter(s -> s.getUserid() == userId).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<StatsBean> getStatsAll() {
        return statsList;
    }

    @Override
    public ArrayList<StatsBean> getStatsWithDescendingPoints() {
        ArrayList<StatsBean> duplicate = new ArrayList<>();
        Collections.copy(duplicate, statsList);
        duplicate.sort((curr, next) -> curr.getPoints() - next.getPoints());
        return duplicate;
    }

    @Override
    public boolean removeStats(int userId) {
        int toRemove = -1;
        for (int i = 0; i < statsList.size(); i++) {
            if (statsList.get(i).getUserid() == userId){
                toRemove = i;
                break;
            }
        }
        if (toRemove == -1) return false;
        statsList.remove(toRemove);
        return true;
    }

    // fake implementation
    @Override
    public int getRankById(int userId) {
        return userId;
    }
}
