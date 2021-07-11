package model.communication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MatchmakingRequest implements IRequest{
    @SerializedName("action")
    private String action;
    private int id;

    public MatchmakingRequest(String action, int id){
        this.action = action;
        this.id = id;
    }

    public MatchmakingRequest(){
        this.id = -1;
    }

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
