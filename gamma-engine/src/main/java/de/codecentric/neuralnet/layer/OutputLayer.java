package de.codecentric.neuralnet.layer;

import de.codecentric.neuralnet.neuron.OutputNeuron;

import java.util.ArrayList;
import java.util.List;

public class OutputLayer extends AbstractLayer {

    private List<OutputNeuron> outputNeurons;

    private int computerMove;

    @Override
    public void subInitialize() {
        outputNeurons = new ArrayList<>();
        for (int i = 0; i < getNeuronNum(); i++) {
            OutputNeuron n = new OutputNeuron();
            n.initialize(i,9, 1);
            outputNeurons.add(n);
        }
    }

    @Override
    public OutputNeuron getNeuron(int num) {
        return outputNeurons.get(num);
    }
}
