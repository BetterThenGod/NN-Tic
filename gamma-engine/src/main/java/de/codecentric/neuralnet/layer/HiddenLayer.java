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
}
