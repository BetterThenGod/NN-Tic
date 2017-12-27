package de.codecentric.game.tictactoe.game;

public enum PlayerEnum {

    NONE("-"),

    O("O"),

    X("X");

    private String representation;

    PlayerEnum(String represnetation) {
        this.representation = represnetation;
    }

    public String getRepresentation() {
        return representation;
    }
}
