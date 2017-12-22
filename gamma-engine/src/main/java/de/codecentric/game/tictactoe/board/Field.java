package de.codecentric.game.tictactoe.board;

public class Field {

    private Player owner;

    private int number;

    private double value;

    public Field(int number) {
        this.number = number;
        owner = Player.NONE;

        switch (number) {

            case 1:
            case 3:
            case 7:
            case 9:
                value = 0.1d;
                break;

            case 2:
            case 4:
            case 6:
            case 8:
                value = 0.2d;
                break;

            case 5:
                value = 0.25d;
                break;
        }
    }

    public Field(int number, Player owner) {
        this.number = number;
        this.owner = owner;
    }

    public Field copy() {
        Field f = new Field(number, owner);
        return f;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
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
