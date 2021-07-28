package model.matchmaking.FriendMatchmaking;

import java.util.List;
import java.util.Set;

public interface Invite<T> {
    public boolean sendInvite(T sender, T receiver);
    public boolean removeInvite(T sender);
    public boolean accept(T sender);
    public boolean contains(T sender);
    public Set<T> getRequest(T receiver);
}
