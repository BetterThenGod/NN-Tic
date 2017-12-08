package de.codecentric.gammatictactoe.gammaengine.board;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Board {

    private static final int X_DIMENSION = 3;

    private static final int Y_DIMENSION = 3;

    private Map<Integer, Field> playingBoard = new HashMap<>();


    @PostConstruct
    public void initialize() {
        for (int x = 1; x <= X_DIMENSION; x++) {
            for (int y = 1; y <= Y_DIMENSION; y++) {
                Field f = new Field(number(x, y));
                playingBoard.put(number(x, y), f);
            }
        }
    }

    public void move(int number, Owner owner) {
        Field f = playingBoard.get(number);
        f.setOwner(owner);
    }

    public Board copy() {
        Board board = new Board();
        for (int x = 1; x <= X_DIMENSION; x++) {
            for (int y = 1; y <= Y_DIMENSION; y++) {
                Field f = this.playingBoard.get(number(x, y));
                board.setField(f.getNumber(), f.copy());

            }
        }

        return board;
    }

    public List<Integer> validMoves() {

        List<Integer> validMoves = new ArrayList<>();
        for (int x = 1; x <= X_DIMENSION; x++) {
            for (int y = 1; y <= Y_DIMENSION; y++) {
                if (isValid(x, y)) {
                    validMoves.add(number(x, y));
                }
            }
        }

        return validMoves;
    }

    public boolean isValid(int x, int y) {
        return isValid((number(x, y)));
    }

    public boolean isValid(int n) {

        if (n > X_DIMENSION * Y_DIMENSION) {
            return false;
        } else if (playingBoard.get(n).getOwner() == Owner.NONE) {
            return true;
        } else {
            return false;
        }
    }


    public boolean won(Owner o) {

        boolean won = false;

        // Check three in a row linewise
        for (int x = 1; x <= X_DIMENSION; x++) {
            int line = 0;
            for (int y = 1; y <= Y_DIMENSION; y++) {
                if (playingBoard.get(number(x, y)).getOwner() == o) {
                    line++;
                } else {
                    break;
                }
            }
            if (line == Y_DIMENSION) {
                won = true;
            }
        }

        // Check three in a row linewise
        for (int y = 1; y <= Y_DIMENSION; y++) {
            int row = 0;
            for (int x = 1; x <= X_DIMENSION; x++) {
                if (playingBoard.get(number(x, y)).getOwner() == o) {
                    row++;
                } else {
                    break;
                }
            }
            if (row == X_DIMENSION) {
                won = true;
            }
        }

        // Check three diagonal left top to right bottom
        int diagonal = 0;
        for (int x = 1; x <= X_DIMENSION; x++) {

            if (playingBoard.get(number(x, x)).getOwner() == o) {
                diagonal++;
            } else {
                break;
            }
        }

        if (diagonal == Y_DIMENSION) {
            won = true;
        }


        // Check three diagonal left bottom to right top
        diagonal = 0;
        for (int x = X_DIMENSION; x > 0; x--) {

            if (playingBoard.get(number(x, x)).getOwner() == o) {
                diagonal++;
            } else {
                break;
            }
        }

        if (diagonal == Y_DIMENSION) {
            won = true;
        }


        return won;
    }

    public void printToScreen() {

        System.out.println("==========================");
        for (int x = 1; x <= X_DIMENSION; x++) {
            for (int y = 1; y <= Y_DIMENSION; y++) {
                Field f = playingBoard.get(number(x, y));
                System.out.print(f.screenRepresentation());
            }
            System.out.println();
        }
        System.out.println("==========================");

    }

    private void setField(int number, Field f) {
        playingBoard.put(number, f);
    }

    private int number(int x, int y) {
        return ((x-1) * Y_DIMENSION) + y;
    }

}
