package de.codecentric.neuralnet.layer;

import de.codecentric.neuralnet.neuron.HiddenNeuron;

import java.util.ArrayList;
import java.util.List;

public class HiddenLayer extends AbstractLayer {

    private List<HiddenNeuron> hiddenNeurons;

    @Override
    public void subInitialize() {
        hiddenNeurons = new ArrayList<>();
        for (int i = 0; i < getNeuronNum(); i++) {
            HiddenNeuron n = new HiddenNeuron();
            n.initialize(i,9, 1);
            hiddenNeurons.add(n);
        }
    }

    @Override
    public HiddenNeuron getNeuron(int num) {
        return hiddenNeurons.get(num);
    }

    public void fire(InputLayer inputLayer) {

        for (int i = 0; i < getNeuronNum(); i++) {
            hiddenNeurons.get(i).fire(inputLayer.getFields(), inputLayer.getOutputWeigths(i));
        }
    }


    public List<Double> getOutputWeigths(int num) {

        List<Double> weights = new ArrayList<>();
        for (int i = 0; i < getNeuronNum(); i++) {
            weights.add(hiddenNeurons.get(i).getWeightOutList().get(num));
        }

        return weights;
    }

    public List<Integer> getCandidateMoves() {

        List<Integer> candidateMoves = new ArrayList<>();

        for (int i = 0; i < getNeuronNum(); i++) {
            candidateMoves.add(hiddenNeurons.get(i).getCandidateMove());
        }

        return candidateMoves;
    }

    public void reward(int index) {
        HiddenNeuron neuron = getNeuron(index);
        neuron.getWeightOutList().set(0, neuron.getWeightOutList().get(0) + 0.05d);

        if (neuron.getWeightOutList().get(0) >= 1) {
            neuron.getWeightOutList().set(0, 0.999d);
        }

    }
}
