package de.codecentric.game.tictactoe.game;

public class Field {

    private PlayerEnum owner;

    private int number;

    private double value;

    public Field(int number) {
        this.number = number;
        owner = PlayerEnum.NONE;

        switch (number) {

            case 1:
            case 3:
            case 7:
            case 9:
                value = 0.2d;
                break;

            case 2:
            case 4:
            case 6:
            case 8:
                value = 0.3d;
                break;

            case 5:
                value = 0.4d;
                break;
        }
    }

    public Field(int number, PlayerEnum owner, double value) {
        this.number = number;
        this.owner = owner;
        this.value = value;
    }

    public Field copy() {
        Field f = new Field(number, owner, value);
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

    public double getValue() {
        return value;
    }

    public String screenRepresentation() {
        return " (" + number + ") " + owner.getRepresentation() + " ";
    }
}
