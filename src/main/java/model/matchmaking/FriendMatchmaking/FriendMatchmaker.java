package model.matchmaking.FriendMatchmaking;

import model.communication.FriendMatchmakingRequest;
import model.communication.IResponse;
import model.communication.MatchmakingRequest;
import model.matchmaking.handlers.FriendHandlers.FriendRequestTypeHandler;
import model.matchmaking.handlers.RequestTypeHandler;
import model.matchmaking.queue.IQueue;
import model.matchmaking.queue.MatchmakingQueue;

public class FriendMatchmaker {
    private final Invite<Integer> mainMap;
    public static final String FRIEND_MATCHMAKING_ATTR = "friendMatchmakingInfo";

    public FriendMatchmaker() {
        this.mainMap = new MatchmakingMap();
    }

    public synchronized IResponse handleMatchmakingRequest(FriendMatchmakingRequest request) {
        return new FriendRequestTypeHandler(mainMap, request).processRequest();
    }

    public Invite<Integer> getMap() {
        return mainMap;
    }
}
