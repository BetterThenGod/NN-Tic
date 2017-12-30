package de.codecentric.game.tictactoe.game;

public class Field {

    private PlayerEnum owner;

    private int number;

    public Field(int number) {
        this.number = number;
        owner = PlayerEnum.NONE;
    }

    public Field(int number, PlayerEnum owner) {
        this.number = number;
        this.owner = owner;
    }

    public Field copy() {
        Field f = new Field(number, owner);
        return f;
    }

    public PlayerEnum getOwner() {
        return owner;
    }

    public void setOwner(PlayerEnum owner) {
        this.owner = owner;
    }

    public int getNumber() {
        return number;
    }

    public String screenRepresentation() {
        return " (" + number + ") " + owner.getRepresentation() + " ";
    }
}
