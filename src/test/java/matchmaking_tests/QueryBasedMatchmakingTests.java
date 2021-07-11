package matchmaking_tests;

import model.communication.IRequest;
import model.communication.IResponse;
import model.communication.MatchmakingRequest;
import model.communication.ResponseBuilder;
import model.matchmaking.QueryBasedMatchmaking;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryBasedMatchmakingTests {
    private static MatchmakingRequest reqUserAJoin;
    private static MatchmakingRequest reqUserBJoin;
    private static MatchmakingRequest reqUserALeave;
    private static MatchmakingRequest reqUserBLeave;

    @BeforeClass
    public static void setup(){
        reqUserAJoin = new MatchmakingRequest("join", 0);
        reqUserBJoin = new MatchmakingRequest("join", 1);
        reqUserALeave = new MatchmakingRequest("leave", 0);
        reqUserBLeave = new MatchmakingRequest("leave", 1);
    }

    @Test
    public void testJoin(){
        QueryBasedMatchmaking qb = new QueryBasedMatchmaking();
        IResponse expected = ResponseBuilder.getAddedToQueueResponse();
        assertEquals(expected.toJson(), qb.handleMatchmakingRequest(reqUserAJoin).toJson());
        assertEquals(expected.toJson(), qb.handleMatchmakingRequest(reqUserBJoin).toJson());
    }

    @Test
    public void testWaiting(){
        QueryBasedMatchmaking qb = new QueryBasedMatchmaking();
        IResponse expectedA = ResponseBuilder.getAddedToQueueResponse();
        IResponse expectedB = ResponseBuilder.getWaitingResponse();
        assertEquals(expectedA.toJson(), qb.handleMatchmakingRequest(reqUserAJoin).toJson());
        assertEquals(expectedB.toJson(), qb.handleMatchmakingRequest(reqUserAJoin).toJson());
        assertEquals(expectedB.toJson(), qb.handleMatchmakingRequest(reqUserAJoin).toJson());
    }

    @Test
    public void testCreateSession(){
        QueryBasedMatchmaking qb = new QueryBasedMatchmaking();
        IResponse expected = ResponseBuilder.getAddedToQueueResponse();
        assertEquals(expected.toJson(), qb.handleMatchmakingRequest(reqUserAJoin).toJson());
        assertEquals(expected.toJson(), qb.handleMatchmakingRequest(reqUserBJoin).toJson());
        IResponse expectedWait = ResponseBuilder.getWaitingResponse();
        assertEquals(expectedWait.toJson(), qb.handleMatchmakingRequest(reqUserAJoin).toJson());
        IResponse expectedJoin = ResponseBuilder.getCreatedResponse(0);
        assertEquals(expectedJoin.toJson(), qb.handleMatchmakingRequest(reqUserBJoin).toJson());
        assertEquals(expectedJoin.toJson(), qb.handleMatchmakingRequest(reqUserAJoin).toJson());
    }

    @Test
    public void testUserLeaving(){
        QueryBasedMatchmaking qb = new QueryBasedMatchmaking();
        IResponse expected = ResponseBuilder.getAddedToQueueResponse();
        assertEquals(expected.toJson(), qb.handleMatchmakingRequest(reqUserAJoin).toJson());
        assertEquals(expected.toJson(), qb.handleMatchmakingRequest(reqUserBJoin).toJson());
        IResponse expectedWait = ResponseBuilder.getWaitingResponse();
        assertEquals(expectedWait.toJson(), qb.handleMatchmakingRequest(reqUserAJoin).toJson());
        IResponse expectedLeave = ResponseBuilder.getRemovedResponse();
        assertEquals(expectedLeave.toJson(), qb.handleMatchmakingRequest(reqUserBLeave).toJson());
        assertEquals(expectedWait.toJson(), qb.handleMatchmakingRequest(reqUserAJoin).toJson());
        assertEquals(expected.toJson(), qb.handleMatchmakingRequest(reqUserBJoin).toJson());
        assertEquals(expectedWait.toJson(), qb.handleMatchmakingRequest(reqUserBJoin).toJson());
        IResponse expectedJoin = ResponseBuilder.getCreatedResponse(1);
        assertEquals(expectedJoin.toJson(), qb.handleMatchmakingRequest(reqUserBJoin).toJson());
        assertEquals(expectedJoin.toJson(), qb.handleMatchmakingRequest(reqUserAJoin).toJson());
    }
}
