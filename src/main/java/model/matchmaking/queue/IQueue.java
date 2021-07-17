package model.matchmaking.queue;

import java.util.List;

public interface IQueue<T> {
    public boolean push(T obj);
    public boolean remove(T obj);
    public boolean contains(T obj);
    public int getSessionForPlayer(T obj);
    public boolean tryCreatingSession(T obj);
    public List<T> getAll();
}
