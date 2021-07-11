package model.matchmaking.handlers;

import model.communication.IResponse;
import model.communication.MatchmakingRequest;
import model.communication.ResponseBuilder;
import model.matchmaking.queue.IQueue;

public class RequestTypeHandler implements RequestHandlerChain {
    private final IQueue queue;
    private final MatchmakingRequest request;

    public RequestTypeHandler(IQueue queue, MatchmakingRequest request){
        this.queue = queue;
        this.request = request;
    }

    @Override
    public IResponse processRequest() {
        switch (request.getAction()){
            case "join": return new JoinRequestHandler(queue, request).processRequest();
            case "leave": return new LeaveRequestHandler(queue, request).processRequest();
            default: return ResponseBuilder.getErrorResponse();
        }
    }
}
