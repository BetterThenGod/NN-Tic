package de.codecentric.gammatictactoe.gammaengine.neuralnet.layer;

import de.codecentric.gammatictactoe.gammaengine.neuralnet.neuron.HiddenNeuron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HiddenLayer extends AbstractLayer {

    @Autowired
    private List<HiddenNeuron> hiddenNeurons;

    @Override
    public void subInitialize() {
        for (int i = 0; i < getNeuronNum(); i++) {
            HiddenNeuron n = new HiddenNeuron();
            n.initialize(1, 10);
            hiddenNeurons.add(n);
        }
    }
}
