package de.codecentric.neuralnet.neuron;

import java.util.ArrayList;
import java.util.List;

public class Neuron {

    private List<Double> inputWeights;

    private List<Double> outputWeights;

    private int numberOfInputWeights;

    private int numberOfOutputWeights;

    private int number;

    public void initialize(int number, int numberOfInputWeights, int numberOfOutputWeights) {
        this.number = number;
        this.numberOfInputWeights = numberOfInputWeights;
        this.numberOfOutputWeights = numberOfOutputWeights;

        inputWeights = new ArrayList<>();
        for (int i = 0; i < numberOfInputWeights; i++) {
            inputWeights.add(initialWeight());
        }

        outputWeights = new ArrayList<>();
        for (int o = 0; o < numberOfOutputWeights; o++) {
            outputWeights.add(initialWeight());
        }
    }

    public void print() {
        System.out.print("Input weights for neuron " + number + ": ");
        for (int i = 0; i < numberOfInputWeights; i++) {
            System.out.print("[" + inputWeights.get(i) + "] ");
        }
        System.out.println();

        System.out.print("Output weights for neuron " + number + ": ");
        for (int i = 0; i < numberOfOutputWeights; i++) {
            System.out.print("[" + outputWeights.get(i) + "] ");
        }
        System.out.println();
    }

    public List<Double> getInputWeights() {
        return inputWeights;
    }

    public void setInputWeights(List<Double> inputWeights) {
        this.inputWeights = inputWeights;
    }

    public List<Double> getOutputWeights() {
        return outputWeights;
    }

    public void setOutputWeights(List<Double> outputWeights) {
        this.outputWeights = outputWeights;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private double initialWeight() {
        return Math.random();
    }



}
