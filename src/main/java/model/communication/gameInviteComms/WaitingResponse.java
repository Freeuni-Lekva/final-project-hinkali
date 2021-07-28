package model.communication.gameInviteComms;

import com.google.gson.Gson;
import commons.beans.UserBean;
import model.communication.IResponse;

public class WaitingResponse implements IResponse {
    private final String state;

    public WaitingResponse(){
        state = "waiting";
;
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
