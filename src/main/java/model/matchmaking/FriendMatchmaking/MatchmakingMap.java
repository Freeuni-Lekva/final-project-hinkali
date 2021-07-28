package model.matchmaking.FriendMatchmaking;

import java.util.*;

public class MatchmakingMap implements Invite<Integer> {
    public static final int NO_SESSION = -1;
    public static final int MAX_SESSION_COUNT = 99;
    private Map<Integer, Integer> pairs;
    private Map<Integer, Integer> sessionIds;
    private int sessionCount;

    public MatchmakingMap(){
        this.pairs = new HashMap<>();
        sessionCount = 0;
    }


    @Override
    public boolean sendInvite(Integer sender, Integer receiver) {
        if(contains(sender)) {
            pairs.put(sender, receiver);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeInvite(Integer sender) {
        if(contains(sender)){
            pairs.remove(sender);
            return true;
        }
        return false;
    }

    @Override
    public boolean accept(Integer sender) {
        Integer receiver = pairs.get(sender);
        assert(!sessionIds.containsKey(sender)&&!sessionIds.containsKey(receiver));
        int sessionId = incrementSession();
        sessionIds.put(sender, sessionId);
        sessionIds.put(receiver, sessionId);
        pairs.remove(sender);
        return true;
    }

    @Override
    public boolean contains(Integer sender) {
        return pairs.containsKey(sender);
    }

    @Override
    public Set<Integer> getRequest(Integer receiver) {
        Set<Integer> senders = new HashSet<>();
        for(Integer key : pairs.keySet()){
            if(pairs.get(key).equals(receiver))
                senders.add(key);
        }
        return senders;
    }


    private int incrementSession(){
        if (sessionCount == MAX_SESSION_COUNT) sessionCount = 0;
        return sessionCount++;
    }
}
