package de.codecentric.gammatictactoe.gammaengine.neuralnet;

import de.codecentric.gammatictactoe.gammaengine.board.Board;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NeuralNet {

    public int calculate(Board board) {
        List<Integer> validMoves = board.validMoves();
        return validMoves.get(0);
    }
}
