package de.codecentric.neuralnet.neuron;

import de.codecentric.game.tictactoe.board.Field;
import de.codecentric.game.tictactoe.board.Owner;

import java.util.ArrayList;
import java.util.List;

public class HiddenNeuron extends Neuron {

    int candidateMove;

    public void fire(List<Field> fields, List<Double> inputWeights) {

        setWeightInList(inputWeights);

        List<Integer> possibleMoves = new ArrayList<>();
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getOwner() == Owner.NONE) {
                possibleMoves.add(i);
            }
        }

        candidateMove = possibleMoves.get(0);
        for (int i = 1; i < possibleMoves.size(); i++) {
            if (inputWeights.get(possibleMoves.get(i)) / boardValue(fields) >
                    inputWeights.get(candidateMove) / boardValue(fields)) {
                candidateMove = possibleMoves.get(i);
            }
        }

        candidateMove += 1;
    }

    public int getCandidateMove() {
        return candidateMove;
    }

    private double boardValue(List<Field> fields) {

        double blueValue = 0;
        double redValue = 0;
        double noneValue = 0;

        for (Field f : fields) {
            if (f.getOwner() == Owner.BLUE) {
                blueValue += f.getValue();
            } else if (f.getOwner() == Owner.RED) {
                redValue += f.getValue();
            } else {
                noneValue += f.getValue();
            }
        }

        double value = (1 / (1-blueValue)) * (1 / (1-redValue)) + (1 / 1- Math.exp(noneValue));
        return value;
    }
}
