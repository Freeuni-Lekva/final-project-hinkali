package model.communication.gameInviteComms;

import com.google.gson.Gson;
import commons.beans.UserBean;
import model.communication.IResponse;

import java.util.Objects;

public class WaitingResponse implements IResponse {
    private final String state;

    public WaitingResponse(){
        state = "waiting";
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaitingResponse that = (WaitingResponse) o;
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
