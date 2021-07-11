package model.communication;

public class ResponseBuilder {
    public static final String[] responses = {"added", "waiting", "created", "removed", "error"};
    public static final int gameIdNotFound = -1;

    public static Response getAddedToQueueResponse(){
        return new Response(responses[0], gameIdNotFound);
    }

    public static Response getWaitingResponse(){
        return new Response(responses[1], gameIdNotFound);
    }

    public static Response getCreatedResponse(int gameId){
        return new Response(responses[2], gameId);
    }

    public static Response getRemovedResponse(){
        return new Response(responses[3], gameIdNotFound);
    }

    public static Response getErrorResponse(){
        return new Response(responses[4], gameIdNotFound);
    }
}
