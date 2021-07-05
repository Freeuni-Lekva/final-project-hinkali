package dao.interfaces;
import commons.beans.UserBean;

import java.util.ArrayList;
import java.util.Set;

public interface FriendDaoInterface {

     boolean sendFriendRequest(int senderId, int receiverId);

     boolean acceptFriendRequest(int senderId, int receiverId);

     boolean rejectFriendRequest(int senderId, int receiverId);

     boolean unfriend(int senderId, int receiverId);

     Set<Integer> friendIdList(int userId);

}
