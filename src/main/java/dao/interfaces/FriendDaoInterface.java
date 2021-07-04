package dao.interfaces;
import commons.beans.UserBean;

import java.util.ArrayList;
import java.util.Set;

public interface FriendDaoInterface {

    public boolean sendFriendRequest(int senderId, int receiverId);

    public boolean acceptFriendRequest(int senderId, int receiverId);

    public boolean rejectFriendRequest(int senderId, int receiverId);

    public boolean unfriend(int senderId, int receiverId);

    public Set<Integer> friendIdList(int userId);

}
