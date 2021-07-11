package model.matchmaking.handlers;

import model.communication.IResponse;
import model.communication.MatchmakingRequest;
import model.communication.ResponseBuilder;
import model.matchmaking.queue.IQueue;

public class JoinRequestHandler implements RequestHandlerChain {
    private final IQueue<Integer> queue;
    private final MatchmakingRequest request;

    public JoinRequestHandler(IQueue<Integer> queue, MatchmakingRequest request){
        this.queue = queue;
        this.request = request;
    }

    @Override
    public IResponse processRequest() {
        if (queue.contains(request.getId())){
            return new FindMatchRequestHandler(queue, request).processRequest();
        }

        queue.push(request.getId());
        return ResponseBuilder.getAddedToQueueResponse();
    }
}
