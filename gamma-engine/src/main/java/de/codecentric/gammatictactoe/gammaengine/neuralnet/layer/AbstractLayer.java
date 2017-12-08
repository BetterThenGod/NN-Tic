package de.codecentric.gammatictactoe.gammaengine.neuralnet.layer;

import de.codecentric.gammatictactoe.gammaengine.neuralnet.neuron.AbstractNeuron;

import java.util.List;

public abstract class AbstractLayer {

    private List<AbstractNeuron> neurons;

    private int neuronNum;

    public void initialize(int neuronNum) {
        this.neuronNum = neuronNum;
    }

    public List<AbstractNeuron> getNeurons() {
        return neurons;
    }

    public void addNeuron(AbstractNeuron n) {
        neurons.add(n);
    }
}
