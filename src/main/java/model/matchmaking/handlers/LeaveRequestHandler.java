package model.matchmaking.handlers;

import model.communication.IResponse;
import model.communication.MatchmakingRequest;
import model.communication.ResponseBuilder;
import model.matchmaking.queue.IQueue;

public class LeaveRequestHandler implements RequestHandlerChain {
    private final IQueue<Integer> queue;
    private final MatchmakingRequest request;

    public LeaveRequestHandler(IQueue<Integer> queue, MatchmakingRequest request){
        this.queue = queue;
        this.request = request;
    }

    @Override
    public IResponse processRequest() {
        if (queue.contains(request.getId())){
            queue.remove(request.getId());
            return ResponseBuilder.getRemovedResponse();
        }

        // add to queue user
        return ResponseBuilder.getErrorResponse();
    }
}
