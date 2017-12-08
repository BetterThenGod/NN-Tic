package de.codecentric.gammatictactoe.gammaengine.neuralnet.layer;

import de.codecentric.gammatictactoe.gammaengine.neuralnet.layer.AbstractLayer;
import de.codecentric.gammatictactoe.gammaengine.neuralnet.neuron.HiddenNeuron;
import de.codecentric.gammatictactoe.gammaengine.neuralnet.neuron.OutputNeuron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OutputLayer extends AbstractLayer {

    @Autowired
    private List<OutputNeuron> outputNeurons;

    private int computerMove;

    @Override
    public void subInitialize() {
        for (int i = 0; i < getNeuronNum(); i++) {
            OutputNeuron n = new OutputNeuron();
            n.initialize(1, 10);
            outputNeurons.add(n);
        }
    }
}
