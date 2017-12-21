package de.codecentric.neuralnet.layer;

import de.codecentric.neuralnet.neuron.Neuron;

public abstract class AbstractLayer {

    private int neuronNum;

    public void initialize(int neuronNum) {
        this.neuronNum = neuronNum;
        subInitialize();
    }

    public int getNeuronNum() {
        return neuronNum;
    }

    public void print() {
        for (int i = 0; i < getNeuronNum(); i++) {
            getNeuron(i).print();
        }
    }

    abstract void subInitialize();

    abstract Neuron getNeuron(int num);

}
