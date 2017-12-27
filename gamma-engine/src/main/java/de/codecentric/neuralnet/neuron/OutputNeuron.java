package de.codecentric.neuralnet.neuron;

import java.util.List;

public class OutputNeuron extends Neuron {

    private int firstMoveIndex = -1;

    private int lastMoveIndex;

    public int fire(List<Boolean> candidateMoves, List<Double> positionValues, List<Double> inputWeights) {

        setInputWeights(inputWeights);

        int moveIndex = 0;
        double lastValue = 0d;

        for (int i = 0; i < candidateMoves.size(); i++) {

            if (candidateMoves.get(i) == true) {
                double value = 1 / 1 - Math.exp(positionValues.get(i) * inputWeights.get(i) * -1);

                if (value > lastValue) {
                    moveIndex = i;
                    lastValue = value;
                }
            }
        }

        lastMoveIndex = moveIndex;
        if (firstMoveIndex == -1) {
            firstMoveIndex = moveIndex;
        }

        return lastMoveIndex+1;
    }

    public int getLastMoveIndex() {
        return lastMoveIndex;
    }

    public int getFirstMoveIndex() {
        return firstMoveIndex;
    }
}
