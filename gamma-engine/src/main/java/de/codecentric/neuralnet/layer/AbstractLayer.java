package de.codecentric.neuralnet.layer;

import de.codecentric.neuralnet.neuron.Neuron;

public abstract class AbstractLayer {

    private int numberOfNeurons;

    public void initialize(int numberOfNeurons) {
        this.numberOfNeurons = numberOfNeurons;
        subInitialize();
    }

    public int getNumberOfNeurons() {
        return numberOfNeurons;
    }

    public void print() {
        for (int i = 0; i < getNumberOfNeurons(); i++) {
            getNeuron(i).print();
        }
    }

    abstract void subInitialize();

    abstract Neuron getNeuron(int num);

}
