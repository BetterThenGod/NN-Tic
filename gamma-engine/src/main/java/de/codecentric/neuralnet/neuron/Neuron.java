package de.codecentric.neuralnet.neuron;

import java.util.ArrayList;
import java.util.List;

public class Neuron {

    private List<Double> weightInList;

    private List<Double> weightOutList;

    private int weightInNum;

    private int weightOutNum;

    private int number;

    public void initialize(int number, int weightInNum, int weightOutNum) {
        this.number = number;
        this.weightInNum = weightInNum;
        this.weightOutNum = weightOutNum;

        weightInList = new ArrayList<>();
        for (int i = 0; i < weightInNum; i++) {
            weightInList.add(initialWeight());
        }

        weightOutList = new ArrayList<>();
        for (int o = 0; o < weightOutNum; o++) {
            weightOutList.add(initialWeight());
        }
    }

    public void print() {
        System.out.print("Input weights for neuron " + number + ": ");
        for (int i = 0; i < weightInNum; i++) {
            System.out.print("[" + weightInList.get(i) + "] ");
        }
        System.out.println();

        System.out.print("Output weights for neuron " + number + ": ");
        for (int i = 0; i < weightOutNum; i++) {
            System.out.print("[" + weightOutList.get(i) + "] ");
        }
        System.out.println();
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
