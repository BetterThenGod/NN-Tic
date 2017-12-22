package de.codecentric.neuralnet.layer;

import de.codecentric.game.tictactoe.board.Board;
import de.codecentric.game.tictactoe.board.Field;
import de.codecentric.neuralnet.neuron.InputNeuron;

import java.util.ArrayList;
import java.util.List;

public class InputLayer extends AbstractLayer {

    private List<InputNeuron> inputNeurons;


    @Override
    public void subInitialize() {
        inputNeurons = new ArrayList<>();
        for (int i = 0; i < getNeuronNum(); i++) {
            InputNeuron n = new InputNeuron();
            n.initialize(i,0, 9);
            inputNeurons.add(n);
        }
    }

    public void fire(Board board) {
        for (int i = 0; i < getNeuronNum(); i++) {
            inputNeurons.get(i).fire(board.getField(i+1));
        }
    }

    @Override
    public InputNeuron getNeuron(int num) {
        return inputNeurons.get(num);
    }

    public List<Double> getOutputWeigths(int num) {

        List<Double> weights = new ArrayList<>();
        for (int i = 0; i < getNeuronNum(); i++) {
            weights.add(inputNeurons.get(i).getWeightOutList().get(num));
        }

        return weights;
    }

    public List<Field> getFields() {
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < getNeuronNum(); i++) {
            fields.add(inputNeurons.get(i).getField());
        }

        return fields;
    }

    public void reward(int index) {

        for (int i = 0; i < getNeuronNum(); i++) {
            InputNeuron neuron = inputNeurons.get(i);
            neuron.getWeightOutList().set(index, neuron.getWeightOutList().get(index) + 0.025d);

            if (neuron.getWeightOutList().get(index) >= 1) {
                neuron.getWeightOutList().set(index, 0.999d);
            }
        }
    }
}
