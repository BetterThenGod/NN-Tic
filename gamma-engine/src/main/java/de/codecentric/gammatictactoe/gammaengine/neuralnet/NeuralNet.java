package de.codecentric.gammatictactoe.gammaengine.neuralnet;

import de.codecentric.gammatictactoe.gammaengine.board.Board;
import de.codecentric.gammatictactoe.gammaengine.neuralnet.layer.HiddenLayer;
import de.codecentric.gammatictactoe.gammaengine.neuralnet.layer.InputLayer;
import de.codecentric.gammatictactoe.gammaengine.neuralnet.layer.OutputLayer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class NeuralNet {

    private InputLayer inputLayer;

    private HiddenLayer hiddenLayer;

    private OutputLayer outputLayer;


    @PostConstruct
    public void initialize() {
        inputLayer = new InputLayer();
        inputLayer.initialize(1);

        hiddenLayer = new HiddenLayer();
        hiddenLayer.initialize(10);

        outputLayer = new OutputLayer();
        outputLayer.initialize(1);
    }

    public int calculate(Board board) {
        List<Integer> validMoves = board.validMoves();
        return validMoves.get(0);
    }
}
