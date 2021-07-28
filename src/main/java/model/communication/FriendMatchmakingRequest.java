package model.communication;

import com.google.gson.annotations.SerializedName;

public class FriendMatchmakingRequest {
    @SerializedName("action")
    private String action;
    private int sender;
    private int receiver;

    public FriendMatchmakingRequest(String action, int sender, int receiver){
        this.action = action;
        this.sender = sender;
        this.receiver = receiver;
    }


    public String getAction() {
        return action;
    }

    public int getSender(){
        return sender;
    }

    public int getReceiver() { return  receiver;}

    public void setSender(int sender){
        this.sender = sender;
    }

    public void setReceiver(int receiver){
        this.receiver = receiver;
    }


    @Override
    public String toString() {
        return "MatchmakingRequest{" +
                "action='" + action + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                '}';
    }
}
