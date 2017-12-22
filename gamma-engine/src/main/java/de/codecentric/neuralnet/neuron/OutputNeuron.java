package de.codecentric.neuralnet.neuron;

import java.util.List;

public class OutputNeuron extends Neuron {


    private int lastMoveIndex;

    public int fire(List<Integer> candidateMoves, List<Double> inputWeights) {

        lastMoveIndex = 0;
        int move = candidateMoves.get(0);

        for (int i = 1; i < candidateMoves.size(); i++) {
            if (inputWeights.get(i) > inputWeights.get(lastMoveIndex)) {
                move = candidateMoves.get(i);
                lastMoveIndex = i;
            }
        }

        return move;
    }

    public int getLastMoveIndex() {
        return lastMoveIndex;
    }
}
