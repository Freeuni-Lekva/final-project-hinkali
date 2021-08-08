package model.matchmaking.queue;

import java.util.*;

public class MatchmakingQueue implements IQueue<Integer>{
    public static final int NO_SESSION = -1;
    public static final int MAX_SESSION_COUNT = 99;

    // user to sessionId map
    private final Map<Integer, Integer> queue;

    // user to user map
    private final Map<Integer, Integer> pairs;

    private int sessionCount;

    public MatchmakingQueue() {
        this.queue = new HashMap<>();
        this.pairs = new HashMap<>();
        sessionCount = 0;
    }

    @Override
    public boolean push(Integer obj) {
        queue.put(obj, NO_SESSION);

        return false;
    }

    @Override
    public boolean remove(Integer obj) {
        Integer pairId = pairs.get(obj);

        queue.remove(obj);
        queue.replace(pairId, NO_SESSION);
        pairs.remove(obj);
        pairs.remove(pairId);

        return false;
    }

    @Override
    public boolean contains(Integer obj) {
        return queue.containsKey(obj);
    }

    @Override
    public int getSessionForPlayer(Integer obj) {
        return queue.get(obj);
    }

    @Override
    public boolean tryCreatingSession(Integer obj) {
        assert (queue.containsKey(obj));
        if (queue.size() < 2){
            return false;
        }

        List<Integer> users = getAll();
        Integer pair = getPair(users, obj);
        if (pair == -1) return false;

        pairs.put(obj, pair);
        pairs.put(pair, obj);
        int sessionId = incrementSession();
        queue.replace(obj, sessionId);
        queue.replace(pair, sessionId);

        return true;
    }

    private Integer getPair(List<Integer> users, Integer originalId) {
        for (Integer userId :
                users) {
            if (!userId.equals(originalId) && !pairs.containsKey(userId)){
                return userId;
            }
        }

        return -1;
    }

    private int incrementSession(){
        if (sessionCount == MAX_SESSION_COUNT) sessionCount = 0;
        return sessionCount++;
    }

    @Override
    public List<Integer> getAll() {
        Set<Integer> userSet = queue.keySet();
        return new ArrayList<>(userSet);
    }

    @Override
    public String toString() {
        return "MatchmakingQueue{" +
                "queue=" + queue +
                ", pairs=" + pairs +
                ", sessionCount=" + sessionCount +
                '}';
    }
}
