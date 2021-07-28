package model.matchmaking.handlers.FriendHandlers;

import model.communication.FriendMatchmakingRequest;
import model.communication.IResponse;
import model.communication.MatchmakingRequest;
import model.communication.ResponseBuilder;
import model.matchmaking.FriendMatchmaking.Invite;
import model.matchmaking.handlers.JoinRequestHandler;
import model.matchmaking.handlers.LeaveRequestHandler;
import model.matchmaking.queue.IQueue;

public class FriendRequestTypeHandler {
    private final Invite<Integer> mainMap;
    private final FriendMatchmakingRequest request;

    public FriendRequestTypeHandler(Invite<Integer> mainMap, FriendMatchmakingRequest request){
        this.mainMap = mainMap;
        this.request = request;
    }

    public IResponse processRequest() {
        IResponse response;
        switch (request.getAction()) {
            case "join": response = new sendRequestHandler(mainMap, request).processRequest(); break;
            case "leave": response = new removeRequestHandler(mainMap, request).processRequest(); break;
            default: response = ResponseBuilder.getErrorResponse();
        };
        return response;
    }
}
