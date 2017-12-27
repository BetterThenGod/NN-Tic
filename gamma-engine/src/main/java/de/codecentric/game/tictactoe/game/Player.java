package de.codecentric.game.tictactoe.game;

public enum Player {

    NONE("-"),

    O("O"),

    X("X");

    private String representation;

    Player(String represnetation) {
        this.representation = represnetation;
    }

    public String getRepresentation() {
        return representation;
    }
}
