package de.codecentric.game.tictactoe.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private static final int ROW_DIMENSION = 3;

    private static final int COL_DIMENSION = 3;

    private Map<Integer, Field> playingBoard = new HashMap<>();

    public Board() {
        initialize();
    }

    public void initialize() {
        for (int row = 1; row <= ROW_DIMENSION; row++) {
            for (int col = 1; col <= COL_DIMENSION; col++) {
                Field f = new Field(number(row, col));
                playingBoard.put(number(row, col), f);
            }
        }
    }

    public void move(int number, Player owner) {
        Field f = playingBoard.get(number);
        f.setOwner(owner);
    }

    public Board copy() {
        Board board = new Board();
        for (int row = 1; row <= ROW_DIMENSION; row++) {
            for (int col = 1; col <= COL_DIMENSION; col++) {
                Field f = this.playingBoard.get(number(row, col));
                board.setField(f.getNumber(), f.copy());
            }
        }

        return board;
    }

    public List<Integer> validMoves() {

        List<Integer> validMoves = new ArrayList<>();
        for (int row = 1; row <= ROW_DIMENSION; row++) {
            for (int col = 1; col <= COL_DIMENSION; col++) {
                if (isValid(row, col)) {
                    validMoves.add(number(row, col));
                }
            }
        }

        return validMoves;
    }

    public boolean isValid(int row, int col) {
        return isValid((number(row, col)));
    }

    public boolean isValid(int n) {

        if (n > ROW_DIMENSION * COL_DIMENSION) {
            return false;
        } else if (playingBoard.get(n).getOwner() == Player.NONE) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isWon(Player o) {

        boolean won = false;

        // Check three in a row
        for (int row = 1; row <= ROW_DIMENSION; row++) {
            int count = 0;
            for (int col = 1; col <= COL_DIMENSION; col++) {
                if (playingBoard.get(number(row, col)).getOwner() == o) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == COL_DIMENSION) {
                won = true;
            }
        }

        // Check three in a col
        for (int col = 1; col <= COL_DIMENSION; col++) {
            int count = 0;
            for (int row = 1; row <= ROW_DIMENSION; row++) {
                if (playingBoard.get(number(row, col)).getOwner() == o) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == ROW_DIMENSION) {
                won = true;
            }
        }

        // Check three diagonal left top to right bottom
        int diagonal = 0;
        for (int x = 1; x <= ROW_DIMENSION; x++) {

            if (playingBoard.get(number(x, x)).getOwner() == o) {
                diagonal++;
            } else {
                break;
            }
        }

        if (diagonal == COL_DIMENSION) {
            won = true;
        }


        // Check three diagonal left bottom to right top
        diagonal = 0;
        for (int x = ROW_DIMENSION; x > 0; x--) {

            if (playingBoard.get(number(x, x)).getOwner() == o) {
                diagonal++;
            } else {
                break;
            }
        }

        if (diagonal == COL_DIMENSION) {
            won = true;
        }

        return won;
    }

    public boolean isDraw() {
        if (validMoves().size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean gameEnded() {

        if (isDraw()) {
            printToScreen();
            System.out.println("Game is draw!");
            return true;
        }

        if (isWon(Player.O)) {
            printToScreen();
            System.out.println("Game won by " + Player.O.getRepresentation() + "!");
            return true;
        }

        if (isWon(Player.X)) {
            printToScreen();
            System.out.println("Game won by " + Player.X.getRepresentation() + "!");
            return true;
        }

        return false;
    }

    public void printToScreen() {

        System.out.println("==========================");
        for (int row = 1; row <= ROW_DIMENSION; row++) {
            for (int col = 1; col <= COL_DIMENSION; col++) {
                Field f = playingBoard.get(number(row, col));
                System.out.print(f.screenRepresentation());
            }
            System.out.println();
        }
        System.out.println("==========================");

    }

    public Field getField(int number) {
        return playingBoard.get(number);
    }

    private void setField(int number, Field f) {
        playingBoard.put(number, f);
    }

    private int number(int x, int y) {
        return ((x-1) * COL_DIMENSION) + y;
    }

}
