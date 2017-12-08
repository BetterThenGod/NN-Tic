package de.codecentric.gammatictactoe.gammaengine.neuralnet.neuron;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNeuron {

    private List<Double> weightInList;

    private List<Double> weightOutList;

    private int weightInNum;

    private int weightOutNum;

    public void initialize(int weightInNum, int weightOutNum) {
        this.weightInNum = weightInNum;
        this.weightOutNum = weightOutNum;

        weightInList = new ArrayList<>();
        for (int i = 0; i < weightInNum; i++) {
            weightInList.add(initialWeight());
        }

        weightOutList = new ArrayList<>();
        for (int o = 0; o < weightInNum; o++) {
            weightOutList.add(initialWeight());
        }
    }

    public List<Double> getWeightInList() {
        return weightInList;
    }

    public void setWeightInList(List<Double> weightInList) {
        this.weightInList = weightInList;
    }

    public List<Double> getWeightOutList() {
        return weightOutList;
    }

    public void setWeightOutList(List<Double> weightOutList) {
        this.weightOutList = weightOutList;
    }

    private double initialWeight() {
        return Math.random();
    }

}
