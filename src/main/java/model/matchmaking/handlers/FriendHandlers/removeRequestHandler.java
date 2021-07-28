package model.matchmaking.handlers.FriendHandlers;

import model.communication.FriendMatchmakingRequest;
import model.communication.IResponse;
import model.communication.ResponseBuilder;
import model.matchmaking.FriendMatchmaking.Invite;

public class removeRequestHandler {
    private final Invite<Integer> mainMap;
    private final FriendMatchmakingRequest request;

    public removeRequestHandler(Invite<Integer> mainMap, FriendMatchmakingRequest request){
        this.mainMap = mainMap;
        this.request = request;
    }

    public IResponse processRequest() {
        //if (mainMap.contains(request.getSender())){
        //    return new FindMatchRequestHandler(queue, request).processRequest();
        // }

        mainMap.removeInvite(request.getSender());
        return ResponseBuilder.getAddedToQueueResponse();
    }
}
