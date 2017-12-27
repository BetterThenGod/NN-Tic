package de.codecentric.neuralnet.neuron;

import de.codecentric.game.tictactoe.board.Field;
import de.codecentric.game.tictactoe.board.Player;

import java.util.ArrayList;
import java.util.List;


public class HiddenNeuron extends Neuron {

    private boolean isCandidateMove;

    private double positionValue;

    public void fire(List<Field> fields, List<Double> inputWeights, Player player) {

        setInputWeights(inputWeights);

        int relevantFieldNum = getNumber() * 3;
        if (fields.get(relevantFieldNum).getOwner() == Player.NONE) {
            isCandidateMove = true;
        } else {
            isCandidateMove = false;
        }

        double sumOfInputWeights = 0d;
        int inputNum = 0;
        for (int i = 1; i <= 9; i++) {
            if (fields.get(inputNum).getOwner() == Player.NONE) {
                sumOfInputWeights += inputWeights.get(inputNum);
                inputNum += 3;
            } else if (fields.get(inputNum).getOwner() == player) {
                sumOfInputWeights += inputWeights.get(inputNum+1);
                inputNum += 3;
            } else {
                sumOfInputWeights += inputWeights.get(inputNum+2);
                inputNum += 3;
            }
        }

        positionValue = 1 / 1 - Math.exp(sumOfInputWeights * (-1));
    }

    public boolean isCandidateMove() {
        return isCandidateMove;
    }

    public double getPositionValue() {
        return positionValue;
    }
}
