package model.communication.gameInviteComms;

import com.google.gson.Gson;
import commons.beans.UserBean;
import model.communication.IResponse;

public class SuccessResponse implements IResponse {
    private final String state;
    private final int gameId;

    public SuccessResponse(int gameId){
        state = "success";
        this.gameId = gameId;
    }


    @Override
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
