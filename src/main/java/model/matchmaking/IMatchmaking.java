package model.matchmaking;

import model.communication.IResponse;
import model.communication.MatchmakingRequest;

public interface IMatchmaking {
    public IResponse handleMatchmakingRequest(MatchmakingRequest req);
}
