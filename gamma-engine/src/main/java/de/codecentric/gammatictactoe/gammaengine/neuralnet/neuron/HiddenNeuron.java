package de.codecentric.gammatictactoe.gammaengine.neuralnet.neuron;

import de.codecentric.gammatictactoe.gammaengine.board.Board;
import org.springframework.stereotype.Component;

@Component
public class HiddenNeuron extends AbstractNeuron {

    private Board board;

    private int move;

    public void initialize(int weightInNum, int weightOutNum, Board board, int move) {
        initialize(weightInNum, weightOutNum);
        this.board = board;
        this.move = move;
    }
}
