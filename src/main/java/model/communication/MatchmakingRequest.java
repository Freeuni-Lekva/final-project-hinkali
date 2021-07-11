package model.communication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MatchmakingRequest implements IRequest{
    @SerializedName("action")
    private String action;
    private int id;

    public MatchmakingRequest(String action){
        this.action = action;
    }

    public MatchmakingRequest(){}

    public String getAction() {
        return action;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "MatchmakingRequest{" +
                "action='" + action + '\'' +
                ", id=" + id +
                '}';
    }
}
