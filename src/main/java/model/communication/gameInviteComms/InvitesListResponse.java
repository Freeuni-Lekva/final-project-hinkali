package model.communication.gameInviteComms;

import com.google.gson.Gson;
import commons.beans.UserBean;
import model.communication.IResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InvitesListResponse implements IResponse {
    private final List<String> senders;

    public InvitesListResponse(List<UserBean> inviters){
        if(inviters == null) {
            this.senders = new ArrayList<>();
            return;
        }
        this.senders = inviters.stream().map(UserBean::getUsername).collect(Collectors.toList());
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
