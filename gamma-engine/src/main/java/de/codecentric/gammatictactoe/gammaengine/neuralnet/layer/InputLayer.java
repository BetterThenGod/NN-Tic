package de.codecentric.gammatictactoe.gammaengine.neuralnet.layer;

import de.codecentric.gammatictactoe.gammaengine.board.Board;
import de.codecentric.gammatictactoe.gammaengine.neuralnet.neuron.InputNeuron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InputLayer extends AbstractLayer {

    @Autowired
    private List<InputNeuron> inputNeurons;


    @Override
    public void subInitialize() {
        for (int i = 0; i < getNeuronNum(); i++) {
            InputNeuron n = new InputNeuron();
            n.initialize(0, 10);
            inputNeurons.add(n);
        }
    }

    public void fireNeurons(Board board) {

        for (int i = 0; i < getNeuronNum(); i++) {
            inputNeurons.get(i).fire(board);
        }
    }
}
