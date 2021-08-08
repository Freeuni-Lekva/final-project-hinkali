package model.matchmaking;

import model.communication.IResponse;
import model.communication.MatchmakingRequest;
import model.matchmaking.handlers.JoinRequestHandler;
import model.matchmaking.handlers.RequestTypeHandler;
import model.matchmaking.queue.IQueue;
import model.matchmaking.queue.MatchmakingQueue;

import java.util.HashMap;
import java.util.Map;

public class QueryBasedMatchmaking implements IMatchmaking{
    private final IQueue<Integer> queue;
    public static final String MATCHMAKING_ATTR = "matchmakingInfo";

    public QueryBasedMatchmaking(){
        this.queue = new MatchmakingQueue();
    }

    public synchronized IResponse handleMatchmakingRequest(MatchmakingRequest request) {
        return new RequestTypeHandler(queue, request).processRequest();
    }

    public IQueue<Integer> getQueue() {
        return queue;
    }
}
