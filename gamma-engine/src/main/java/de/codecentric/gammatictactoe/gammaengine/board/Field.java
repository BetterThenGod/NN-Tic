package de.codecentric.gammatictactoe.gammaengine.board;

public class Field {

    private Owner owner;

    private int number;

    public Field(int number) {
        this.number = number;
        owner = Owner.NONE;
    }

    public Field(int number, Owner owner) {
        this.number = number;
        this.owner = owner;
    }

    public Field copy() {
        Field f = new Field(number, owner);
        return f;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public int getNumber() {
        return number;
    }

    public String screenRepresentation() {
        return " (" + number + ") " + owner.getRepresentation() + " ";
    }
}
