package de.codecentric.game.tictactoe.board;

public enum Owner {

    NONE("-"),

    BLUE("O"),

    RED("X");

    private String representation;

    Owner(String represnetation) {
        this.representation = represnetation;
    }

    public String getRepresentation() {
        return representation;
    }
}
