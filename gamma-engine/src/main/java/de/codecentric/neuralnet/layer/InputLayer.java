package de.codecentric.neuralnet.layer;

import de.codecentric.game.tictactoe.board.Board;
import de.codecentric.neuralnet.neuron.InputNeuron;

import java.util.ArrayList;
import java.util.List;

public class InputLayer extends AbstractLayer {

    private List<InputNeuron> inputNeurons;


    @Override
    public void subInitialize() {
        inputNeurons = new ArrayList<>();
        for (int i = 0; i < getNeuronNum(); i++) {
            InputNeuron n = new InputNeuron();
            n.initialize(i,0, 9);
            inputNeurons.add(n);
        }
    }

    public void fireNeurons(Board board) {

        for (int i = 0; i < getNeuronNum(); i++) {
            inputNeurons.get(i).fire(board.getField(i+1));
        }
    }

    @Override
    public InputNeuron getNeuron(int num) {
        return inputNeurons.get(num);
    }
}
