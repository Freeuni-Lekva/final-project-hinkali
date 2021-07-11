package model.communication;

import com.google.gson.Gson;

public class Response implements IResponse{
    private String body;
    private int gameId;

    public Response(String body, int gameId){
        this.body = body;
        this.gameId = gameId;
    }

    @Override
    public String toJson() {
        return new Gson().toJson(this);
    }
}
