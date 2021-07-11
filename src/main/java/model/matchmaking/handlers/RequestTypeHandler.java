package model.matchmaking.handlers;

import model.communication.IResponse;
import model.communication.MatchmakingRequest;
import model.communication.ResponseBuilder;
import model.matchmaking.queue.IQueue;

public class RequestTypeHandler implements RequestHandlerChain {
    private final IQueue<Integer> queue;
    private final MatchmakingRequest request;

    public RequestTypeHandler(IQueue<Integer> queue, MatchmakingRequest request){
        this.queue = queue;
        this.request = request;
    }

    @Override
    public IResponse processRequest() {
        return switch (request.getAction()) {
            case "join" -> new JoinRequestHandler(queue, request).processRequest();
            case "leave" -> new LeaveRequestHandler(queue, request).processRequest();
            default -> ResponseBuilder.getErrorResponse();
        };
    }
}
