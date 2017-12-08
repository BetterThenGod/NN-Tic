package de.codecentric.gammatictactoe.gammaengine.neuralnet.layer;


public abstract class AbstractLayer {

    private int neuronNum;

    public void initialize(int neuronNum) {
        this.neuronNum = neuronNum;
        subInitialize();
    }

    public int getNeuronNum() {
        return neuronNum;
    }

    abstract void subInitialize();
}
