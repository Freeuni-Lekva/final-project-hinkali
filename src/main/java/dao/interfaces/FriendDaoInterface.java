package dao.interfaces;
import commons.beans.UserBean;

import java.util.ArrayList;
import java.util.Set;

public interface FriendDaoInterface {

     boolean sendFriendRequest(int senderId, int receiverId);

     boolean acceptFriendRequest(int senderId, int receiverId);

     boolean rejectFriendRequest(int senderId, int receiverId);

     boolean unfriend(int userId, int friendId);

     Set<Integer> friendIdList(int userId);

     Set<Integer> pendingList(int userId);

     boolean isFriend(int currUserId, int userToCheck);

     boolean isPendingFriend(int currUserId, int userToCheck);

}
