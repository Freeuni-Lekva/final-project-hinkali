package dao.implementation;

import dao.interfaces.FriendDaoInterface;

import java.util.Set;

public class InMemoryFriendDao implements FriendDaoInterface {


    @Override
    public boolean sendFriendRequest(int senderId, int receiverId) {
        return false;
    }

    @Override
    public boolean acceptFriendRequest(int senderId, int receiverId) {
        return false;
    }

    @Override
    public boolean rejectFriendRequest(int senderId, int receiverId) {
        return false;
    }

    @Override
    public boolean unfriend(int userId, int friendId) {
        return false;
    }

    @Override
    public Set<Integer> friendIdList(int userId) {
        return null;
    }

    @Override
    public Set<Integer> pendingList(int userId) {
        return null;
    }

    @Override
    public boolean isFriend(int currUserId, int userToCheck) {
        return false;
    }

    @Override
    public boolean isPendingFriend(int currUserId, int userToCheck) {
        return false;
    }
}
