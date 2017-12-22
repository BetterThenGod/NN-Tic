package de.codecentric.neuralnet;

import de.codecentric.game.tictactoe.board.Board;
import de.codecentric.game.tictactoe.board.Player;
import de.codecentric.neuralnet.layer.HiddenLayer;
import de.codecentric.neuralnet.layer.InputLayer;
import de.codecentric.neuralnet.layer.OutputLayer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NeuralNet {

    private InputLayer inputLayer;

    private HiddenLayer hiddenLayer;

    private OutputLayer outputLayer;


    @PostConstruct
    public void initialize() {

        inputLayer = new InputLayer();
        inputLayer.initialize(9);

        hiddenLayer = new HiddenLayer();
        hiddenLayer.initialize(9);

        outputLayer = new OutputLayer();
        outputLayer.initialize(1);
    }


    public int makeMove(Board board, Player owner, boolean trainingEnabled) {

        inputLayer.fire(board);
        hiddenLayer.fire(inputLayer);
        int move = outputLayer.fire(hiddenLayer);

        board.move(move, owner);

        if (trainingEnabled) {
            if (board.isWon(owner)) {
                hiddenLayer.reward(outputLayer.getNeuron(0).getLastMoveIndex());
                inputLayer.reward(outputLayer.getNeuron(0).getLastMoveIndex());
            }
        }

        return move;
    }

    public void print() {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("I n p u t  L a y e r");
        System.out.println("==================================================");
        inputLayer.print();

        System.out.println();
        System.out.println("==================================================");
        System.out.println("H i d d e n  L a y e r");
        System.out.println("==================================================");
        hiddenLayer.print();

        System.out.println();
        System.out.println("==================================================");
        System.out.println("O u t p u t  L a y e r");
        System.out.println("==================================================");
        outputLayer.print();
    }
}
