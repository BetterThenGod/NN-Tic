package de.codecentric.neuralnet;

import de.codecentric.game.playing.Engine;
import de.codecentric.game.tictactoe.game.Board;
import de.codecentric.game.tictactoe.game.Player;
import de.codecentric.neuralnet.layer.HiddenLayer;
import de.codecentric.neuralnet.layer.InputLayer;
import de.codecentric.neuralnet.layer.OutputLayer;

public class GammaEngine implements Engine {

    private int learningStage;

    private InputLayer inputLayer;

    private HiddenLayer hiddenLayer;

    private OutputLayer outputLayer;

    public GammaEngine(int learningStage) {
        this.learningStage = learningStage;
        this.initialize();
    }

    public void initialize() {

        inputLayer = new InputLayer();
        inputLayer.initialize(27);

        hiddenLayer = new HiddenLayer();
        hiddenLayer.initialize(9);

        outputLayer = new OutputLayer();
        outputLayer.initialize(1);
    }

    @Override
    public int makeMove(Board board, Player player, boolean trainingEnabled) {

        inputLayer.fire(board);
        hiddenLayer.fire(inputLayer, player);
        int move = outputLayer.fire(hiddenLayer);

        board.move(move, player);

        if (trainingEnabled) {
            if (board.isWon(player)) {
                if (learningStage >= 1) {
                    hiddenLayer.reward(outputLayer.getNeuron(0).getLastMoveIndex(), 0.05d);
                    inputLayer.reward(outputLayer.getNeuron(0).getLastMoveIndex(), 0.075d,
                            hiddenLayer.getNeuron(outputLayer.getNeuron(0).getLastMoveIndex()).getLastUsedInputNeurons());

                    hiddenLayer.reward(outputLayer.getNeuron(0).getFirstMoveIndex(), 0.05d);
                    inputLayer.reward(outputLayer.getNeuron(0).getFirstMoveIndex(), 0.075d,
                            hiddenLayer.getNeuron(outputLayer.getNeuron(0).getLastMoveIndex()).getFirstUsedInputNeurons());
                }
            }
        }

        return move;
    }

    @Override
    public void newGame() {
        for (int i = 0; i < hiddenLayer.getNeuronNum(); i++) {
            hiddenLayer.getNeuron(i).resetUsedInputNeurons();
        }
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
