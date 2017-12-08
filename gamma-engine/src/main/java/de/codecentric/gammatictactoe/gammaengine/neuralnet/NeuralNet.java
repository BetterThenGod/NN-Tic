package de.codecentric.gammatictactoe.gammaengine.neuralnet;

import de.codecentric.gammatictactoe.gammaengine.board.Board;
import de.codecentric.gammatictactoe.gammaengine.neuralnet.layer.HiddenLayer;
import de.codecentric.gammatictactoe.gammaengine.neuralnet.layer.InputLayer;
import de.codecentric.gammatictactoe.gammaengine.neuralnet.layer.OutputLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class NeuralNet {

    @Autowired
    private InputLayer inputLayer;

    @Autowired
    private HiddenLayer hiddenLayer;

    @Autowired
    private OutputLayer outputLayer;


    @PostConstruct
    public void initialize() {
        inputLayer.initialize(1);
        hiddenLayer.initialize(10);
        outputLayer.initialize(1);
    }

    public int calculate(Board board) {
        List<Integer> validMoves = board.validMoves();
        return validMoves.get(0);
    }
}
