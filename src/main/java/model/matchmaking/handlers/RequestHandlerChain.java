package model.matchmaking.handlers;

import model.communication.IRequest;
import model.communication.IResponse;
import model.communication.MatchmakingRequest;

public interface RequestHandlerChain {
    public IResponse processRequest();
}
