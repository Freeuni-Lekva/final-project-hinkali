package model.gameInviteHandler;

import commons.beans.UserBean;
import model.communication.IResponse;
import model.communication.gameInviteComms.FailedResponse;
import model.communication.gameInviteComms.SuccessResponse;
import model.communication.gameInviteComms.WaitingResponse;

import java.util.*;

public class GameInviteManager implements IGameInviteManager{
    private final Map<UserBean, List<UserBean>> pendingInvites;
    private final Map<UserBean, Integer> usersToGameIds;
    private final Map<UserBean, UserBean> lastInvited;
    private int sessionCount;
    public static final int INITIAL_GAME_ID = 1024;

    public GameInviteManager(){
        pendingInvites = new HashMap<>();
        usersToGameIds = new HashMap<>();
        lastInvited = new HashMap<>();
        sessionCount = INITIAL_GAME_ID;
    }

    @Override
    public void handleInviteRequest(UserBean sender, UserBean receiver) {
        if (pendingInvites.containsKey(receiver)){
            pendingInvites.get(receiver).add(sender);
            lastInvited.put(sender, receiver);
            return;
        }

        pendingInvites.put(receiver, new ArrayList<>(Collections.singleton(sender)));
        lastInvited.put(sender, receiver);
    }

    @Override
    public IResponse handleAwaitRequest(UserBean user) {
        if (usersToGameIds.containsKey(user)){
            int gameId = usersToGameIds.get(user);
            usersToGameIds.remove(user);
            return new SuccessResponse(gameId);
        }

        if (!pendingInvites.containsKey(lastInvited.get(user))){
            return new FailedResponse();
        }

        return new WaitingResponse();
    }

    @Override
    public IResponse handleLeaveRequest(UserBean user) {
        assert (!usersToGameIds.containsKey(user));
        pendingInvites.get(lastInvited.get(user)).remove(user);
        lastInvited.remove(user);
        return new FailedResponse();
    }

    @Override
    public IResponse handleAcceptRequest(UserBean receiver, UserBean sender) {
        assert (pendingInvites.containsKey(receiver));
        assert (pendingInvites.get(receiver).contains(sender));
        int gameId = createGame(sender, receiver);
        return new SuccessResponse(gameId);
    }

    @Override
    public List<UserBean> getAllInvites(UserBean user) {
        return pendingInvites.get(user);
    }

    public Map<UserBean, Integer> getUsersToGameIds() {
        return usersToGameIds;
    }

    public Map<UserBean, UserBean> getLastInvited() {
        return lastInvited;
    }

    private int createGame(UserBean sender, UserBean receiver){
        int gameId = sessionCount++;
        updateGameIdForUser(sender, gameId);
        updateGameIdForUser(receiver, gameId);
        pendingInvites.remove(receiver);
        return gameId;
    }

    private void updateGameIdForUser(UserBean sender, int gameId) {
        if (usersToGameIds.containsKey(sender)){
            usersToGameIds.replace(sender, gameId);
        }else{
            usersToGameIds.put(sender, gameId);
        }
    }
}
