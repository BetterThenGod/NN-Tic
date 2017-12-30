package de.codecentric.neuralnet.neuron;

import de.codecentric.game.tictactoe.game.Field;
import de.codecentric.game.tictactoe.game.PlayerEnum;

import java.util.ArrayList;
import java.util.List;


public class HiddenNeuron extends Neuron {

    private boolean isCandidateMove;

    private double positionValue;

    private List<Integer> firstUsedInputNeurons;

    private List<Integer> lastUsedInputNeurons = new ArrayList<>();

    public void activate(List<Field> fields, List<Double> values, List<Double> inputWeights, PlayerEnum forPlayer) {

        setInputWeights(inputWeights);

        int relevantFieldNum = getNumber() * 3;

        if (fields.get(relevantFieldNum).getOwner() == PlayerEnum.NONE) {
            isCandidateMove = true;
        } else {
            isCandidateMove = false;
        }

        boolean isFirstMove = false;
        if (firstUsedInputNeurons == null) {
            firstUsedInputNeurons = new ArrayList<>();
            isFirstMove = true;
        }
        lastUsedInputNeurons.clear();

        //
        // Using different input weights and different field values depending on the fact if a field is
        // empty, already owned or owned by the opponent.
        //
        double sumOfInputWeights = 0d;
        int inputNum = 0;
        for (int i = 1; i <= 9; i++) {
            if (fields.get(inputNum).getOwner() == PlayerEnum.NONE) {
                sumOfInputWeights += (inputWeights.get(inputNum) * values.get(inputNum));
                if (isFirstMove) {
                    firstUsedInputNeurons.add(inputNum);
                }
                lastUsedInputNeurons.add(inputNum);
                inputNum += 3;
            } else if (fields.get(inputNum + 1).getOwner() == forPlayer) {
                sumOfInputWeights += (inputWeights.get(inputNum + 1) * (values.get(inputNum + 1) / 2));
                if (isFirstMove) {
                    firstUsedInputNeurons.add(inputNum + 1);
                }
                lastUsedInputNeurons.add(inputNum + 1);
                inputNum += 3;
            } else {
                sumOfInputWeights += (inputWeights.get(inputNum + 2) * (values.get(inputNum + 2) / 4));
                if (isFirstMove) {
                    firstUsedInputNeurons.add(inputNum + 2);
                }
                lastUsedInputNeurons.add(inputNum + 2);
                inputNum += 3;
            }
        }

        positionValue = 1 / (1 + Math.exp(sumOfInputWeights * (-1)));
    }

    public boolean isCandidateMove() {
        return isCandidateMove;
    }

    public double getPositionValue() {
        return positionValue;
    }

    public List<Integer> getFirstUsedInputNeurons() {
        return firstUsedInputNeurons;
    }

    public List<Integer> getLastUsedInputNeurons() {
        return lastUsedInputNeurons;
    }

    public void resetBetweenGames() {
        firstUsedInputNeurons = null;
        lastUsedInputNeurons.clear();
    }
}
