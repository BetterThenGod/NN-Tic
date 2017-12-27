package de.codecentric.game.tictactoe.game;

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
                Field field = new Field(number(row, col));
                playingBoard.put(number(row, col), field);
            }
        }
    }

    public void move(int number, PlayerEnum owner) {
        if (playingBoard.get(number).getOwner() != PlayerEnum.NONE) {
            throw new RuntimeException("Illegal move, field already occupied: " + number);
        }

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
        } else if (playingBoard.get(n).getOwner() == PlayerEnum.NONE) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isWon(PlayerEnum player) {

        boolean won = false;

        // Check three in a row
        for (int row = 1; row <= ROW_DIMENSION; row++) {
            int count = 0;
            for (int col = 1; col <= COL_DIMENSION; col++) {
                if (playingBoard.get(number(row, col)).getOwner() == player) {
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
                if (playingBoard.get(number(row, col)).getOwner() == player) {
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
        if (playingBoard.get(number(1, 1)).getOwner() == player) {
            diagonal++;
        }
        if (playingBoard.get(number(2, 2)).getOwner() == player) {
            diagonal++;
        }
        if (playingBoard.get(number(3, 3)).getOwner() == player) {
            diagonal++;
        }

        if (diagonal == COL_DIMENSION) {
            won = true;
        }

        // Check three diagonal left bottom to right top
        diagonal = 0;
        if (playingBoard.get(number(1, 3)).getOwner() == player) {
            diagonal++;
        }
        if (playingBoard.get(number(2, 2)).getOwner() == player) {
            diagonal++;
        }
        if (playingBoard.get(number(3, 1)).getOwner() == player) {
            diagonal++;
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

        if (isWon(PlayerEnum.O)) {
            printToScreen();
            System.out.println("Game won by " + PlayerEnum.O.getRepresentation() + "!");
            return true;
        }

        if (isWon(PlayerEnum.X)) {
            printToScreen();
            System.out.println("Game won by " + PlayerEnum.X.getRepresentation() + "!");
            return true;
        }

        if (isDraw()) {
            printToScreen();
            System.out.println("Game is draw!");
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
