package model.matchmaking;

import java.util.*;

/*
    basic in-memory implementation
    not for
 */
public class BasicMatchmakingQueue {
    public static final int MAX_SIZE = 32;
    public static final String MATCHMAKING_ATTR = "matchmakingInfo";

    public static final String GAME_ID_PREFIX = "";
    public static final String ADDED_TO_QUEUE = "added";
    public static final String WAITING = "waiting";
    // user_id
    private final Set<Integer> userSet;

    // user_id to game_id
    private final Map<Integer, Integer> newPairs;

    private int game_id_count;

    public BasicMatchmakingQueue(){
        userSet = new HashSet<>();
        newPairs = new HashMap<>();
        game_id_count = 0;
    }

    public synchronized String processMatchmakingRequestById(int userid){
        if (queueFull()){
            return WAITING;
        }

        if (hasGameId(userid)){
            return GAME_ID_PREFIX + newPairs.get(userid);
        }

        if (userNotYetAdded(userid)){
            userSet.add(userid);
            return ADDED_TO_QUEUE;
        }

        tryMatchingUsers(userid);

        return WAITING;
    }

    private boolean userNotYetAdded(int userid) {
        return !userSet.contains(userid);
    }

    private boolean hasGameId(int userid) {
        return newPairs.containsKey(userid);
    }

    private boolean queueFull() {
        return userSet.size() == MAX_SIZE;
    }

    private void tryMatchingUsers(int userid) {
        if (userSet.size() > 1){
            int pairId = getPairingId(userid);
            newPairs.put(userid, game_id_count);
            newPairs.put(pairId, game_id_count);
            game_id_count++;
            resetGameCount();
        }
    }

    private void resetGameCount() {
        if (game_id_count == 33) game_id_count = 0;
    }

    public int getGame_id_count(){
        return game_id_count;
    }

    private int getPairingId(int userid) {
        for (int nextId : userSet) {
            if (nextId != userid) {
                return nextId;
            }
        }

        return -1;
    }
}
