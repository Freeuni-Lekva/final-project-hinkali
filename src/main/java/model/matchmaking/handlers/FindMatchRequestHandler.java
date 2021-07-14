package model.matchmaking.handlers;

import model.communication.IResponse;
import model.communication.MatchmakingRequest;
import model.communication.ResponseBuilder;
import model.matchmaking.queue.IQueue;

public class FindMatchRequestHandler implements RequestHandlerChain {
    private final IQueue<Integer> queue;
    private final MatchmakingRequest request;

    public FindMatchRequestHandler(IQueue<Integer> queue, MatchmakingRequest request){
        this.queue = queue;
        this.request = request;
    }

    @Override
    public IResponse processRequest() {
        int sessionId = queue.getSessionForPlayer(request.getId());
        if (sessionId == -1){
            queue.tryCreatingSession(request.getId());
            return ResponseBuilder.getWaitingResponse();
        }

        return ResponseBuilder.getCreatedResponse(sessionId);
    }
}
