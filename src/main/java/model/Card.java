package model;

public class Card {

    public static final int NO_ID = -1;

    private int cardId;
    private String name;
    private String image;
    private int power;
    private int row;

    public Card(int cardId, String name, String image, int power, int row) {
        this.cardId = cardId;
        this.name = name;
        this.image = image;
        this.power = power;
        this.row = row;
    }

    public Card(String name, String image, int power, int row) {
        this(NO_ID, name, image, power, row);
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public int getRow() { return row;}

    public void setImage(String image) {
        this.image = image;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getRow() { return row; }

    public void setRow(int row) { this.row = row; }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Card) {
            Card toCompare = (Card) o;
            return this.cardId == toCompare.cardId;
        }
        return false;
    }
}
