package model.matchmaking.handlers.FriendHandlers;

import model.communication.FriendMatchmakingRequest;
import model.communication.IResponse;
import model.communication.MatchmakingRequest;
import model.communication.ResponseBuilder;
import model.matchmaking.FriendMatchmaking.Invite;
import model.matchmaking.handlers.FindMatchRequestHandler;
import model.matchmaking.queue.IQueue;

public class sendRequestHandler {
    private final Invite<Integer> mainMap;
    private final FriendMatchmakingRequest request;

    public sendRequestHandler(Invite<Integer> mainMap, FriendMatchmakingRequest request){
        this.mainMap = mainMap;
        this.request = request;
    }

    public IResponse processRequest() {
        //if (mainMap.contains(request.getSender())){
        //    return new FindMatchRequestHandler(queue, request).processRequest();
       // }

        mainMap.sendInvite(request.getSender(), request.getReceiver());
        return ResponseBuilder.getAddedToQueueResponse();
    }
}
