package model;

import model.game.Game;
import model.game.modified.GameModified;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {

    public static final String GAME_MANAGER_ATTR = "gameManager";

    private Map<Integer, GameModified> gameMap; // gameId -> Game
    private Map<Integer, GameModified> userGameMap; // userId -> Game
    private Map<Integer, List<Integer>> gameUserIds; // gameId -> user ids

    public GameManager() {
        gameMap = new HashMap<>();
        userGameMap = new HashMap<>();
        gameUserIds = new HashMap<>();
    }

    public Map<Integer, GameModified> getGameMap() {
        return gameMap;
    }

    public Map<Integer, GameModified> getUserGameMap() {
        return userGameMap;
    }

    public Map<Integer, List<Integer>> getGameUserIds() {
        return gameUserIds;
    }
}
