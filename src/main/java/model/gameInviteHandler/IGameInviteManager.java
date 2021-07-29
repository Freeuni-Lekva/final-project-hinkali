package model.gameInviteHandler;

import commons.beans.UserBean;
import model.communication.IResponse;

import java.util.List;

public interface IGameInviteManager {
    void handleInviteRequest(UserBean sender, UserBean receiver);
    IResponse handleAwaitRequest(UserBean user);
    IResponse handleLeaveRequest(UserBean user);
    IResponse handleAcceptRequest(UserBean receiver, UserBean sender);
    List<UserBean> getAllInvites(UserBean user);
}
