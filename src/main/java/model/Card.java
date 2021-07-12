package model;

public class Card {

    private final int cardId;
    private String name;
    private String image;
    private int power;

    public Card(int cardId, String name, String image, int power) {
        this.cardId = cardId;
        this.name = name;
        this.image = image;
        this.power = power;
    }

    public int getCardId() {
        return cardId;
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

    public void setImage(String image) {
        this.image = image;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
