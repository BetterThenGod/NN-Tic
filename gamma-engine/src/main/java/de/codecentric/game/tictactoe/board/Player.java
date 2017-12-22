package de.codecentric.game.tictactoe.board;

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
