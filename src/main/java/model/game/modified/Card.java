package model.game.modified;

public class Card {
    public static final int MELEE = 0;
    public static final int RANGED = 1;
    public static final int ARTILLERY = 2;

    private final int id;
    private final String name;
    private final int rating;
    private final int type;

    public Card(int id, String name, int rating, int type) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "GameLogic.Card{" +
                "rating=" + rating +
                ", type=" + type +
                '}';
    }
}
