package de.codecentric.game.tools;

import de.codecentric.game.tictactoe.board.Board;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomPlayer {


    public int makeMove(Board board) {

        List<Integer> validMoves = board.validMoves();
        int randomNum = ThreadLocalRandom.current().nextInt(0, validMoves.size());

        return validMoves.get(randomNum);
    }
}
