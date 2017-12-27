package de.codecentric.neuralnet.layer;

import de.codecentric.game.tictactoe.game.PlayerEnum;
import de.codecentric.neuralnet.neuron.HiddenNeuron;

import java.util.ArrayList;
import java.util.List;

public class HiddenLayer extends AbstractLayer {

    private List<HiddenNeuron> hiddenNeurons;

    @Override
    public void subInitialize() {
        hiddenNeurons = new ArrayList<>();
        for (int i = 0; i < getNumberOfNeurons(); i++) {
            HiddenNeuron n = new HiddenNeuron();
            n.initialize(i,27, 1);
            hiddenNeurons.add(n);
        }
    }

    @Override
    public HiddenNeuron getNeuron(int num) {
        return hiddenNeurons.get(num);
    }

    public void fire(InputLayer inputLayer, PlayerEnum player) {

        for (int i = 0; i < getNumberOfNeurons(); i++) {
            hiddenNeurons.get(i).fire(inputLayer.getFields(), inputLayer.getOutputWeigths(i), player);
        }
    }

    public List<Double> getOutputWeigths(int num) {

        List<Double> weights = new ArrayList<>();
        for (int i = 0; i < getNumberOfNeurons(); i++) {
            weights.add(hiddenNeurons.get(i).getOutputWeights().get(num));
        }

        return weights;
    }

    public List<Boolean> candidateMoves() {

        List<Boolean> candidateMoves = new ArrayList<>();
        for (int i = 0; i < getNumberOfNeurons(); i++) {
            candidateMoves.add(hiddenNeurons.get(i).isCandidateMove());
        }

        return candidateMoves;
    }

    public List<Double> positionValues() {

        List<Double> positionValues = new ArrayList<>();
        for (int i = 0; i < getNumberOfNeurons(); i++) {
            positionValues.add(hiddenNeurons.get(i).getPositionValue());
        }

        return positionValues;
    }

    public void reward(int index, double value) {
        HiddenNeuron neuron = getNeuron(index);
        neuron.getOutputWeights().set(0, neuron.getOutputWeights().get(0) + value);

        if (neuron.getOutputWeights().get(0) >= 1) {
            neuron.getOutputWeights().set(0, 0.999d);
        }

    }
}
