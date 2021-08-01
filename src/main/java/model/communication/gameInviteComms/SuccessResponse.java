package model.communication.gameInviteComms;

import com.google.gson.Gson;
import commons.beans.UserBean;
import model.communication.IResponse;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuccessResponse that = (SuccessResponse) o;
        return gameId == that.gameId && Objects.equals(state, that.state);
    }

    public int getGameId() {
        return gameId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, gameId);
    }
}
